package edu.ijse.datadish.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryTM {
    private String id;
    private String name;
    private int qty;
    private int stockLevel;
}
