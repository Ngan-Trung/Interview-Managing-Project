package com.NganTrung.InterviewNote.Service;

import com.NganTrung.InterviewNote.Model.*;
import com.NganTrung.InterviewNote.Repository.CompanyRepository;
import com.NganTrung.InterviewNote.Repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class JobService {
    @Autowired
    JobRepository jobRepo;

    public List<Job> getAllJobs(){
        return jobRepo.findAll();
    }

    public List<Job> getAllJobsGroupByCompany(){
        return jobRepo.findAllGroupByCompany();
    }

    public Optional<Job> getJobById(Integer id){
        return jobRepo.findById(id);
    }

    public Job createJob(Job job){
        if(job == null){
            return null;
        }
        jobRepo.save(job);
        return job;
    }

    public Job updateJob(Integer id, Job job){
        if(job == null){
            return null;
        }
        Optional<Job> isExistJob = jobRepo.findById(id);
        if(!isExistJob.isPresent()){
            return null;
        }
        Job existingJob = isExistJob.get();
        existingJob.setName(job.getName());
        existingJob.setRequirement(job.getRequirement());
        existingJob.setDescription(job.getDescription());
        existingJob.setInterviewTimeTime(job.getInterviewTimeTime());
        existingJob.setCompany(job.getCompany());
        jobRepo.save(job);
        return job;
    }

    public void deleteJob(Integer id){
        jobRepo.deleteById(id);
    }

    public void deleteAllJob(){
        jobRepo.deleteAll();
    }

    public List<Job> searchJobsByName(String keyword){
        return jobRepo.findByNameContaining(keyword);
    }

    public List<Job> getJobsByCompanyName(String keyword){
        return jobRepo.findByCompanyNameContaining(keyword);
    }

    public List<Job> getJobsByCompanyId(Integer id){
        return jobRepo.findByCompanyId(id);
    }

    public List<Job> getJobsByDescription(String keyword){
        return jobRepo.findByDescriptionContaining(keyword);
    }

    public List<Job> getJobsByRequirement(String keyword){
        return jobRepo.findByRequirementContaining(keyword);
    }
}
