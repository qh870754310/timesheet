package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.TimesheetTemplateSaveWrapDTO;
import org.openkoala.koala.commons.InvokeResult;

/**
 * Created by daidong on 16/6/27.
 */
public interface  TimeSheetTemplateFacade {

    public InvokeResult getDefaultTemplate(Long userId);

    public InvokeResult getTemplateList(Long userId);

    public InvokeResult getTemplateById(Long id);

    public InvokeResult getTemplateDetailById(Long id);

    public InvokeResult setDefault(Long id, Long userId);

    InvokeResult deleteTemplateById(Long id);

    InvokeResult update(TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO);

    InvokeResult create(TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO);
}

