package ru.sakhdanil.TechRadar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sakhdanil.TechRadar.models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
}
