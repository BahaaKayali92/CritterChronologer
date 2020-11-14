package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Schedule {
    @GeneratedValue
    @Id
    private Long id;
    @ManyToMany
    private Set<Employee> employees;
    @ManyToMany
    private Set<Pet> pets;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    @Column(name = "activity")
    private Set<EmployeeSkill> activity;
    private LocalDate date;

    public Schedule(Long id, Set<Employee> employees, Set<Pet> pets, Set<EmployeeSkill> activity, LocalDate date) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.activity = activity;
        this.date = date;
    }

    public Schedule() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Set<EmployeeSkill> getActivity() {
        return activity;
    }

    public void setActivity(Set<EmployeeSkill> activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
