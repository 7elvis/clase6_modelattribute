package com.edu.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Regions {
    @Id
    @Column(name = "region_id")
    private int regionId;

    @Column(name = "region_name")
    private String regionName;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}