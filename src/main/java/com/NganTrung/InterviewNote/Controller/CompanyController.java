package com.NganTrung.InterviewNote.Controller;

import com.NganTrung.InterviewNote.DTO.CompanyInfo;
import com.NganTrung.InterviewNote.Model.Company;
import com.NganTrung.InterviewNote.Service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "My Controller for companies", description = "For CRUD of company data.")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Get a list of all the companies")
    @GetMapping("/company/all")
    public List<CompanyInfo> getAllCompanies() {
        return companyService.getAllCompanies().stream()
                .map(CompanyInfo::getCompanyInfo)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a company by id")
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyInfo> getCompanyById(@Parameter(description = "company's id") @PathVariable Integer id) {
        Optional<Company> existingCompany = companyService.getCompanyById(id);
        if(existingCompany.isPresent()){
            return ResponseEntity.ok(CompanyInfo.getCompanyInfo(existingCompany.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create company by companyInfo")
    @PostMapping("/company/create")
    public ResponseEntity<CompanyInfo> createCompany(@Parameter(description = "CompanyInfo") @RequestBody CompanyInfo companyInfo) {
        Company company = new Company(companyInfo);
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(CompanyInfo.getCompanyInfo(createdCompany));
    }

    @Operation(summary = "Update company by companyInfo")
    @PutMapping("/company/update")
    public ResponseEntity<CompanyInfo> updateCompany(@Parameter(description = "CompanyInfo") @RequestBody CompanyInfo companyInfo) {
        Company company = new Company(companyInfo);
        Company updatedCompany = companyService.updateCompany(company.getId(), company);
        if (updatedCompany != null) {
            return ResponseEntity.ok(CompanyInfo.getCompanyInfo(updatedCompany));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete company by id")
    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<CompanyInfo> deleteCompany(@Parameter(description = "company's id") @PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete all the companies")
    @DeleteMapping("/company/deleteAll")
    public ResponseEntity<CompanyInfo> deleteAllCompany() {
        companyService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Search company by name(partial)")
    @GetMapping("/company/searchByName/{keyword}")
    public List<CompanyInfo> searchCompanies(@Parameter(description = "company's name keyword") @PathVariable String keyword) {
        return companyService.searchCompanies(keyword).stream()
                .map(CompanyInfo::getCompanyInfo)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Search by exact name")
    @GetMapping("/company/name/{name}")
    public List<CompanyInfo> findCompaniesByName(@Parameter(description = "company's exact name") @PathVariable String name) {
        return companyService.findCompaniesByName(name).stream()
                .map(CompanyInfo::getCompanyInfo)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get job number by company name")
    @GetMapping("/company/countJobsByName/{keyword}")
    public Integer countJobsByCompanyName(@Parameter(description = "company's name keyword") @PathVariable String keyword) {
        return companyService.countJobsByCompanyName(keyword);
    }

    @Operation(summary = "Search company by job id")
    @GetMapping("/company/countJobsById/{id}")
    public Integer countJobsByCompanyId(@Parameter(description = "company's id") @PathVariable Integer id) {
        return companyService.countJobsByCompanyId(id);
    }
}