package com.PatientMonitoringPlatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name="patient")
@AllArgsConstructor
@NoArgsConstructor

public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String status;
}
