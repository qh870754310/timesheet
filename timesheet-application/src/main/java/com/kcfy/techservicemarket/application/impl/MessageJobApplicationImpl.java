package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.MessageJobApplication;
import com.kcfy.techservicemarket.application.scheduler.RemindJob;
import com.kcfy.techservicemarket.application.scheduler.ScheduleUtil;
import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.Authority;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;
import java.util.Set;

@Named
@Transactional
public class MessageJobApplicationImpl implements MessageJobApplication {

	public MessageJob getMessageJob(Long id) {
		return MessageJob.get(MessageJob.class, id);
	}
	
	public void creatMessageJob(MessageJob messageJob) {
		messageJob.save();
		ScheduleUtil.scheduleJob(RemindJob.class, "job" + messageJob.getId(), "group",
				"trigger" + messageJob.getId(), "group", messageJob.getCronExpression());
		if(!messageJob.isStatus()) {
			ScheduleUtil.pauseJob("job" + messageJob.getId(), "group");
		}
	}
	
	public void updateMessageJob(MessageJob messageJob) {
		messageJob .save();
		ScheduleUtil.rescheduleJob("trigger" + messageJob.getId(), "group",
				"trigger" + messageJob.getId(), "group", messageJob.getCronExpression());
		if(!messageJob.isStatus()) {
			ScheduleUtil.pauseJob("job" + messageJob.getId(), "group");
		} else {
			ScheduleUtil.resumeJob("job" + messageJob.getId(), "group");
		}
	}
	
	public void removeMessageJob(MessageJob messageJob) {
		if(messageJob != null){
			messageJob.remove();
			ScheduleUtil.deleteJob("job" + messageJob.getId(), "group");
		}
	}
	
	public void removeMessageJobs(Set<MessageJob> messageJobs) {
		for (MessageJob messageJob : messageJobs) {
			messageJob.remove();
			ScheduleUtil.deleteJob("job" + messageJob.getId(), "group");
		}
	}
	
	public List<MessageJob> findAllMessageJob() {
		return MessageJob.findAll(MessageJob.class);
	}

	@Override
	public InvokeResult updateMessageJobRols(Long messageJobId, Long[] idArrs) {
		MessageJob messageJob = this.getMessageJob(messageJobId);
		for (Long id: idArrs) {
			Authority  authority =Authority.getBy(id);
			messageJob.getAuthorityList().add(authority);
		}

		this.updateMessageJob(messageJob);
		return InvokeResult.success();
	}

	@Override
	public InvokeResult updateMessageRemoveJobRols(Long messageJobId, Long[] idArrs) {
		MessageJob messageJob = this.getMessageJob(messageJobId);
		for (Long id: idArrs) {
			Authority  authority =Authority.getBy(id);
			messageJob.getAuthorityList().remove(authority);
		}

		this.updateMessageJob(messageJob);
		return InvokeResult.success();
	}

}
