package com.udacity.jdnd.course3.critter.user.mappers;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeDayOfWeekEntity;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeSkillEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDTO dtoFromEntity(EmployeeEntity employeeEntity){
        var employee = new EmployeeDTO();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        var skills = employeeEntity.getSkills();
        if(Objects.nonNull(skills)){
            employee.setSkills(skills.stream()
                .map(EmployeeSkillEntity::getSkill)
                .collect(Collectors.toSet()));
        }
        var daysAvailable = employeeEntity.getAvailableDays();
        if(Objects.nonNull(daysAvailable)){
            employee.setDaysAvailable(daysAvailable.stream()
                    .map(EmployeeDayOfWeekEntity::getDayAvailable)
                    .collect(Collectors.toSet()));
        }
        return employee;
    }

}
