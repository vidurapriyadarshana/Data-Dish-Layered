package edu.ijse.datadish.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TableDto {
    private String id;
    private String status;
    private int capacity;

    public TableDto(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public TableDto(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
