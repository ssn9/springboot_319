package com.aaa.controller;

import com.aaa.entity.Employees;
import com.aaa.service.EmployeesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafController {

    @Resource
    EmployeesService employeesService;

    @RequestMapping("selectPage")
    public String selectPage(Model model){
        List<Employees> employeesList = employeesService.selectAll();
        model.addAttribute("list",employeesList);
        model.addAttribute("lastName","段硕");
        return "success";
    }

}
