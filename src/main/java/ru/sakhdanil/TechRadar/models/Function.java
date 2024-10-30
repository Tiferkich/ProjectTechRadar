package ru.sakhdanil.TechRadar.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "func_id", unique = true, nullable = false)
    private Long funcId;

    @Column(name = "func_name", unique = true, nullable = false)
    private String funcName;
}
