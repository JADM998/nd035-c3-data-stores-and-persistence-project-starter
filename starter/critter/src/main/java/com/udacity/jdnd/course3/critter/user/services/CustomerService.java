package com.udacity.jdnd.course3.critter.user.services;

import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.pet.services.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.mappers.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PetService petService;
    private final PetRepository petRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            PetService petService,
            PetRepository petRepository
    ){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.petService = petService;
        this.petRepository = petRepository;
    }

    public CustomerDTO create(CustomerDTO customerDTO){
        var customer = customerMapper.populateEntityDataFromDto(new CustomerEntity(), customerDTO);
        var createdCustomer = customerRepository.save(customer);
        return customerMapper.dtoFromEntity(createdCustomer);
    }

    public List<CustomerDTO> getAll(){
        var customers = customerRepository.findAll();
        return customers.stream().map(this::populateCustomerDto).toList();
    }

    public Optional<CustomerDTO> getCustomerByPetId(Long petId){
        var pet = petService.getById(petId);
        if(pet.isEmpty()) return Optional.empty();

        var customer = customerRepository.findById(pet.get().getOwnerId());
        return customer.map(this::populateCustomerDto);
    }

    private CustomerDTO populateCustomerDto(CustomerEntity entity){
        var customer = this.customerMapper.dtoFromEntity(entity);
        var pets = petRepository.getByOwnerId(customer.getId());
        customer.setPetIds(pets.stream().map(PetEntity::getId).toList());
        return customer;
    }
}
