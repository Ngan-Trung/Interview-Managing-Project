package com.NganTrung.InterviewNote.Controller;

import com.NganTrung.InterviewNote.DTO.JobInfo;
import com.NganTrung.InterviewNote.Model.*;
import com.NganTrung.InterviewNote.Service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "My Controller for jobs", description = "For CRUD of Job data.")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Get a list of all the jobs")
    @GetMapping("/job/all")
    public List<JobInfo> getAllJobs() {
        return jobService.getAllJobs().stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a list of all the jobs and group by company.")
    @GetMapping("/job/groupByCompany")
    public List<JobInfo> getAllJobsGroupByCompany() {
        return jobService.getAllJobsGroupByCompany().stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get the job by id.")
    @GetMapping("/job/{id}")
    public ResponseEntity<JobInfo> getJobById(@Parameter(description = "Job's id") @PathVariable Integer id) {
        Optional<Job> job = jobService.getJobById(id);
        if(job.isPresent()){
            return ResponseEntity.ok(JobInfo.getJobInfo(job.get()));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(summary = "Create job by jobInfo(DTO)")
    @PostMapping("/job/create")
    public ResponseEntity<JobInfo> createJob(@Parameter(description = "jobInfo without company name") @RequestBody JobInfo jobInfo) {
        Optional<Company> company = companyService.getCompanyById(jobInfo.getCompanyId());
        if(!company.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Job job = new Job(jobInfo);
        Job createdJob = jobService.createJob(job);
        if (createdJob == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(JobInfo.getJobInfo(createdJob));
    }

    @Operation(summary = "Update job by jobInfo(DTO)")
    @PutMapping("/job/update")
    public ResponseEntity<JobInfo> updateJob(@Parameter(description = "jobInfo without company name") @RequestBody JobInfo jobInfo) {
        Job job = new Job(jobInfo);
        Job updatedJob = jobService.updateJob(job.getId(), job);
        if (updatedJob == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(JobInfo.getJobInfo(updatedJob));
    }

    @Operation(summary = "Delete job by id")
    @DeleteMapping("/job/delete/{id}")
    public ResponseEntity<Void> deleteJob(@Parameter(description = "Job's id") @PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all the jobs")
    @DeleteMapping("/job/deleteAll")
    public ResponseEntity<Void> deleteAllJob() {
        jobService.deleteAllJob();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find job by name")
    @GetMapping("/job/searchByName/{keyword}")
    public List<JobInfo> searchJobsByName(@Parameter(description = "Job's keyword") @PathVariable String keyword) {
        return jobService.searchJobsByName(keyword).stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find job by company name")
    @GetMapping("/job/searchByCompany/{keyword}")
    public List<JobInfo> getJobsByCompanyName(@Parameter(description = "company's keyword") @PathVariable String keyword) {
        return jobService.getJobsByCompanyName(keyword).stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find job by company id")
    @GetMapping("/job/searchByCompanyId/{id}")
    public List<JobInfo> getJobsByCompanyId(@Parameter(description = "company's id") @PathVariable Integer id) {
        return jobService.getJobsByCompanyId(id).stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find job by description")
    @GetMapping("/job/searchByDescription/{keyword}")
    public List<JobInfo> getJobsByDescription(@Parameter(description = "job's description") @PathVariable String keyword) {
        return jobService.getJobsByDescription(keyword).stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find job by requirement")
    @GetMapping("/job/searchByRequirement/{keyword}")
    public List<JobInfo> getJobsByRequirement(@Parameter(description = "job's requirement") @PathVariable String keyword) {
        return jobService.getJobsByRequirement(keyword).stream()
                .map(job -> JobInfo.getJobInfo(job))
                .collect(Collectors.toList());
    }
}
