package com.udacity.jdnd.course3.critter.user.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity(name = "Employee")
public class EmployeeEntity extends PersonEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<EmployeeSkillEntity> skills;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<EmployeeDayOfWeekEntity> availableDays;

    public Set<EmployeeSkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillEntity> skills) {
        this.skills = skills;
    }

    public Set<EmployeeDayOfWeekEntity> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(Set<EmployeeDayOfWeekEntity> availableDays) {
        this.availableDays = availableDays;
    }
}
