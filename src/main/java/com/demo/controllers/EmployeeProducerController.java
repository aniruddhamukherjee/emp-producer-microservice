package com.demo.controllers;

import com.demo.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeProducerController {

    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getDataFallBack")
    public Employee firstPage(@PathVariable String empId) {

        Employee emp = new Employee();
        emp.setName("Aniruddha");
        emp.setDesignation("Senior Developer");
        emp.setEmpId(empId);
        emp.setBusinessUnit("CMI-Telecom");

        if("5".equals(empId)) {    // fallback
            throw new RuntimeException();
        }

        return emp;
    }

    public Employee getDataFallBack(String empId) {

        Employee emp = new Employee();
        emp.setName("fallback-emp1");
        emp.setDesignation("fallback-developer");
        emp.setEmpId("fallback-1");
        emp.setBusinessUnit("fallback-BU");

        return emp;

    }

    @RequestMapping(value = "/healthCheck",
            method = RequestMethod.GET)

    public String healthCheck()  {

        return "Success";
    }

}
