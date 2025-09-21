package com.udacity.jdnd.course3.critter.user.services;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeDayOfWeekEntity;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.user.mappers.EmployeeMapper;
import com.udacity.jdnd.course3.critter.user.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper
    ){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        var employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());

        assignSkillsToEmployeeIfNotNull(employee, employeeDTO.getSkills());
        assignDaysAvailableToEmployeeIfNotNull(employee, employeeDTO.getDaysAvailable());

        var createdEmployee = employeeRepository.save(employee);

        return employeeMapper.dtoFromEntity(createdEmployee);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId){
        var employee = employeeRepository.findById(employeeId);
        return employee.map(employeeMapper::dtoFromEntity);
    }

    @Transactional
    public Boolean setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable){

        var employeeOpt = employeeRepository.findById(employeeId);
        if(employeeOpt.isEmpty()) return false;

        var employee = employeeOpt.get();
        if(Objects.nonNull(employee.getAvailableDays())){
            employeeOpt.get().getAvailableDays().clear();
        }
        assignDaysAvailableToEmployeeIfNotNull(employee, daysAvailable);

        return true;
    }

    public List<EmployeeDTO> findForDateAndActivities(EmployeeRequestDTO requestDTO){
        var day = DayOfWeek.from(requestDTO.getDate());

        var employees = employeeRepository.getEmployeeByDayAndActivities(
                day, requestDTO.getSkills(), requestDTO.getSkills().size());

        return employees.stream().map(employeeMapper::dtoFromEntity).toList();
    }

    private void assignSkillsToEmployeeIfNotNull(EmployeeEntity employee, Set<EmployeeSkill> skills){
        if(Objects.nonNull(skills)){
            var employeeSkills = skills.stream()
                    .map(skill -> {
                        var skillEntity = new EmployeeSkillEntity();
                        skillEntity.setEmployee(employee);
                        skillEntity.setSkill(skill);
                        return skillEntity;
                    }).collect(Collectors.toSet());
            var skillsSet = employee.getSkills();
            if(Objects.isNull(skillsSet)){
                employee.setSkills(employeeSkills);
                return;
            }

            skillsSet.clear();
            skillsSet.addAll(employeeSkills);
        }
    }

    private void assignDaysAvailableToEmployeeIfNotNull(EmployeeEntity employee, Set<DayOfWeek> dayOfWeeks){
        if(Objects.nonNull(dayOfWeeks)){
            var employeeDays = dayOfWeeks.stream()
                    .map(day -> {
                        var availableDay = new EmployeeDayOfWeekEntity();
                        availableDay.setEmployee(employee);
                        availableDay.setDayAvailable(day);
                        return availableDay;
                    }).collect(Collectors.toSet());
            var daysAvailableSet = employee.getAvailableDays();
            if(Objects.isNull(daysAvailableSet)){
                employee.setAvailableDays(employeeDays);
                return;
            }

            daysAvailableSet.clear();
            daysAvailableSet.addAll(employeeDays);
        }
    }

}
