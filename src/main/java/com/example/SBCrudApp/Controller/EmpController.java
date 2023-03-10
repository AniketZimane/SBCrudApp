package com.example.SBCrudApp.Controller;


import com.example.SBCrudApp.Entity.Employee;
import com.example.SBCrudApp.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpController
{
    @Autowired
    EmployeeRepo empRepo;
    @GetMapping("/")
    public String RegisterEmp()
    {
        return "EmpReg";
    }

    @GetMapping("/home")
    public String showData(String name,Model model)
    {
        List<Employee> emplist=empRepo.findAll();
        model.addAttribute("emplist",emplist);
        return "index";
    }


    @PostMapping("/emps/")//after saving data
    public String SubmitData(Model model,Employee emp)
    {
        empRepo.save(emp);
        List<Employee> emplist=empRepo.findAll();
        model.addAttribute("emplist",emplist);
        model.addAttribute("msg","Employee saved successfully");
        return "index";
    }

    @GetMapping("/emps/delete/{empID}/")
    public String SubmitData(Model model, @PathVariable long empID)
    {
        empRepo.deleteById(empID);
        List<Employee> emplist=empRepo.findAll();
        model.addAttribute("emplist",emplist);
        model.addAttribute("msg","Employee deleted successfully");
        return "index";
    }

}
