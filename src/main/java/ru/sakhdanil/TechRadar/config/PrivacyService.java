package ru.sakhdanil.TechRadar.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;
import ru.sakhdanil.TechRadar.service.FunctionService;
import ru.sakhdanil.TechRadar.service.MappingService;
import ru.sakhdanil.TechRadar.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sakhdanil.TechRadar.models.Mapping;

import org.springframework.http.HttpMethod;

import java.lang.reflect.Method;
import java.util.*;


@Data
@Component
public class PrivacyService {
    @Autowired
    private final FunctionService functionService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MappingService mappingService;

    @Autowired
    private RoleService roleService;


    public Map<privacyEndPoint, List<String>> getEndPointRoleName() {
        var controllers = applicationContext.getBeansWithAnnotation(RestController.class).values();
        Map<Method, String> methodsWithFuncName = new HashMap<>();
        Map<Method, String> methodsWithoutFuncName = new HashMap<>();

        for (var controller : controllers) {

            String endPointName;
            if (controller.getClass().isAnnotationPresent(RequestMapping.class))
                endPointName = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
            else {
                endPointName = "";
            }

            Arrays.stream(controller.getClass()
                            .getMethods())
                    .filter(method -> method.isAnnotationPresent(FuncName.class))
                    .forEach(method ->
                            methodsWithFuncName.put(method, endPointName));
            Arrays.stream(controller.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                    .forEach(method ->
                            methodsWithoutFuncName.put(method, endPointName));
        }

        Map<privacyEndPoint, String> nameEndPoints = new HashMap<>();

        for (var method : methodsWithFuncName.entrySet()) {
            if (method.getKey().isAnnotationPresent(GetMapping.class)) {
                nameEndPoints.put(new privacyEndPoint(method.getValue() + method.getKey().getAnnotation(GetMapping.class).value()[0], HttpMethod.GET), method.getKey().getAnnotation(FuncName.class).name());
            }
            if (method.getKey().isAnnotationPresent(PostMapping.class)) {
                nameEndPoints.put(new privacyEndPoint(method.getValue() + method.getKey().getAnnotation(PostMapping.class).value()[0], HttpMethod.POST), method.getKey().getAnnotation(FuncName.class).name());
            }
        }

        var allFunctions = functionService.allFunctions();
        Map<privacyEndPoint, Long> idEndPoints = new HashMap<>();
        for (var nameEndPoint : nameEndPoints.entrySet()) {
            idEndPoints.put(nameEndPoint.getKey(), allFunctions.get(nameEndPoint.getValue()));
        }
        var allMappings = mappingService.getMappings();

        Map<privacyEndPoint, List<Long>> endPointRoleId = new HashMap<>();

        for (var val : idEndPoints.entrySet()) {
            List<Long> roleId = allMappings.stream().filter(f -> f.getFuncId().equals(val.getValue())).map(Mapping::getRoleId).toList();
            if (endPointRoleId.containsKey(val.getKey())) {
                endPointRoleId.get(val.getKey()).addAll(roleId);
            } else {
                endPointRoleId.put(val.getKey(), roleId);
            }
        }

        Map<privacyEndPoint, List<String>> endPointRoleName = new HashMap<>();
        for (var endPointId : endPointRoleId.entrySet()) {
            List<Long> rolesId = endPointId.getValue();
            List<String> roleNames = new ArrayList<>();
            for (var role : rolesId) {
                roleNames.add(RoleService.getAllRoles().get(role));
            }
            endPointRoleName.put(endPointId.getKey(), roleNames);
        }
        return endPointRoleName;
    }
}