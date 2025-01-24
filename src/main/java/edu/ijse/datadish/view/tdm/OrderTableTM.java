package edu.ijse.datadish.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderTableTM {
    private String orderId;
    private String employeeId;
    private String tableId;
    private String totalAmount;
    private String status;
}