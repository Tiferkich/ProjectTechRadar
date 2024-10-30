package ru.sakhdanil.TechRadar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.Function;


@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {
}
