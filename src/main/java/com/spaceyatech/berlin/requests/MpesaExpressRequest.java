package com.spaceyatech.berlin.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaExpressRequest {

    private String businessShortCode;
    private String password;
    private String timestamp;
    private String transactionType;
    private String amount;
    private String partyA;
    private String partyB;
    private String phoneNumber;
    private String callBackURL;
    private String accountReference;
    private String transactionDesc;


    /*
 *   {
 *     "BusinessShortCode": 174379,
 *     "Password": "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMjMwMjE4MTAyNDU1",
 *     "Timestamp": "20230218102455",
 *     "TransactionType": "CustomerPayBillOnline",
 *     "Amount": 1,
 *     "PartyA": 254708374149,
 *     "PartyB": 174379,
 *     "PhoneNumber": 254708374149,
 *     "CallBackURL": "https://mydomain.com/path",
 *     "AccountReference": "CompanyXLTD",
 *     "TransactionDesc": "Payment of X"
 *   }**/
}
