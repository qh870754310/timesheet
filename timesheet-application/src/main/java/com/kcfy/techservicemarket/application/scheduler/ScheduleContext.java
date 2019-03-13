package com.kcfy.techservicemarket.application.scheduler;

import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by zhouxc on 2016/6/16.
 */
@WebListener(value = "ScheduleContext")
public class ScheduleContext implements ServletContextListener {
    private Log log = LogFactory.getLog(ScheduleContext.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ScheduleContext Initialized");
        ScheduleUtil.startScheduler();
        // 装载数据库里的定时任务
        List<MessageJob> list = MessageJob.findAll(MessageJob.class);
        for(MessageJob messageJob : list){
            ScheduleUtil.scheduleJob(RemindJob.class,"job" + messageJob.getId(),"group",
                    "trigger" + messageJob.getId(),"group",messageJob.getCronExpression());
            if(!messageJob.isStatus()) {
                ScheduleUtil.pauseJob("job" + messageJob.getId(), "group");
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ScheduleContext Destroyed");
        ScheduleUtil.stopScheduler();
    }
}
