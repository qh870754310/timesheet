package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplate;
import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplateDetail;

import java.util.List;

/**
 * Created by daidong on 16/6/27.
 */
public interface  TimeSheetTemplateApplication {
    public TimeSheetTemplate getTimeSheetDefaultTemplate(Long userId);
    public List <TimeSheetTemplate> getTimeSheetTemplateList(Long userId);

    public TimeSheetTemplate getTimeSheetDefaultTemplateDetails(Long id);

    public TimeSheetTemplateDetail getTemplateDetailById(Long id);
    public  void save(TimeSheetTemplate timeSheetTemplate);
    public  void update(TimeSheetTemplate timeSheetTemplate);

    int setDefault(Long id, Long userId);

    int deleteTemplateById(Long id);

    void deleteDetails(List<TimeSheetTemplateDetail> detailsOldForDelete);
}

