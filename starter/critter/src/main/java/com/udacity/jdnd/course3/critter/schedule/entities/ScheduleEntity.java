package com.udacity.jdnd.course3.critter.schedule.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate serviceDay;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.PERSIST)
    private List<ScheduledPetEntity> pets;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.PERSIST)
    private List<ScheduledEmployeeEntity> employees;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.PERSIST)
    List<ScheduledActivitiesEntity> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(LocalDate serviceDay) {
        this.serviceDay = serviceDay;
    }

    public List<ScheduledPetEntity> getPets() {
        return pets;
    }

    public void setPets(List<ScheduledPetEntity> pets) {
        this.pets = pets;
    }

    public List<ScheduledEmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ScheduledEmployeeEntity> employees) {
        this.employees = employees;
    }

    public List<ScheduledActivitiesEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ScheduledActivitiesEntity> activities) {
        this.activities = activities;
    }
}
