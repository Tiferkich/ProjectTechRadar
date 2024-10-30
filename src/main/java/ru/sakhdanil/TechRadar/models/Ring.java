package ru.sakhdanil.TechRadar.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rings")
@Data
public class Ring {

    @Id
    @GeneratedValue
    private Long ring_id;

    private String ring_name;

}
