package com.udacity.jdnd.course3.critter.schedule.mappers;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduledActivitiesEntity;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduledEmployeeEntity;
import com.udacity.jdnd.course3.critter.schedule.entities.ScheduledPetEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    public ScheduleDTO dtoFromEntity(ScheduleEntity scheduleEntity){
        var schedule = new ScheduleDTO();
        schedule.setDate(scheduleEntity.getServiceDay());
        schedule.setId(scheduleEntity.getId());
        schedule.setActivities(scheduleEntity.getActivities().stream().map(
                ScheduledActivitiesEntity::getActivity).collect(Collectors.toSet()));
        schedule.setEmployeeIds(scheduleEntity.getEmployees().stream().map(
                employeeEntity -> employeeEntity.getEmployee().getId()).toList());
        schedule.setPetIds(scheduleEntity.getPets().stream().map(
                pet -> pet.getPet().getId()).toList());
        return schedule;
    }

    public ScheduleEntity populateFromDto(ScheduleEntity scheduleEntity, ScheduleDTO scheduleDTO){
        scheduleEntity.setServiceDay(scheduleDTO.getDate());
        scheduleEntity.setActivities(scheduleDTO.getActivities().stream().map(
                activity -> {
                    var activityEntity = new ScheduledActivitiesEntity();
                    activityEntity.setActivity(activity);
                    activityEntity.setSchedule(scheduleEntity);
                    return activityEntity;
                }).toList());
        return scheduleEntity;
    }
}
