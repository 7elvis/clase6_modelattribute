package com.edu.app.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Countries {

    @Id
    @Column(name = "country_id", columnDefinition = "CHAR(2)")
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Regions region;

    @OneToMany(mappedBy = "country")
    private List<Locations> locations;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }
}
