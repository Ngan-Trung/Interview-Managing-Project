package com.NganTrung.InterviewNote.DTO;

import com.NganTrung.InterviewNote.Model.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobInfo {
    public JobInfo(Job job){
        this.id = job.getId();
        this.name = job.getName();
        this.description = job.getDescription();
        this.requirement = job.getRequirement();
        this.interviewTime = job.getInterviewTimeTime();
        this.companyId = job.getCompany().getId();
        this.companyName = job.getCompany().getName();
    }

    public JobInfo(){}

    public static JobInfo getJobInfo(Job job){
        JobInfo jobInfo = new JobInfo(job);
        return jobInfo;
    }

    Integer id;

    private String name;

    private String requirement;

    private String description;

    private LocalDateTime interviewTime;

    private Integer companyId;

    private String companyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getJobUpdate() {
        return interviewTime;
    }

    public void setJobUpdate(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
