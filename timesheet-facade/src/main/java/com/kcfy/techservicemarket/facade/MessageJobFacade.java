package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.AuthorityDTO;
import com.kcfy.techservicemarket.facade.dto.MessageJobDTO;
import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.facade.dto.RoleDTO;

import java.util.List;

public interface MessageJobFacade {

    public InvokeResult getMessageJob(Long id);

    public InvokeResult creatMessageJob(MessageJobDTO messageJob);

    public InvokeResult updateMessageJob(MessageJobDTO messageJob);

    public InvokeResult removeMessageJob(Long id);

    public InvokeResult removeMessageJobs(Long[] ids);

    public List<MessageJobDTO> findAllMessageJob();

    public Page<MessageJobDTO> pageQueryMessageJob(MessageJobDTO messageJob, int currentPage, int pageSize);

    public MessageTemplateDTO findMessageTemplateByMessageJob(Long id);


    public Page<AuthorityDTO> findAuthorityListByMessageJob(Long id, int currentPage, int pageSize);

    public  InvokeResult updateMessageJobRols(Long messageJobId, Long[] idArrs);

    public Page<RoleDTO> pagingQueryNotGrantRoles(int page, int pagesize, Long messageJobId);

    public Page<RoleDTO> pagingQueryGrantRoles(int page, int pagesize, Long messageJobId);

    public InvokeResult updateMessageRemoveJobRols(Long messageJobId, Long[] idArrs);
}

