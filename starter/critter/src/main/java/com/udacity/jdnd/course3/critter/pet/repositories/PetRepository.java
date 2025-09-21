package com.udacity.jdnd.course3.critter.pet.repositories;

import com.udacity.jdnd.course3.critter.pet.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

//    @Query("SELECT p.* FROM pet WHERE id=:ownerId")
    public List<PetEntity> getByOwnerId(Long ownerId);

}
