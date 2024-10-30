package ru.sakhdanil.TechRadar.models.poll;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "polls")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    @Id
    @GeneratedValue
    @Column(name = "poll_id", nullable = false, unique = true)
    private Long pollId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "tech_id", nullable = false)
    private Long techId;

    @Column(name = "ring_id", nullable = false)
    private Long ringId;

    @Temporal(TemporalType.TIMESTAMP)
    private String time;
}
