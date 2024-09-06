package com.gaurang.ByteMonk_Assignment.repository;

import com.gaurang.ByteMonk_Assignment.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {


    List<Incident> findBySeverityAndIncidentDateBetween(String severity, Date startDate, Date endDate);

    List<Incident> findByIncidentDateBetween(Date startDate, Date endDate);

    List<Incident> findBySeverity(String severity);
}
