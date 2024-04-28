package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name= "rentarea")
public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="buildingid")
    private BuildingEntity building;


    public BuildingEntity getBuilding() {
        return building;
    }
    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
    @Column(name="value")
    private Long value;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }
}