package com.edu.app.repository;

import com.edu.app.entity.Employees;
import com.edu.app.entity.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

      @Query(value = "select p from History p "
            + " where p.employeeid.salary >:salario")
    public List<History> MayorSalario(@Param("salario") double salario);
}
