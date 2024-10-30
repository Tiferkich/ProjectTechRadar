package ru.sakhdanil.TechRadar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.poll.Poll;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    Poll findByTechIdAndUserId(Long techId, Long userId);

    @Query("SELECT p.ringId, COUNT(p) FROM Poll p WHERE p.techId = ?1 GROUP BY p.ringId")
    List<Long[]> countVotesByTechId(Long technologyId);

}
