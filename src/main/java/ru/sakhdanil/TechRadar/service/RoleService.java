package ru.sakhdanil.TechRadar.service;


import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakhdanil.TechRadar.repository.RolesRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
@Data
public class RoleService {
    @Autowired
    RolesRepository rolesRepository;

    @Getter
    private static Map<Long, String> allRoles;

    @PostConstruct
    public void init() {
        Map<Long, String> modifiableMap = new HashMap<>();
        rolesRepository.findAll().forEach(role -> modifiableMap.put(role.getId(), role.getRole()));

        // Создаем неизменяемый вид на полученную карту
        allRoles = Collections.unmodifiableMap(modifiableMap);
    }

}
