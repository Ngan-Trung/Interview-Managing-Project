package com.NganTrung.InterviewNote.Model;

import com.NganTrung.InterviewNote.DTO.CompanyInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    public Integer getId() {
        return id;
    }

    public Company(CompanyInfo companyInfo) {
        this.id = companyInfo.getId();
        this.name = companyInfo.getName();
        this.address = companyInfo.getAddress();
        this.contact = companyInfo.getContact();
        this.description = companyInfo.getDescription();
        this.jobs = new ArrayList<>();
    }

    public Company() {}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
