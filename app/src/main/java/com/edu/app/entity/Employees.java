package com.edu.app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeid;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 1, message = "No puede estar vacío")
    @Column(name = "first_name")
    private String firstname;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 1, message = "No puede estar vacío")
    @Column(name = "last_name")
    private String lastname;

    @NotBlank(message = "El correo no puede estar en blanco")
    @Size(min = 1, message = "El correo no puede estar vacío")
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Date hireDate;

    @NotNull(message = "No puede estar en blanco")
    @Positive(message = "El salario debe ser mayor a 0")
    @Column(name = "salary")
    private double salary;

    @Column(name = "commission_pct")
    private BigDecimal commissionPct;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs jobid;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Departments departmentid;

    @ManyToOne
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private Employees managerid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Jobs getJobid() {
        return jobid;
    }

    public void setJobid(Jobs jobid) {
        this.jobid = jobid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public Departments getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Departments departmentid) {
        this.departmentid = departmentid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employees getManagerid() {
        return managerid;
    }

    public void setManagerid(Employees managerid) {
        this.managerid = managerid;
    }

}
