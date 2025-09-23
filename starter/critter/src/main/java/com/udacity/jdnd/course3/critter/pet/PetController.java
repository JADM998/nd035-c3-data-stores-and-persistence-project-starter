package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.mappers.PetMapper;
import com.udacity.jdnd.course3.critter.pet.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(
            PetService petService,
            PetMapper petMapper
    ){
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        var pet = petService.create(petDTO);
        return petMapper.dtoFromEntity(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        var pet = petService.getById(petId);
        return pet.map(petMapper::dtoFromEntity).orElse(null);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAll().stream().map(
                petMapper::dtoFromEntity).toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getByOwnerId(ownerId).stream().map(
                petMapper::dtoFromEntity).toList();
    }
}
