package com.udacity.jdnd.course3.critter.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.DayOfWeek;


@Entity(name = "EmployeeDayOfWeek")
public class EmployeeDayOfWeekEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayAvailable;

    @ManyToOne
    private EmployeeEntity employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(DayOfWeek dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
}
