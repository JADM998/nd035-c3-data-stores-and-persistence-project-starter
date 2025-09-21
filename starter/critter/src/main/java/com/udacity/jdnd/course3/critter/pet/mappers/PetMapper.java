package com.udacity.jdnd.course3.critter.pet.mappers;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PetMapper {

    public PetDTO dtoFromEntity(PetEntity petEntity){
        var pet = new PetDTO();
        pet.setId(petEntity.getId());
        pet.setNotes(petEntity.getNotes());
        pet.setName(petEntity.getName());
        pet.setType(petEntity.getType());
        pet.setBirthDate(petEntity.getBirthDate());

        var owner = petEntity.getOwner();
        if(Objects.nonNull(owner)){
            pet.setOwnerId(owner.getId());
        }
        return pet;
    }

    public PetEntity populateEntityFromDto(PetEntity petEntity, PetDTO petDTO){
        petEntity.setNotes(petDTO.getNotes());
        petEntity.setBirthDate(petDTO.getBirthDate());
        petEntity.setName(petDTO.getName());
        petEntity.setType(petDTO.getType());

        return petEntity;
    }

}
