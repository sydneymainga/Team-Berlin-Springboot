package com.spaceyatech.berlin.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaC2bRequest {

    private String shortCode;
    private String commandID;
    private String amount;
    private String msisdn;
    private String billRefNumber;


    /*{
            "ShortCode": 600980,
            "CommandID": "CustomerBuyGoodsOnline",
            "Amount": 1,
            "Msisdn": 254703414118,
            "BillRefNumber": "null"
    }*/
}
