package com.udacity.jdnd.course3.critter.schedule.entities;

import com.udacity.jdnd.course3.critter.user.entities.EmployeeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "ScheduledEmployee")
public class ScheduledEmployeeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private EmployeeEntity employee;
    @ManyToOne
    private ScheduleEntity schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }
}
