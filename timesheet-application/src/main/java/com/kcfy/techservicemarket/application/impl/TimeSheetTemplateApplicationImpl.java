package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.TimeSheetTemplateApplication;
import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplate;
import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplateDetail;
import com.kcfy.techservicemarket.facade.dto.TimeSheetDetailTemplateDTO;
import org.dayatang.domain.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daidong on 16/6/27.
 */
@Named
@Transactional
public class TimeSheetTemplateApplicationImpl implements TimeSheetTemplateApplication {
    @Override
    public TimeSheetTemplate getTimeSheetDefaultTemplate(Long userid) {

        List<TimeSheetTemplate> list = TimeSheetTemplate.getRepository().createCriteriaQuery(TimeSheetTemplate.class)
                .eq("user.id", userid).eq("status", 1).list();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<TimeSheetTemplate> getTimeSheetTemplateList(Long userId) {
        List<TimeSheetTemplate> list = TimeSheetTemplate.getRepository().createCriteriaQuery(TimeSheetTemplate.class)
                .eq("user.id", userId).list();
        return list.size() > 0 ? list : null;
    }

    @Override
    public TimeSheetTemplate getTimeSheetDefaultTemplateDetails(Long id) {
        List<TimeSheetTemplate> list = TimeSheetTemplate.getRepository().createCriteriaQuery(TimeSheetTemplate.class)
                .eq("id", id).list();
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public TimeSheetTemplateDetail getTemplateDetailById(Long id) {
        TimeSheetTemplateDetail object = TimeSheetTemplateDetail.get(TimeSheetTemplateDetail.class, id);
        return object;
    }

    @Override
    public void deleteDetails(List<TimeSheetTemplateDetail> detailsOldForDelete) {
        for(TimeSheetTemplateDetail detail : detailsOldForDelete) {
            detail.remove();
        }
    }

    @Override
    public void save(TimeSheetTemplate timeSheetTemplate) {
        timeSheetTemplate.save();
    }

    @Override
    public void update(TimeSheetTemplate timeSheetTemplate) {
        timeSheetTemplate.save();
    }

    //     return 1 = error
    @Override
    public int setDefault(Long id, Long userId) {
        List<TimeSheetTemplate> timeSheetTemplates = this.getTimeSheetTemplateList(userId);
        if (timeSheetTemplates != null) {
            //多条模板记录,先全部设为飞默认0
            if (timeSheetTemplates.size() > 0) {
                for (TimeSheetTemplate t : timeSheetTemplates) {
                    t.setStatus(0);
                    this.update(t);
                }
                TimeSheetTemplate timeSheetTemplate = this.getTimeSheetDefaultTemplateDetails(id);
                if (timeSheetTemplate != null) {
                    timeSheetTemplate.setStatus(1);
                    this.update(timeSheetTemplate);
                } else {
                    //模板ID有误
                    return 1;
                }
            }
        } else {
            //用户ID有误
                return 1;
            }
        return 0;
    }

    @Override
    public int deleteTemplateById(Long id) {
        TimeSheetTemplate timeSheetTemplate = this.getTimeSheetDefaultTemplateDetails(id);
        if (timeSheetTemplate != null) {
            timeSheetTemplate.remove();
        }else{
            return 1;
        }
        return 0;
    }
}
