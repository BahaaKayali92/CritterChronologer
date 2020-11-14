package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ScheduleId implements Serializable {
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "skill")
    private EmployeeSkill employeeSkill;

    public ScheduleId(Long employeeId, Long petId, EmployeeSkill employeeSkill) {
        this.employeeId = employeeId;
        this.petId = petId;
        this.employeeSkill = employeeSkill;
    }

    public ScheduleId() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public EmployeeSkill getEmployeeSkill() {
        return employeeSkill;
    }

    public void setEmployeeSkill(EmployeeSkill employeeSkill) {
        this.employeeSkill = employeeSkill;
    }
}
