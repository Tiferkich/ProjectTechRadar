package ru.sakhdanil.TechRadar.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakhdanil.TechRadar.models.Mapping;
import ru.sakhdanil.TechRadar.repository.FunctionRepository;
import ru.sakhdanil.TechRadar.repository.MappingRepository;
import ru.sakhdanil.TechRadar.repository.RolesRepository;

import java.util.List;

@Data
@Service
public class MappingService {
    @Autowired
    private MappingRepository mappingRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private FunctionRepository functionRepository;


    public List<Mapping> getMappings() {
        return mappingRepository.findAll();
    }

}
