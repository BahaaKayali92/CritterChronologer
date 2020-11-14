package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends Person {
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    @Column(name = "skill")
    private Set<EmployeeSkill> skills;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    @Column(name = "day_available")
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    private List<Schedule> schedule;

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, List<Schedule> schedule) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
        this.schedule = schedule;
    }

    public Employee() {
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
