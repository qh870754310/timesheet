package com.kcfy.techservicemarket.application.scheduler;

import com.kcfy.techservicemarket.application.MessageJobApplication;
import com.kcfy.techservicemarket.core.MyApplicationContextUtil;
import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import javax.inject.Named;
import java.util.Date;

/**
 * Created by zhouxc on 2016/6/16.
 */
@Named
public class RemindJob implements Job {

    private Log log = LogFactory.getLog(RemindJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
            Long taskId = Long.valueOf(jobKey.getName().substring(3));//job1,1


            log.info("RemindJob says: " + jobKey + " executing at " + new Date());
            log.info("RemindJob says: task id is " + taskId);
            MessageJobApplication messageJobApplication = (MessageJobApplication) MyApplicationContextUtil.getContext().getBean("messageJobApplicationImpl");

            MessageJob messageJob = messageJobApplication.getMessageJob(taskId);
            MessageTemplate messageTemplate = messageJob.getMessageTemplate();

            log.info("RemindJob says: message template name is " + messageTemplate.getName());
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }
}
