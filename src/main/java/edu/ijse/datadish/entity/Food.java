package edu.ijse.datadish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Food {
    private String foodId;
    private String foodName;
    private double foodPrice;
    private String foodCategory;
    private String foodAvailability;
    private String foodImagePath;

    public Food(String id, String name, double price, String category, String availability) {
        this.foodId = id;
        this.foodName = name;
        this.foodPrice = price;
        this.foodCategory = category;
        this.foodAvailability = availability;
    }
}
