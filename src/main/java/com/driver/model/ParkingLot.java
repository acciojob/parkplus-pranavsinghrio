package com.driver.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "parkinglot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;
    private String name;

    @OneToMany(mappedBy = "parkinglot",cascade = CascadeType.ALL)
    private List<Spot>spotList=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }

    public ParkingLot() {
    }

    public ParkingLot(String address, String name) {
        this.address = address;
        this.name = name;
    }


}
