package com.kcfy.techservicemarket.core.domain.timesheet;

import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.*;

/**
 * Created by zhouxc on 2016/6/24.
 */
@Entity
@Table(name = "t_timesheet_detail")
public class TimesheetDetail extends CustomKoalaAbstractEntity {
    @Column(name="hour")
    private Long hour;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name ="timesheet_id")
    private Timesheet timesheet;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name ="project_id")
    private Project project;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name ="project_stage_id")
    private ProjectStage projectStage;
    @Column(name="task_desc")
    private String taskDesc;
    @Column(name="status")
    private String status;
    @Column(name="rejected_reason")
    private String rejectedReason;
    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    @Override
    public String[] businessKeys() {
        return new String[0];
    }
}
