package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Long save(Employee employee) {
        return this.employeeRepository.save(employee).getId();
    }

    public Optional<Employee> find(Long id) {
        return this.employeeRepository.findById(id);
    }

    public List<Employee> findEmployeesBySkill(EmployeeSkill employeeSkill, DayOfWeek dayOfWeek) {
        return this.employeeRepository.findEmployeeBySkillsAndDaysAvailableEquals(employeeSkill, dayOfWeek);
    }
}
