package com.udacity.jdnd.course3.critter.user.services;

import com.udacity.jdnd.course3.critter.pet.services.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.mappers.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PetService petService;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            PetService petService
    ){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.petService = petService;
    }

    public CustomerEntity create(CustomerDTO customerDTO){
        var customer = customerMapper.populateEntityDataFromDto(new CustomerEntity(), customerDTO);
        return customerRepository.save(customer);
    }

    public List<CustomerEntity> getAll(){
        var customers = customerRepository.findAll();
        customers.forEach(customerEntity -> {
            customerEntity.setPets(petService.getByOwnerId(customerEntity.getId()));
        });

        return customers;
    }

    public Optional<CustomerEntity> getCustomerByPetId(Long petId){
        var pet = petService.getById(petId);
        if(pet.isEmpty()) return Optional.empty();

        var customer = customerRepository.findById(pet.get().getOwner().getId());
        if(customer.isEmpty()) return Optional.empty();
        var customerEntity = customer.get();

        //This is necessary since the tests seem to not be able to load the customer.getPets (which is null)
        //This is a test-only case, in Postman this doesn't happen.
        customerEntity.setPets(petService.getByOwnerId(customerEntity.getId()));

        return customer;
    }
}
