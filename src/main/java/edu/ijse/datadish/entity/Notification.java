package edu.ijse.datadish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Notification {
    private String notificationId;
    private String customerId;
    private String desc;
    private String date;
}
