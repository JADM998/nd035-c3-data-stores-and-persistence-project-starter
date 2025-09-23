package com.udacity.jdnd.course3.critter.pet.services;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import com.udacity.jdnd.course3.critter.pet.mappers.PetMapper;
import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final CustomerRepository customerRepository;

    public PetService(
            PetRepository petRepository,
            PetMapper petMapper,
            CustomerRepository customerRepository
    ){
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.customerRepository = customerRepository;
    }

    public PetEntity create(PetDTO petDTO){
        var owner = customerRepository.findById(petDTO.getOwnerId()).orElseThrow(
                () -> new RuntimeException("Couldn't find the owner with id: " + petDTO.getOwnerId()));

        var pet = petMapper.populateEntityFromDto(new PetEntity(), petDTO);
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    public List<PetEntity> getAll(){
        return petRepository.findAll();
    }

    public Optional<PetEntity> getById(Long petId){
        return petRepository.findById(petId);
    }

    public List<PetEntity> getByOwnerId(Long ownerId){
        return petRepository.getByOwnerId(ownerId);
    }
}
