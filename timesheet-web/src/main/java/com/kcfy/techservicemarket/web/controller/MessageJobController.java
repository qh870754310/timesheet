package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.MessageJobFacade;
import com.kcfy.techservicemarket.facade.dto.AuthorityDTO;
import com.kcfy.techservicemarket.facade.dto.MessageJobDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.facade.dto.RoleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/MessageJob")
public class MessageJobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageJobController.class);
    @Inject
    private MessageJobFacade messageJobFacade;

    @ResponseBody
    @RequestMapping("/add")
    public InvokeResult add(MessageJobDTO messageJobDTO) {
        return messageJobFacade.creatMessageJob(messageJobDTO);
    }

    @ResponseBody
    @RequestMapping("/update")
    public InvokeResult update(MessageJobDTO messageJobDTO) {
        return messageJobFacade.updateMessageJob(messageJobDTO);
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(MessageJobDTO messageJobDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<MessageJobDTO> all = messageJobFacade.pageQueryMessageJob(messageJobDTO, page, pagesize);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public InvokeResult remove(@RequestParam String ids) {
        String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        return messageJobFacade.removeMessageJobs(idArrs);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public InvokeResult get(@PathVariable Long id) {
        return messageJobFacade.getMessageJob(id);
    }

    @ResponseBody
    @RequestMapping("/findMessageTemplateByMessageJob/{id}")
    public Map<String, Object> findMessageTemplateByMessageJob(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", messageJobFacade.findMessageTemplateByMessageJob(id));
        return result;
    }


    @ResponseBody
    @RequestMapping("/findAuthorityListByMessageJob/{id}")
    public Page findAuthorityListByMessageJob(@PathVariable Long id, @RequestParam int page, @RequestParam int pagesize) {
        Page<AuthorityDTO> all = messageJobFacade.findAuthorityListByMessageJob(id, page, pagesize);
        return all;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }

    @ResponseBody
    @RequestMapping("/setMessageJobRole")
    public InvokeResult setMessageJobRole(@RequestParam Long messageJobId, @RequestParam String authorityIds) {
        LOGGER.info(messageJobId.toString());
        LOGGER.info(authorityIds);
        String[] value = authorityIds.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        InvokeResult invokeResult = this.messageJobFacade.updateMessageJobRols(messageJobId, idArrs);
        return invokeResult;
    }
    @ResponseBody
    @RequestMapping("/unSetMessageJobRole")
    public InvokeResult unSetMessageJobRole(@RequestParam Long messageJobId, @RequestParam String authorityIds) {
        LOGGER.info(messageJobId.toString());
        LOGGER.info(authorityIds);
        String[] value = authorityIds.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
       InvokeResult invokeResult = this.messageJobFacade.updateMessageRemoveJobRols(messageJobId, idArrs);
        return invokeResult;
    }

    @ResponseBody
    @RequestMapping(value = "/pagingQueryNotGrantRoles", method = RequestMethod.POST)
    public Page<RoleDTO> pagingQueryNotGrantRoles(int page, int pagesize, Long messageJobId) {
        Page<RoleDTO> pageList = messageJobFacade.pagingQueryNotGrantRoles(page, pagesize, messageJobId);
        return pageList;
    }
    @ResponseBody
    @RequestMapping(value = "/pagingQueryGrantRoleByMessageJobId", method = RequestMethod.POST)
    public Page<RoleDTO> pagingQueryGrantRoleByMessageJobId(int page, int pagesize, Long messageJobId) {
        Page<RoleDTO> pageList = messageJobFacade.pagingQueryGrantRoles(page, pagesize, messageJobId);
        return pageList;
    }

}