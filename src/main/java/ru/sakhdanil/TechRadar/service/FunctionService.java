package ru.sakhdanil.TechRadar.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakhdanil.TechRadar.repository.FunctionRepository;

import java.util.HashMap;

@Service
@Data
public class FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    public HashMap<String, Long> allFunctions() {
        HashMap<String, Long> functions = new HashMap<>();
        var allFunList = functionRepository.findAll();
        allFunList.forEach(function -> functions.put(function.getFuncName(), function.getFuncId()));
        return functions;
    }

}
