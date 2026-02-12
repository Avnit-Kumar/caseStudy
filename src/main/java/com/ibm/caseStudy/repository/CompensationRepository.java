package com.ibm.caseStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.caseStudy.model.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {

}
