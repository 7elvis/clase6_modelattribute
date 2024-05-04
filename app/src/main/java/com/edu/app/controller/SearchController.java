package com.edu.app.controller;

import com.edu.app.dto.SueldoPromedioPorDepartamentoDTO;
import com.edu.app.entity.Departments;
import com.edu.app.entity.Employees;
import com.edu.app.entity.History;
import com.edu.app.repository.DepartmentsRepository;
import com.edu.app.repository.EmployeesRepository;
import com.edu.app.repository.HistoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Search")
public class SearchController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private DepartmentsRepository departamentsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping(value = {"", "/"})
    public String indice() {
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio(Model model) {

        model.addAttribute("employees", new ArrayList<>());
        return "Search/lista2";
    }

    @PostMapping("/busqueda")
    public String buscar(Model model, @Param("salario") double salario) {
        List<History> lista = historyRepository.MayorSalario(salario);

        model.addAttribute("salario", salario);
        model.addAttribute("employees", lista);
        return "Search/lista2";
    }

    @GetMapping(value = "/Filtro2")
    public String cantidadEmpleadosPorPais(Model model) {
        List<SueldoPromedioPorDepartamentoDTO> lista = departamentsRepository.
                obtenerSueldoPromedioPorDepartamento();

        model.addAttribute("lista", lista);
        return "/Search/salario";
    }

    @GetMapping("/listar/{id}")
    public String listarEmpleadoDep(@PathVariable("id") int id, Model model) {
        List<Employees> lista = employeesRepository.findByDepartmentId(id);
        model.addAttribute("employees", lista);
        return "/Search/lista3";

    }

}
