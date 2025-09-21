package com.udacity.jdnd.course3.critter.schedule.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "ScheduledActivity")
public class ScheduledActivitiesEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeSkill activity;

    @ManyToOne
    private ScheduleEntity schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeSkill getActivity() {
        return activity;
    }

    public void setActivity(EmployeeSkill activity) {
        this.activity = activity;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }
}
