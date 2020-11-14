package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Long save(Schedule schedule) {
        return this.scheduleRepository.save(schedule).getId();
    }

    public List<Schedule> findAll() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> getAllByPet(Pet pet) {
        return this.scheduleRepository.getAllByPets(pet);
    }

    public List<Schedule> getAllByPets(List<Pet> pets) {
        return this.scheduleRepository.getAllByPetsIn(pets);
    }

    public List<Schedule> getAllByEmployee(Employee employee) {
        return this.scheduleRepository.getAllByEmployees(employee);
    }
}
