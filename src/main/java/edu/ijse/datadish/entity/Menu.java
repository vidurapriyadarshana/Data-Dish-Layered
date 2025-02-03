package edu.ijse.datadish.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Menu {
    private String menuItemID;
    private String orderID;
    private int qty;
}
