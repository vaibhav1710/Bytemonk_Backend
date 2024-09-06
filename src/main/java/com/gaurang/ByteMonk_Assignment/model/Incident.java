package com.gaurang.ByteMonk_Assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Incidents")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title cannot be Null")
    @Size(min = 10, message = "Title must be at least 10 characters long")
    @Column(unique = true)
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Enter the Incident Date")
    @PastOrPresent(message = "Incident date cannot be in the future")
    private Date incidentDate;

    @NotNull(message = "Severity level cannot be null")
    @Enumerated(EnumType.STRING)
    private Level severity;

}
