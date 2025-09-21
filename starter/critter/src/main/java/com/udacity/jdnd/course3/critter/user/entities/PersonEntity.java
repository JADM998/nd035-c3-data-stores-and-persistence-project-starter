package com.udacity.jdnd.course3.critter.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//Was trying to do it with the annotations above, but it produced the same sequence for both Customer and Employee
@MappedSuperclass
public class PersonEntity {
//    There was an issue about how to use an strategy how id generation, had to consult SO.
//    https://stackoverflow.com/questions/916169/cannot-use-identity-column-key-generation-with-union-subclass-table-per-clas
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @GeneratedValue
    private Long id;

    //255 is fine, most names wont reach that and want to be sure for some edge cases.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
