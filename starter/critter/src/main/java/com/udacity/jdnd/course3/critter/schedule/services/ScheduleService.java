package com.udacity.jdnd.course3.critter.schedule.services;

import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduledEmployeeEntity;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduledPetEntity;
import com.udacity.jdnd.course3.critter.schedule.mappers.ScheduleMapper;
import com.udacity.jdnd.course3.critter.schedule.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleService(
            ScheduleRepository scheduleRepository,
            ScheduleMapper scheduleMapper,
            PetRepository petRepository,
            EmployeeRepository employeeRepository
    ){
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ScheduleEntity create(ScheduleDTO scheduleDTO){
        var scheduledEntity = populateEntity(new ScheduleEntity(), scheduleDTO);
        return scheduleRepository.save(scheduledEntity);
    }

    public List<ScheduleEntity> getAll(){
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getAllFromPet(Long petId){
        return scheduleRepository.getByPetId(petId);
    }

    public List<ScheduleEntity> getAllFromEmployee(Long employeeId){
        return scheduleRepository.getByEmployeeId(employeeId);
    }

    public List<ScheduleEntity> getAllFromCustomer(Long customerId){
        return scheduleRepository.getByCustomerId(customerId);
    }

    private ScheduleEntity populateEntity(ScheduleEntity scheduleEntity, ScheduleDTO scheduleDTO){
        var schedule = scheduleMapper.populateFromDto(scheduleEntity, scheduleDTO);

        schedule.setEmployees(scheduleDTO.getEmployeeIds().stream().map(
                employeeId -> {
                    var employee = employeeRepository.findById(employeeId);
                    if(employee.isEmpty()) throw new RuntimeException("Couldn't find employee with id: " + employeeId);
                    var scheduledEmployee = new ScheduledEmployeeEntity();
                    scheduledEmployee.setSchedule(schedule);
                    scheduledEmployee.setEmployee(employee.get());
                    return scheduledEmployee;
                }).toList());

        schedule.setPets(scheduleDTO.getPetIds().stream().map(
                petId -> {
                    var pet = petRepository.findById(petId);
                    if(pet.isEmpty()) throw new RuntimeException("Couldn't find pet with id: " + petId);
                    var scheduledPet = new ScheduledPetEntity();
                    scheduledPet.setSchedule(schedule);
                    scheduledPet.setPet(pet.get());
                    return scheduledPet;
                }
        ).toList());

        return schedule;
    }
}
