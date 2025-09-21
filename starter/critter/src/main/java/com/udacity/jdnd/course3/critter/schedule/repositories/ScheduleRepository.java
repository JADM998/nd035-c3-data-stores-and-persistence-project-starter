package com.udacity.jdnd.course3.critter.schedule.repositories;

import com.udacity.jdnd.course3.critter.schedule.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT s FROM Schedule s "+
            "LEFT JOIN s.pets sp "+
            "WHERE sp.pet.id = :petId"
    )
    public List<ScheduleEntity> getByPetId(Long petId);

    @Query("SELECT s FROM Schedule s "+
            "INNER JOIN s.employees se "+
            "WHERE se.employee.id = :employeeId"
    )
    public List<ScheduleEntity> getByEmployeeId(Long employeeId);

    @Query("SELECT s FROM Schedule s "+
            "LEFT JOIN s.pets sp "+
            "INNER JOIN sp.pet p "+
            "INNER JOIN p.owner o "+
            "WHERE o.id = :customerId"
    )
    public List<ScheduleEntity> getByCustomerId(Long customerId);
}
