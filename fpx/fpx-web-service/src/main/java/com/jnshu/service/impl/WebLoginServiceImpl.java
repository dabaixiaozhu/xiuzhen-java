package com.jnshu.service.impl;

import com.jnshu.mapper.UserMapper;
import com.jnshu.mapper.UserMessageMapper;
import com.jnshu.pojo.User;
import org.json.JSONException;
import org.json.JSONObject;
import com.jnshu.service.web.WebLoginService;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Service;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebLoginServiceImpl implements WebLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    Logger logger = LoggerFactory.getLogger(WebLoginServiceImpl.class);
    // 1.根据code获取token
    public String getToken(String code){
        // 拼接链接
        String appid = "wx0b31bcd6cbe880a4";
        String secret = "ef768d40a279dc0c811e6fda9dbbe176";
        String grantType = "authorization_code";
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"+"appid="+appid+"&secret="+secret+"&code="+code+"&grant_type="+grantType;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(getOpenIdUrl);
            HttpResponse execute = client.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String strResult = EntityUtils.toString(execute.getEntity());
                return strResult;
            }else {
                return "查询失败，请重试";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "查询异常，请联系开发人员";
        }
    }

    // 2.获取用户信息
    public String getUserInfo(String openid,String accesstoken){
        String aturl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN";
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(aturl);
            HttpResponse execute = client.execute(httpGet);

            if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String strResult = EntityUtils.toString(execute.getEntity());
                String result = new String(strResult.getBytes("ISO-8859-1"), "UTF-8");
                return result;
            }else {
                return "查询失败，请重试";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "查询异常，请联系开发人员";
        }
    }

    // 3.刷新token时间
    public String reFreshToken(String token){
        String appid = "wx0b31bcd6cbe880a4";
        String getUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"+"appid="+appid+"&grant_type=refresh_token&refresh_token="+token;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(getUrl);
            HttpResponse execute = client.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String strResult = EntityUtils.toString(execute.getEntity());
                return strResult;
            }else {
                return "查询失败，请重试";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "查询异常，请联系开发人员";
        }
    }

    /**
     * 1.微信登陆
     * @param code
     * @return
     */
    @Override
    public Map<String,Object> weixinLogin(String code) throws Exception {
        // 根据code获取token
        String token = getToken(code);
        logger.info("------------token:"+token);
        boolean result = token.contains("access_token");
        if (result){
            JSONObject maps = new JSONObject(token);
            String openid = String.valueOf(maps.get("openid"));
            String accesstoken = String.valueOf(maps.get("access_token"));
            String refresh_token = String.valueOf(maps.get("refresh_token"));


            // 2.获取用户信息
            String userInfo = getUserInfo(openid, accesstoken);
            logger.info("------------userInfo:"+userInfo);
            if (userInfo.contains("openid")){
                JSONObject userInfos = new JSONObject(userInfo);
                String nickname = String.valueOf(userInfos.get("nickname"));
                String  openid1 = String.valueOf(userInfos.get("openid"));
                String headimgurl = String.valueOf(userInfos.get("headimgurl"));

                Example example = new Example(User.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("openid",openid1);
                List<User> users = this.userMapper.selectByExample(example);
                if (users.size()>0){
                    User user = users.get(0);
                    String reFreshToken = reFreshToken(refresh_token);
                    if (user.getOld()==0){
                        User user1 = new User();
                        user1.setId(user.getId());
                        user1.setOld(1);
                        this.userMapper.updateByPrimaryKeySelective(user1);
                    }
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("id",user.getId());
                    map.put("old",1);
                    map.put("status",user.getStatus());
                    map.put("grade",user.getUserClass());
                    return map;
                }else {
                    User user = new User();
                    user.setNickname(nickname);
                    user.setHeadimgurl(headimgurl);
                    user.setStatus(0);
                    user.setOld(0);
                    user.setOpenid(openid1);
                    user.setScore(0);
                    user.setLoginStatus(0);
                    user.setSignStatus(0);
                    user.setSignDay(0);
                    long currentTimeMillis = System.currentTimeMillis();
                    user.setUpdateAt(currentTimeMillis);
                    user.setCreatAt(currentTimeMillis);
                    this.userMapper.insertSelective(user);
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("id",user.getId());
                    map.put("old",user.getOld());
                    map.put("status",user.getStatus());
                    map.put("grade",user.getUserClass());
                    return map;
                }
            }else {
                return null;
            }
        }
        return null;
    }
}
