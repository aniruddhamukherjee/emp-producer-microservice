package com.demo.controllers;

import com.demo.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EmployeeProducerController {

    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getDataFallBack")
    public Employee firstPage(@PathVariable String empId) {

        Employee emp = new Employee();
        emp.setName("Aniruddha Mukherjee");
        emp.setDesignation("Data Engineering " +
                "Lead");
        emp.setEmpId(empId);
        emp.setBusinessUnit("UBM-Informa");

        Integer.parseInt(empId) ; //fallback

        return emp;
    }

    public Employee getDataFallBack(String empId) {

        Employee emp = new Employee();
        emp.setName("fallback-employee-name");
        emp.setDesignation("fallback-employee-designation");
        emp.setEmpId("fallback-emp-id");
        emp.setBusinessUnit("fallback-BU");

        return emp;

    }

    @RequestMapping(value = "/healthCheck",
            method = RequestMethod.GET)

    public String healthCheck() {

        return "Success";
    }

}
