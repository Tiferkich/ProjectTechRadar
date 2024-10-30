package ru.sakhdanil.TechRadar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false, unique = true)
    private Long id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String role;
}
