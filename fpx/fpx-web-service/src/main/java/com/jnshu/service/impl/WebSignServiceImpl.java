package com.jnshu.service.impl;

import com.jnshu.mapper.UserMapper;
import com.jnshu.mapper.UserSignMapper;
import com.jnshu.pojo.User;
import com.jnshu.pojo.UserSign;
import com.jnshu.service.web.WebSignService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Service
public class WebSignServiceImpl implements WebSignService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSignMapper userSignMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    /*timestamp转为LocalDateTime*/
    public LocalDateTime timeToLocal(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }

    /*计算相差的天数*/
    public Long betweenDay(LocalDateTime time1,LocalDateTime time2){
        Duration duration = Duration.between(time1,time2);
        Long days = duration.toDays(); //相差的天数
        return days;
    }

    /*计算相差的秒数*/
    public Long betweenSeconds(LocalDateTime time1,LocalDateTime time2){
        Duration duration = Duration.between(time1,time2);
        Long minutes = duration.toMinutes();//相差的天数
        return minutes;
    }

    /**
     * 1.之前连续签到的天数
     * @param id
     * @param timestamp
     * @return
     */
    @Override
    public UserSign signBefore(Long id, Long timestamp) {
        Long lastime = (Long) this.redisTemplate.boundValueOps(id + "fpxcheck").get();
        // 1.必然断签
        if (lastime == null){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            user.setSignDay(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }

        // 2.可能连续签到
        LocalDateTime localDateTime1 = timeToLocal(lastime);
        LocalDateTime localDateTime2 = timeToLocal(timestamp);
        Long betweenDay = betweenDay(localDateTime1, localDateTime2);

        if (betweenDay == 1L){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }else  if (betweenDay == 0L){
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }else {
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            user.setSignDay(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }
    }

    /**
     * 2.点击签到
     * @param id
     * @param timestamp
     * @return
     */
    @Override
    public UserSign signAfter(Long id, Long timestamp) {
        Long lastime = (Long) this.redisTemplate.boundValueOps(id + "fpxcheck").get();
        User user1 = this.userMapper.selectByPrimaryKey(id);
        // 1.必然断签
        if (lastime == null){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            user.setSignDay(1);
            user.setScore(user1.getScore()+1);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            this.redisTemplate.opsForValue().set(id+"fpxcheck",timestamp,48, TimeUnit.HOURS);
            return userSign;
        }

        // 2.可能连续签到
        LocalDateTime localDateTime1 = timeToLocal(lastime);
        LocalDateTime localDateTime2 = timeToLocal(timestamp);
        Long betweenDay = betweenDay(localDateTime1, localDateTime2);

        // 2.1连续签到
        if (betweenDay == 1L){
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            if (user1.getSignDay()==7){
                user.setSignDay(1);
                user.setScore(user1.getScore()+1);
            }else {
                user.setSignDay(user1.getSignDay()+1);
                user.setScore(user1.getScore()+user1.getSignDay()+1);
            }

            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            this.redisTemplate.opsForValue().set(id+"fpxcheck",timestamp,48, TimeUnit.HOURS);
            return userSign;
        }else  if (betweenDay == 0L){
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }
        // 2.2不连续签到
        else {
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            user.setSignDay(1);
            user.setScore(user1.getScore()+1);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }
    }

    /**
     * 3.之前连续签到的天数测试
     * @param id
     * @param timestamp
     * @return
     */
    @Override
    public UserSign signBeforeTes(Long id, Long timestamp) {
        Long lastime = (Long) this.redisTemplate.boundValueOps(id + "fpxcheck").get();
        // 1.必然断签
        if (lastime == null){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            user.setSignDay(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }

        // 2.可能连续签到
        LocalDateTime localDateTime1 = timeToLocal(lastime);
        LocalDateTime localDateTime2 = timeToLocal(timestamp);
        Long betweenSeconds = betweenSeconds(localDateTime1, localDateTime2);

        if (betweenSeconds == 1L){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }else  if (betweenSeconds == 0L){
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }else {
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(0);
            user.setSignDay(0);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }
    }

    /**
     * 4.点击签到测试
     * @param id
     * @param timestamp
     * @return
     */
    @Override
    public UserSign signAfterTes(Long id, Long timestamp) {
        Long lastime = (Long) this.redisTemplate.boundValueOps(id + "fpxcheck").get();
        User user1 = this.userMapper.selectByPrimaryKey(id);
        // 1.必然断签
        if (lastime == null){
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            user.setSignDay(1);
            user.setScore(user1.getScore()+1);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            this.redisTemplate.opsForValue().set(id+"fpxcheck",timestamp,2, TimeUnit.MINUTES);
            return userSign;
        }

        // 2.可能连续签到
        LocalDateTime localDateTime1 = timeToLocal(lastime);
        LocalDateTime localDateTime2 = timeToLocal(timestamp);
        Long betweenSeconds = betweenSeconds(localDateTime1, localDateTime2);

        // 2.1连续签到
        if (betweenSeconds == 1L){
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            if (user1.getSignDay()==7){
                user.setSignDay(1);
                user.setScore(user1.getScore()+1);
            }else {
                user.setSignDay(user1.getSignDay()+1);
                user.setScore(user1.getScore()+user1.getSignDay()+1);
            }
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            this.redisTemplate.opsForValue().set(id+"fpxcheck",timestamp,2, TimeUnit.MINUTES);
            return userSign;
        }else  if (betweenSeconds == 0L){
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            return userSign;
        }
        // 2.2不连续签到
        else {
            // 1.1更新sign_status为0，sign_day为0
            User user = new User();
            user.setId(id);
            user.setSignStatus(1);
            user.setSignDay(1);
            user.setScore(user1.getScore()+1);
            this.userMapper.updateByPrimaryKeySelective(user);

            // 1.2取出需要的数据
            UserSign userSign = this.userSignMapper.selectByPrimaryKey(id);
            this.redisTemplate.opsForValue().set(id+"fpxcheck",timestamp,2, TimeUnit.MINUTES);
            return userSign;
        }
    }
}
