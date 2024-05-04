package com.edu.app.repository;

import com.edu.app.entity.Employees;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query(value = "select p from Employees p "
            + " where p.firstname like %:s% "
            + " or p.lastname  like %:s% "
            + " or p.jobid.jobtitle like %:s% "
            + " or p.departmentid.locationid.city like %:s% "
            + " or p.departmentid.departmentname like %:s%")
    public List<Employees> Search(@Param("s") String s);

    @Query("SELECT e FROM Employees e "
            + "WHERE e.departmentid.departmentId = :departmentId "
            + "ORDER BY e.salary DESC")
    public List<Employees> findByDepartmentId(@Param("departmentId") int departmentId);
}
