package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivity(scheduleDTO.getActivities());
        schedule.setEmployees(scheduleDTO.getEmployeeIds().stream().map(aLong -> this.employeeService.find(aLong).get()).collect(Collectors.toSet()));
        schedule.setPets(scheduleDTO.getPetIds().stream().map(aLong -> this.petService.find(aLong).get()).collect(Collectors.toSet()));
        Long id = this.scheduleService.save(schedule);
        if (id != null) {
            return this.convertScheduleToScheduleDTO(schedule);
        }

        return null;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleService.findAll().stream().map(schedule -> this.convertScheduleToScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Optional<Pet> pet = this.petService.find(petId);
        if (pet.isPresent()) {
            return this.scheduleService.getAllByPet(pet.get()).stream().map(schedule -> this.convertScheduleToScheduleDTO(schedule)).collect(Collectors.toList());
        }

        return null;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Optional<Employee> employee = this.employeeService.find(employeeId);
        if (employee.isPresent()) {
            return this.scheduleService.getAllByEmployee(employee.get()).stream().map(schedule -> this.convertScheduleToScheduleDTO(schedule)).collect(Collectors.toList());
        }

        return null;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = this.petService.findAllByOwnerId(customerId);

        if (pets.size() > 0) {
            return this.scheduleService.getAllByPets(pets).stream().map(schedule -> this.convertScheduleToScheduleDTO(schedule)).collect(Collectors.toList());
        }

        return null;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if (schedule.getPets() != null) {
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        }

        if (schedule.getActivity() != null) {
            scheduleDTO.setActivities(schedule.getActivity());
        }

        if (schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        }

        return scheduleDTO;
    }
}
