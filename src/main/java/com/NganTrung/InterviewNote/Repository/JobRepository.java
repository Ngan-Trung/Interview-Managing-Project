package com.NganTrung.InterviewNote.Repository;

import com.NganTrung.InterviewNote.Model.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface JobRepository extends JpaRepository<Job,Integer> {
    boolean existsById(Integer id);

    List<Job> findByNameContaining(String keyword);

    List<Job> findByCompanyId(Integer companyId);

    List<Job> findByCompanyNameContaining(String keyword);

    List<Job> findByDescriptionContaining(String keyword);

    List<Job> findByRequirementContaining(String keyword);

    @Query("SELECT j FROM Job j JOIN j.company c ORDER BY c.name")
    List<Job> findAllGroupByCompany();
}
