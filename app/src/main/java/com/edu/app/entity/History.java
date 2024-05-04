package com.edu.app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employeeid;
    @Column(name = "start_date")
    private Date hiredate;
    @Column(name = "end_date", nullable = false)
    private Date enddate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs jobid;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments departmentid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employees getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Employees employeeid) {
        this.employeeid = employeeid;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Jobs getJobid() {
        return jobid;
    }

    public void setJobid(Jobs jobid) {
        this.jobid = jobid;
    }

    public Departments getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Departments departmentid) {
        this.departmentid = departmentid;
    }

}
