package com.kcfy.techservicemarket.application.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zhouxc on 2016/6/16.
 */
public class ScheduleUtil {
    private static Log log = LogFactory.getLog(ScheduleUtil.class);
    private static Scheduler scheduler = null;

    public static void startScheduler() {
        try {
            if (scheduler == null) {
                scheduler = new StdSchedulerFactory().getScheduler();
            }
            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e);
        }
    }

    public static void stopScheduler() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e);
        }
    }

    public static void scheduleJob(Class jobClazz, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
        JobDetail job = JobBuilder.newJob(jobClazz)
                .withIdentity(jobName, jobGroup)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void rescheduleJob(String oldTriggerName, String oldGroupName, String triggerName, String groupName, String cron) {
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, groupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        try {
            scheduler.rescheduleJob(TriggerKey.triggerKey(oldTriggerName, oldGroupName), trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void pauseJob(String jobName, String group) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void resumeJob(String jobName, String group) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static void deleteJob(String jobName, String group) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

}
