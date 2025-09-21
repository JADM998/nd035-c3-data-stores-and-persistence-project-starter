package com.udacity.jdnd.course3.critter.user.repositories;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT DISTINCT e FROM Employee e "
            + "LEFT JOIN e.availableDays ad "
            + "LEFT JOIN e.skills s "
            + "WHERE ad.dayAvailable = :day AND s.skill IN :activities "
            + "GROUP BY e.id "
            + "HAVING COUNT(DISTINCT s.skill) >= :amountActivities"
    )
    public List<EmployeeEntity> getEmployeeByDayAndActivities(DayOfWeek day, Set<EmployeeSkill> activities, int amountActivities);

}
