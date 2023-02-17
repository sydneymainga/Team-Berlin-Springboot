package com.spaceyatech.berlin.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MpesaTransactionStatusRequestBody {

        private String initiator;
        private String securityCredential;
        private String commandId;
        private String transactionId;
        private String partyA;
        private String identifierType;
        private String resultUrl;
        private String queueTimeOutUrl;
        private String remarks;
        private String occasion;

        // Getters and setters
    }


