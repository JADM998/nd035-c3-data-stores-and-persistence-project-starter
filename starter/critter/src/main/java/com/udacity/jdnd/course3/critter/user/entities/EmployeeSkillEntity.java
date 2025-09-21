package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "EmployeeSkill")
public class EmployeeSkillEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeSkill skill;

    @ManyToOne
    private EmployeeEntity employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeSkill getSkill() {
        return skill;
    }

    public void setSkill(EmployeeSkill skill) {
        this.skill = skill;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
}
