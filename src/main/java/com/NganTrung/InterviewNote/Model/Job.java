package com.NganTrung.InterviewNote.Model;

import com.NganTrung.InterviewNote.DTO.JobInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "description")
    private String description;

    @Column(name = "interviewTime")
    private LocalDateTime interviewTime;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Job(JobInfo jobInfo) {
        this.company = new Company();
        this.id = jobInfo.getId();
        this.name = jobInfo.getName();
        this.requirement = jobInfo.getRequirement();
        this.description = jobInfo.getDescription();
        this.interviewTime = jobInfo.getJobUpdate();
        this.company.setId(jobInfo.getCompanyId());
    }

    public Job() {}

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

    public LocalDateTime getInterviewTimeTime() {
        return interviewTime;
    }

    public void setInterviewTimeTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
