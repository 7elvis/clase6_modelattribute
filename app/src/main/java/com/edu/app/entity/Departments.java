package com.edu.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "department_name")
    private String departmentname;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id",
            insertable = false, updatable = false)
    private Employees manager;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Locations locationid;

    @Override
    public String toString() {
        return "Departments{" + "departmentId=" + departmentId + ", departmentname=" + departmentname + ", manager=" + manager + ", locationid=" + locationid + '}';
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Employees getManager() {
        return manager;
    }

    public void setManager(Employees manager) {
        this.manager = manager;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public Locations getLocationid() {
        return locationid;
    }

    public void setLocationid(Locations locationid) {
        this.locationid = locationid;
    }
}
