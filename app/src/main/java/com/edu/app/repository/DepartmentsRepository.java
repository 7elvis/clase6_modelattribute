package com.edu.app.repository;

import com.edu.app.dto.SueldoPromedioPorDepartamentoDTO;
import com.edu.app.entity.Departments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

    @Query(value = "select d.department_id as departamentId, \n"
            + "department_name as departamento, \n"
            + "AVG(salary) as sueldo\n"
            + "from departments d inner join employees e on e.department_id = d.department_id\n"
            + "group by d.department_id , department_name \n"
            + "order by sueldo desc", nativeQuery = true)
    List<SueldoPromedioPorDepartamentoDTO> obtenerSueldoPromedioPorDepartamento();

}
