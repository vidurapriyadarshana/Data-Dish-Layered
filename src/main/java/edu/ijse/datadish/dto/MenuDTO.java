package edu.ijse.datadish.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MenuDTO {
    private String menuItemID;
    private String orderID;
    private int qty;
}
