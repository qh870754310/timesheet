package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.MessageJobApplication;
import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import com.kcfy.techservicemarket.facade.MessageJobFacade;
import com.kcfy.techservicemarket.facade.dto.AuthorityDTO;
import com.kcfy.techservicemarket.facade.dto.MessageJobDTO;
import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.MessageJobAssembler;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.facade.dto.RoleDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.util.*;

@Named
public class MessageJobFacadeImpl implements MessageJobFacade {

    @Inject
    private MessageJobApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getMessageJob(Long id) {
        return InvokeResult.success(MessageJobAssembler.toDTO(application.getMessageJob(id)));
    }

    public InvokeResult creatMessageJob(MessageJobDTO messageJobDTO) {
        application.creatMessageJob(MessageJobAssembler.toEntity(messageJobDTO));
        return InvokeResult.success();
    }

    public InvokeResult updateMessageJob(MessageJobDTO messageJobDTO) {
        application.updateMessageJob(MessageJobAssembler.toEntity(messageJobDTO));
        return InvokeResult.success();
    }

    public InvokeResult removeMessageJob(Long id) {
        application.removeMessageJob(application.getMessageJob(id));
        return InvokeResult.success();
    }

    public InvokeResult removeMessageJobs(Long[] ids) {
        Set<MessageJob> messageJobs = new HashSet<MessageJob>();
        for (Long id : ids) {
            messageJobs.add(application.getMessageJob(id));
        }
        application.removeMessageJobs(messageJobs);
        return InvokeResult.success();
    }

    public List<MessageJobDTO> findAllMessageJob() {
        return MessageJobAssembler.toDTOs(application.findAllMessageJob());
    }

    public Page<MessageJobDTO> pageQueryMessageJob(MessageJobDTO queryVo, int currentPage, int pageSize) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _messageJob from MessageJob _messageJob   where 1=1 ");
        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _messageJob.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }
        if (queryVo.getMemo() != null && !"".equals(queryVo.getMemo())) {
            jpql.append(" and _messageJob.memo like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMemo()));
        }
        if (queryVo.getCronExpression() != null && !"".equals(queryVo.getCronExpression())) {
            jpql.append(" and _messageJob.cronExpression like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCronExpression()));
        }
        if (queryVo.getStatus() != null) {
            jpql.append(" and _messageJob.status is ?");
            conditionVals.add(queryVo.getStatus());
        }
        Page<MessageJob> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<MessageJobDTO>(pages.getStart(), pages.getResultCount(), pageSize, MessageJobAssembler.toDTOs(pages.getData()));
    }

    @Override
    public MessageTemplateDTO findMessageTemplateByMessageJob(Long id) {
        return null;
    }

    @Override
    public Page<AuthorityDTO> findAuthorityListByMessageJob(Long id, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public InvokeResult updateMessageJobRols(Long messageJobId, Long[] idArrs) {
        return application.updateMessageJobRols(messageJobId,idArrs);
    }
    @Override
    public InvokeResult updateMessageRemoveJobRols(Long messageJobId, Long[] idArrs) {
        return application.updateMessageRemoveJobRols(messageJobId,idArrs);
    }
    @Override
    public Page<RoleDTO> pagingQueryGrantRoles(int page, int pagesize, Long messageJobId) {
        return getRoleDTOPage(page, pagesize, messageJobId, "IN ");
    }



    private Page<RoleDTO> getRoleDTOPage(int page, int pagesize, Long messageJobId, String str3) {
        if (messageJobId == null) {
            InvokeResult.failure("消息模板ID不能为空");
        }

        StringBuilder jpql = new StringBuilder("SELECT NEW org.openkoala.security.facade.dto.RoleDTO(_role.id, _role.name, _role.description)  FROM Role _role WHERE 1 = 1 ");
        jpql.append("And _role.id ");
        jpql.append(str3);
        jpql.append(" (SELECT _authority.id FROM MessageJob _messageJob JOIN _messageJob.authorityList _authority WHERE _messageJob.id= :messageJobId)");
        HashMap<String, Long> conditionVals = new HashMap<>();
        conditionVals.put("messageJobId", messageJobId);

        return getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(page, pagesize)
                .pagedList();
    }

    @Override
    public Page<RoleDTO> pagingQueryNotGrantRoles(int page, int pagesize , Long messageJobId) {
        return getRoleDTOPage(page, pagesize, messageJobId, "NOT IN ");
    }



}
