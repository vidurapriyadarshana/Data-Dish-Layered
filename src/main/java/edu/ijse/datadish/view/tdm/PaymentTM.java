package edu.ijse.datadish.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PaymentTM {
    private String payId;
    private String orderId;
    private String amount;
    private String date;
}
