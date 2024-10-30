package ru.sakhdanil.TechRadar.models.poll;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class PollRequest {
    @Column(name = "tech_id", nullable = false)
    private Long techId;

    @Column(name = "ring_id", nullable = false)
    private Long ringId;
}
