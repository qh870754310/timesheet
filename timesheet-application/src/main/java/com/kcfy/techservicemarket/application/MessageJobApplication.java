package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;
import java.util.Set;

public interface MessageJobApplication {

    public MessageJob getMessageJob(Long id);

    public void creatMessageJob(MessageJob messageJob);

    public void updateMessageJob(MessageJob messageJob);

    public void removeMessageJob(MessageJob messageJob);

    public void removeMessageJobs(Set<MessageJob> messageJobs);

    public List<MessageJob> findAllMessageJob();

    public InvokeResult updateMessageJobRols(Long messageJobId, Long[] idArrs);

    public  InvokeResult updateMessageRemoveJobRols(Long messageJobId, Long[] idArrs);
}

