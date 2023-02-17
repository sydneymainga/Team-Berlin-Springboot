package com.spaceyatech.berlin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaTransactionStatusResponse {

    private String conversationId;
    private String originatorConversationId;
    private String responseCode;
    private String responseDescription;
    private String transactionStatus;

    //for when it falls into an error
    private String requestId;
    private String errorCode;
    private String errorMessage;


    /*{
            "OriginatorConversationID": "47146-3899930-1",
            "ConversationID": "AG_20230216_201019746bc23c93e14e",
            "ResponseCode": "0",
            "ResponseDescription": "Accept the service request successfully."
    }
    {
                      "requestId":"108249-130166674-1",
                      "errorCode": "404.001.03",
                     "errorMessage": "Invalid Access Token"
                }



    */

    /*
     * M-Pesa Result and Response Codes
     *
     * `0` - Success
     *
     * `1` - Insufficient Funds
     *
     * `2` - Less Than Minimum Transaction Value
     *
     * `3` - More Than Maximum Transaction Value
     *
     * `4` - Would Exceed Daily Transfer Limit
     *
     * `5` - Would Exceed Minimum Balance
     *
     * `6` - Unresolved Primary Party
     *
     * `7` - Unresolved Receiver Party
     *
     * `8` - Would Exceed Maxiumum Balance
     *
     * `11` - Debit Account Invalid
     *
     * `12` - Credit Account Invalid
     *
     * `13` - Unresolved Debit Account
     *
     * `14` - Unresolved Credit Account
     *
     * `15` - Duplicate Detected
     *
     * `17` - Internal Failure
     *
     * `20` - Unresolved Initiator
     *
     * `26` - Traffic blocking condition in place
     *
     */

}
