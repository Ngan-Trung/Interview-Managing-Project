package com.NganTrung.InterviewNote.DTO;

import com.NganTrung.InterviewNote.Model.Company;
import org.springframework.stereotype.Component;

import java.util.List;

public class CompanyInfo {
    private Integer id;

    private String name;

    private String address;

    private String contact;

    private String description;

    public CompanyInfo(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.contact = company.getContact();
        this.description = company.getDescription();
    }

    public CompanyInfo() {}

    public static CompanyInfo getCompanyInfo(Company company){
        CompanyInfo info = new CompanyInfo(company);
        return info;
    }

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
}
