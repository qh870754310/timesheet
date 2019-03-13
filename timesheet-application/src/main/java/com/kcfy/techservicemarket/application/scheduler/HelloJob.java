package com.kcfy.techservicemarket.application.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * Created by zhouxc on 2016/6/16.
 */
public class HelloJob implements Job {
    private Log log = LogFactory.getLog(HelloJob.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}
