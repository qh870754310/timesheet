package com.kcfy.techservicemarket.core.domain.timesheet;

import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.*;

/**
 * Created by daidong on 16/6/27.
 */
@Entity
@Table(name = "t_template_detail")
public class TimeSheetTemplateDetail extends CustomKoalaAbstractEntity {
    @Column(name = "hour")
    private long hour;
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch =FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private TimeSheetTemplate timeSheetTemplate;
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name ="project_id")
    private Project project;
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "project_stage_id")
    private ProjectStage projectStage;
    @Column(name = "task_desc")
    private String taskDesc;

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public TimeSheetTemplate getTimeSheetTemplate() {
        return timeSheetTemplate;
    }

    public void setTimeSheetTemplate(TimeSheetTemplate timeSheetTemplate) {
        this.timeSheetTemplate = timeSheetTemplate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectStage getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(ProjectStage projectStage) {
        this.projectStage = projectStage;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String[] businessKeys() {
        return null;
    }
}

