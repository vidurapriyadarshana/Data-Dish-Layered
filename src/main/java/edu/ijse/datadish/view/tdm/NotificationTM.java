package edu.ijse.datadish.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class NotificationTM {
    private String notificationId;
    private String customerId;
    private String desc;
    private String date;
}
