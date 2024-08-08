package com.springboot.crud.springBootCRUDApp.controller;


import com.springboot.crud.springBootCRUDApp.models.Employee;
import com.springboot.crud.springBootCRUDApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/")
    public String index (Model model){
        model.addAttribute("emp",new Employee());

        return "AddEmployee";
    }

    @GetMapping({ "/viewEmployees"})
    public String viewEmployees(@ModelAttribute("message") String message, Model model){
        List<Employee> employeeList = employeeService.getAllEmployees();

        model.addAttribute("empList",employeeList);
        model.addAttribute("message", message);

        return "ViewEmployee";

    }

    @GetMapping("/addEmployee")
    public String newEmployees(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("emp", new Employee());
        model.addAttribute("message", message);

        return "AddEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        if (employeeService.saveOrUpdateEmployee(employee)){
            redirectAttributes.addFlashAttribute("message", "save success");
            return "redirect:/viewEmployees";
        }
        redirectAttributes.addFlashAttribute("massage", "save failed");
        return "redirect:/addEmployee";

    }


    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable Integer id, String message,Model model) {
        Employee employee = employeeService.getEmployeeId(id);
        model.addAttribute("emp", employee);
        model.addAttribute("message", message);

        return "editEmployee";

    }

    @PostMapping("editSaveEmployee")
    public String editSaveEmployee(Employee emp, RedirectAttributes redirectAttributes) {
        if (employeeService.saveOrUpdateEmployee(emp)){
            redirectAttributes.addFlashAttribute("message", "Edit success");
            return "redirect:/viewEmployees";
        }

        redirectAttributes.addFlashAttribute("message", "Edit failed");
        return "redirect:/editEmployees/" + emp.getId();

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (employeeService.deleteEmployee(id)){
            redirectAttributes.addFlashAttribute("massage", "delete success");
            return "redirect:/viewEmployees";
        }
        redirectAttributes.addFlashAttribute("massage", "delete failed");
        return "redirect:/viewEmployees";

    }

}
