package ru.sakhdanil.TechRadar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.Technology;


@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Technology findByTechId(Long techId);
}
