package com.udacity.jdnd.course3.critter.schedule.entities;

import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "ScheduledPet")
public class ScheduledPetEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private PetEntity pet;
    @ManyToOne
    private ScheduleEntity schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }
}
