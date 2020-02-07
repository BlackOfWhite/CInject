package org.model;

import org.di.annotations.CInject;
import org.model.emplyees.Employee;

public class Company {

    @CInject
    private Employee employee;

    public Company() {

    }

    @CInject
    public Company(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return "School{" +
            "user=" + employee +
            '}';
    }
}
