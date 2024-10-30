package ru.sakhdanil.TechRadar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.Mapping;


@Repository
public interface MappingRepository extends JpaRepository<Mapping, Long> {
}
