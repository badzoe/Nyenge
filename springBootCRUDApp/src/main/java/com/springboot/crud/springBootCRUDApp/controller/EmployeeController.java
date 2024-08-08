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

import static com.springboot.crud.springBootCRUDApp.consts.ApiURL.*;
import static com.springboot.crud.springBootCRUDApp.consts.Constants.*;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/")
    public String index (Model model){
        model.addAttribute(EMP,new Employee());
        return "AddEmployee";
    }

    @GetMapping({ VIEW_EMPLOYEES})
    public String viewEmployees(@ModelAttribute(MESSAGE) String message, Model model){
        List<Employee> employeeList = employeeService.getAllEmployees();

        model.addAttribute("empList",employeeList);
        model.addAttribute(MESSAGE, message);

        return "ViewEmployee";

    }

    @GetMapping(ADD_EMPLOYEE)
    public String newEmployees(@ModelAttribute(MESSAGE) String message, Model model) {
        model.addAttribute(EMP, new Employee());
        model.addAttribute(MESSAGE, message);

        return "AddEmployee";
    }

    @PostMapping(SAVE_EMPLOYEE)
    public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        if (employeeService.saveOrUpdateEmployee(employee)){
            redirectAttributes.addFlashAttribute(MESSAGE, "save success");
            return REDIRECT_VIEW_EMPLOYEES;
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "save failed");
        return REDIRECT_VIEW_EMPLOYEES;

    }


    @GetMapping(EDIT_EMPLOYEE)
    public String editEmployee(@PathVariable Integer id, String message,Model model) {
        Employee employee = employeeService.getEmployeeId(id);
        model.addAttribute(EMP, employee);
        model.addAttribute(MESSAGE, message);

        return "editEmployee";

    }

    @PostMapping(EDIT_SAVED_EMPLOYEES)
    public String editSaveEmployee(Employee emp, RedirectAttributes redirectAttributes) {
        if (employeeService.saveOrUpdateEmployee(emp)){
            redirectAttributes.addFlashAttribute(MESSAGE, "Edit success");
            return REDIRECT_VIEW_EMPLOYEES;
        }

        redirectAttributes.addFlashAttribute(MESSAGE, "Edit failed");
        return REDIRECT_VIEW_EMPLOYEES + emp.getId();

    }

    @GetMapping(DELETE_EMPLOYEE)
    public String deleteEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (employeeService.deleteEmployee(id)){
            redirectAttributes.addFlashAttribute(MESSAGE, "delete success");
            return REDIRECT_VIEW_EMPLOYEES;
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "delete failed");
        return REDIRECT_VIEW_EMPLOYEES;

    }

}
