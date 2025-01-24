package edu.ijse.datadish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Salary {
    private String salaryId;
    private String employeeId;
    private String date;
    private double amount;
}
