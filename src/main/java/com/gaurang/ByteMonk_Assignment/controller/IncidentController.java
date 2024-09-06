package com.gaurang.ByteMonk_Assignment.controller;

import com.gaurang.ByteMonk_Assignment.model.Incident;
import com.gaurang.ByteMonk_Assignment.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class IncidentController {


    private final IncidentRepository incidentRepository;

    public IncidentController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping("/getIncident")
    public ResponseEntity<List<Incident>> getAllIncidents(
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
            ) {
        System.out.println("Received severity: " + severity);
        try {
            List<Incident> incidentList;
            if (severity != null && startDate != null && endDate != null) {
                incidentList = incidentRepository.findBySeverityAndIncidentDateBetween(severity, startDate, endDate);
            } else if (severity != null && startDate == null && endDate == null) {
                incidentList = incidentRepository.findBySeverity(severity);
            } else if (startDate != null && endDate != null) {
                incidentList = incidentRepository.findByIncidentDateBetween(startDate, endDate);
            } else {
                incidentList = incidentRepository.findAll();
            }

            if (incidentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(incidentList, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIncident/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        if (incident.isPresent()) {
            return new ResponseEntity<>(incident.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addIncident")
    public ResponseEntity<Incident> addIncident(@RequestBody Incident incident) {
        LocalDate today = LocalDate.now();
        LocalDate incidentLocalDate = incident.getIncidentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Check if the incidentDate is more than 30 days in the past
        if (incidentLocalDate.isBefore(today.minusDays(30))) {
            throw new IllegalArgumentException("Incident date cannot be more than 30 days in the past.");
        }

        // Check if the incidentDate is in the future
        if (incidentLocalDate.isAfter(today)) {
            throw new IllegalArgumentException("Incident date cannot be in the future.");
        }

        Incident obj = incidentRepository.save(incident);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping("/updateIncident/{id}")
    public ResponseEntity<Incident> updateIncidentById(@PathVariable Long id, @RequestBody Incident newIncident) {
        Optional<Incident> incident = incidentRepository.findById(id);
        if (incident.isPresent()) {
            Incident obj = incident.get();
            obj.setTitle(newIncident.getTitle());
            obj.setDescription(newIncident.getTitle());
            obj.setSeverity(newIncident.getSeverity());
            Incident newObj = incidentRepository.save(obj);
            return new ResponseEntity<>(newObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
