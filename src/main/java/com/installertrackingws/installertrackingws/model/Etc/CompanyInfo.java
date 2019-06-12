package com.installertrackingws.installertrackingws.model.Etc;

import javax.persistence.*;

@Entity
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = true)
    public double lat;

    @Column(nullable = true)
    public double lon;

    public CompanyInfo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
