package com.NganTrung.InterviewNote.Service;

import com.NganTrung.InterviewNote.Model.*;
import com.NganTrung.InterviewNote.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Optional<Company> getCompanyById(Integer id) {
        return companyRepo.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public Company updateCompany(Integer id, Company company) {
        if(company == null){
            return null;
        }
        Optional<Company> existingCompany = companyRepo.findById(id);
        if (!existingCompany.isPresent()) {
            return null;
        }
        Company updatedCompany = existingCompany.get();
        updatedCompany.setName(company.getName());
        updatedCompany.setAddress(company.getAddress());
        updatedCompany.setContact(company.getContact());
        updatedCompany.setDescription(company.getDescription());
        return companyRepo.save(updatedCompany);
    }

    public void deleteCompany(Integer id) {
        companyRepo.deleteById(id);
    }

    public void deleteAll() {
        companyRepo.deleteAll();
    }

    public List<Company> findCompaniesByName(String name) {
        return companyRepo.findByName(name);
    }

    public List<Company> searchCompanies(String keyword) {
        return companyRepo.findByNameContaining(keyword);
    }

    public Integer countJobsByCompanyName(String keyword) {
        return companyRepo.countJobsByCompanyName(keyword);
    }

    public Integer countJobsByCompanyId(Integer id) {
        return companyRepo.countJobsByCompanyId(id);
    }
}
