package ru.sakhdanil.TechRadar.models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "technologies")
@Data
public class Technology {

    @Id
    @GeneratedValue
    @Column(name = "tech_id", nullable = false, unique = true)
    private Long techId;

    @Column(name = "cat_id")
    private Long catId;

    @Column(name = "sec_id")
    private Long secId;

    @Column(name = "ring_id")
    private Long ringId;

    private String name;

    private String description;

    @Column(name = "stat_id")
    private Long statId;

    @Temporal(TemporalType.TIMESTAMP)
    private String time;

}
