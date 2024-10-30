package ru.sakhdanil.TechRadar.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mapping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "func_id", unique = true, nullable = false)
    private Long funcId;

    @Column(name = "role_id", unique = true, nullable = false)
    private Long roleId;
}
