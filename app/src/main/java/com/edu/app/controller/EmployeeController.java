package com.edu.app.controller;

import com.edu.app.entity.Employees;
import com.edu.app.repository.DepartmentsRepository;
import com.edu.app.repository.EmployeesRepository;
import com.edu.app.repository.JobsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"", "/"})
    public String listaEmployee(Model model) {
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "/employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees") Employees employees, Model model) {
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/Frm";
    }


    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employees") @Valid Employees employees,
            BindingResult bindingResult,
            RedirectAttributes attr,
            @RequestParam(name = "fechaContrato", required = false) String fechaContrato, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        } else {

            if (employees.getEmployeeid() == 0) {
                try {
                    employees.setHireDate(new Date());
                    employeesRepository.save(employees);
                    attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                    return "redirect:/employee";
                } catch (Exception ex) {
                    model.addAttribute("listaJobs", jobsRepository.findAll());
                    model.addAttribute("listaJefes", employeesRepository.findAll());
                    model.addAttribute("listaDepartments", departmentsRepository.findAll());
                    model.addAttribute("msg", ex.getMessage());
                    ex.printStackTrace();
                    return "employee/Frm";
                }
            } else {

                try {
                    if (fechaContrato != null) {
                        employees.setHireDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaContrato));
                    } else {
                        Employees employeeAux = employeesRepository.findById(employees.getEmployeeid()).orElse(null);
                        if (employeeAux != null) {
                            employees.setHireDate(employeeAux.getHireDate());
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    employeesRepository.save(employees);
                    attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
                } catch (Exception ex) {
                    model.addAttribute("listaJobs", jobsRepository.findAll());
                    model.addAttribute("listaJefes", employeesRepository.findAll());
                    model.addAttribute("listaDepartments", departmentsRepository.findAll());
                    model.addAttribute("msg", ex.getMessage());
                    ex.printStackTrace();
                    return "employee/Frm";
                }
                return "redirect:/employee";
            }
        }
    }

    @GetMapping("/edit")
    public String editarEmployee(@RequestParam("id") int id, @ModelAttribute("employees") Employees employees, Model model) {
        Employees employee = employeesRepository.findById(id).orElse(null);

        if (employee != null) {
            model.addAttribute("employees", employee);
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        }
        return "redirect:/employee";
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
            @RequestParam("id") int id,
            RedirectAttributes attr) {

        try {
            Optional<Employees> optEmployees = employeesRepository.findById(id);

            if (optEmployees.isPresent()) {
                employeesRepository.deleteById(id);
                attr.addFlashAttribute("msg", "Empleado borrado exitosamente");
            }
        } catch (Exception ex) {
            attr.addFlashAttribute("msg", "No se pudo borrar empleado: " + ex.getMessage());
        }
        return "redirect:/employee";

    }

    @PostMapping("/search")
    public String buscar(@RequestParam("s") String s, Model model) {
        if (s == null || s.equals("")) {
            model.addAttribute("listaEmployee", employeesRepository.findAll());
        } else {
            model.addAttribute("listaEmployee", employeesRepository.Search(s));
        }
        model.addAttribute("s", s);
        return "/employee/lista";
    }

}
