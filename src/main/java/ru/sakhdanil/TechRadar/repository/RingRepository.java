package ru.sakhdanil.TechRadar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.Ring;

@Repository
public interface RingRepository extends JpaRepository<Ring, Long> {
}
