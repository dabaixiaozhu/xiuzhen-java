package com.jnshu.service.impl;

import com.jnshu.mapper.BannerMapper;
import com.jnshu.mapper.ManageMapper;
import com.jnshu.pojo.Banner;
import com.jnshu.service.admin.BannerService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(String title,String url,Long courseId,Long manageId) {
        Banner banner = new Banner();
        banner.setTitle(title);
        banner.setUrl(url);
        banner.setCourseId(courseId);
        banner.setStatus(0);

        long update = System.currentTimeMillis();
        banner.setUpdateAt(update);
        banner.setUpdateBy(manageId);
        banner.setCreatAt(update);
        banner.setCreatBy(manageId);
        banner.setCreatName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        banner.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        int result = this.bannerMapper.insert(banner);

        return result;
    }

    @Override
    public Banner selectByPrimaryKey(Long id) {
        return bannerMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Banner> selectAll() {
        List<Banner> result = bannerMapper.selectAll();
        return result;
    }

    @Override
    public Integer updateByPrimaryKeySelective(Long id,String title,String url,Long courseId,Integer status,Long manageId) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(title);
        banner.setUrl(url);
        banner.setCourseId(courseId);
        banner.setStatus(status);
        banner.setUpdateAt(System.currentTimeMillis());
        banner.setUpdateBy(manageId);
        banner.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());

        int result = this.bannerMapper.updateByPrimaryKeySelective(banner);
        return result;
    }

    @Override
    public Banner selectById(Long courseId) {
        return bannerMapper.selectById(courseId);
    }

}
