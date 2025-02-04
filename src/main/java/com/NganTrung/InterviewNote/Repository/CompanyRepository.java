package com.NganTrung.InterviewNote.Repository;

import com.NganTrung.InterviewNote.Model.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findByName(String name);

    List<Company> findByNameContaining(String keyword);

    @Query("SELECT COUNT(j) FROM Job j WHERE j.company.name LIKE %:keyword%")
    Integer countJobsByCompanyName(String keyword);

    @Query("SELECT COUNT(j) FROM Job j WHERE j.company.id = :id")
    Integer countJobsByCompanyId(Integer id);
}
