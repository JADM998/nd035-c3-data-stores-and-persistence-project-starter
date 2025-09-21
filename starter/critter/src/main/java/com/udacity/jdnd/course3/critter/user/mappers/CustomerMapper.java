package com.udacity.jdnd.course3.critter.user.mappers;

import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {

    public CustomerEntity populateEntityDataFromDto(CustomerEntity customerEntity, CustomerDTO customerDTO){
        customerEntity.setName(customerDTO.getName());
        customerEntity.setNotes(customerDTO.getNotes());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());

        return customerEntity;
    }

    public CustomerDTO dtoFromEntity(CustomerEntity customerEntity){
        var customer = new CustomerDTO();
        customer.setName(customerEntity.getName());
        customer.setNotes(customerEntity.getNotes());
        customer.setId(customerEntity.getId());
        customer.setPhoneNumber(customerEntity.getPhoneNumber());
        if(Objects.nonNull(customerEntity.getPets())){
            customer.setPetIds(customerEntity.getPets().stream().map(PetEntity::getId).toList());
        }
        return customer;
    }

}
