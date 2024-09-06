//package com.gaurang.ByteMonk_Assignment.services;
//
//import com.gaurang.ByteMonk_Assignment.model.Incident;
//import com.gaurang.ByteMonk_Assignment.repository.IncidentRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public abstract class IncidentService implements IncidentRepository {
//
//    private final IncidentRepository incidentRepository;
//
//    public IncidentService(IncidentRepository incidentRepository) {
//        this.incidentRepository = incidentRepository;
//    }
//
//    public List<Incident> findBySeverityAndIncidentDateBetween(String severity, Date startDate, Date endDate) {
//        List<Incident> allIncidents = incidentRepository.findAll();
//        List<Incident> list = new ArrayList<>();
//        for (Incident incident : allIncidents) {
//            if (incident.getSeverity().equals(severity)) {
//                Date db = incident.getIncident_date();
//                if (db.before(endDate) && db.after(startDate)) {
//                    list.add(incident);
//                }
//            }
//        }
//        return list;
//    }
//
//    public List<Incident> findByIncidentDateBetween(Date startDate, Date endDate) {
//        List<Incident> allIncidents = incidentRepository.findAll();
//        List<Incident> list = new ArrayList<>();
//        for (Incident incident : allIncidents) {
//            Date db = incident.getIncident_date();
//            if (db.before(endDate) && db.after(startDate)) {
//                list.add(incident);
//            }
//        }
//        return list;
//    }
//
//    public List<Incident> findBySeverity(String severity) {
//        List<Incident> allIncidents = incidentRepository.findAll();
//        List<Incident> list = new ArrayList<>();
//        for (Incident incident : allIncidents) {
//            if (incident.getSeverity().equals(severity)) {
//                list.add(incident);
//            }
//        }
//        return list;
//    }
//
//}
