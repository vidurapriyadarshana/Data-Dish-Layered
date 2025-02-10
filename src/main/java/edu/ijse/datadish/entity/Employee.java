package edu.ijse.datadish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee {
    private String employeeID;
    private String employeeName;
    private String employeeContact;
    private String hireDate;
    private String userName;
    private String employeeStatus;
    private String address;
    private String email;
    private String role;
    private String password;

    public Employee(String name) {
        this.employeeName = name;
    }

    public Employee(String employeeID, String name, String address, String contact, String email, String hireDate, String status) {
        this.employeeID = employeeID;
        this.employeeName = name;
        this.address = address;
        this.employeeContact = contact;
        this.email = email;
        this.hireDate = hireDate;
        this.employeeStatus = status;
    }
}
