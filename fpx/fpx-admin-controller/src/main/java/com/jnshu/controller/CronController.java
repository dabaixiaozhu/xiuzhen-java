package com.jnshu.controller;

import com.jnshu.mapper.CronMapper;
import com.jnshu.mapper.MessageMapper;
import com.jnshu.pojo.Cron;
import com.jnshu.pojo.Message;
import com.jnshu.service.admin.MessageService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author L
 * 定时发送
 */
@Controller
@EnableScheduling
public class CronController {

    @Autowired
    private CronMapper cronMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Reference
    private MessageService messageService;

    @Scheduled(cron = "*/30 * * * * ?")
    private void configureTasks() {
        long time1 = System.currentTimeMillis();
        List<Cron> select = this.cronMapper.select(null);
        if (select != null && select.size()!=0) {
            for (Cron cron : select) {
                Long timestamp = cron.getTimestamp();
                if (time1 > timestamp){
                    // 1.删除tb_cron表中比当前时间戳小的内容
                    Example example = new Example(Cron.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.orEqualTo("timestamp",timestamp);
                    this.cronMapper.deleteByExample(example);

                    // 2.往tb_user_message中添加所有
                    this.messageService.addCronAll(time1);

                    // 3.更新tb_message表中所有比当前时间戳小 + status=0 的内容
                    this.messageMapper.updateByPushTime(time1);


                }
            }
        }
    }
}
