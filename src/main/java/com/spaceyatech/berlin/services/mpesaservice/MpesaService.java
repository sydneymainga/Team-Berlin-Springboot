package com.spaceyatech.berlin.services.mpesaservice;


import com.spaceyatech.berlin.client.MpesaApiClient;
import com.spaceyatech.berlin.requests.MpesaTransactionStatusRequestBody;
import com.spaceyatech.berlin.response.MpesaTransactionStatusResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MpesaService {
        @Autowired
        private MpesaApiClient mpesaApiClient;

        public MpesaTransactionStatusResponse getTransactionStatus(MpesaTransactionStatusRequestBody request,String transactionId) throws IOException {

               MpesaTransactionStatusRequestBody requestBody = MpesaTransactionStatusRequestBody.builder()
                       .transactionId(transactionId)
                       .commandId("TransactionStatusQuery")
                       .identifierType(request.getIdentifierType()) //1 – MSISDN 2 – Till Number 4 – Organization short code
                       .initiator("testapi")
                       .occasion("Comments that are sent along with the transaction")
                       .partyA(request.getPartyA())
                       .queueTimeOutUrl("https://mydomain.com/TransactionStatus/queue/") //looks like a call back
                       .resultUrl("https://mydomain.com/TransactionStatus/result/") //looks like a callback
                       .remarks("Comments that are sent along with the transaction")
                       .securityCredential("bsPT92KkvJMXAGRvUeRFoFNaFYMdtE1xhDEIXa8W24vKSePAsppv/7D3E/TvKuZCpqQp1ppWzQT5xITdXRsjc6C2au+3ohYJwc6egvvs36haOuZ9NBZHQHXn3HAPoB9HLkP48bu3wJhoLIsnWEwLz8c1Jp8EKVt9wyt/3HwzdaKUkUdk7bVhYMdmHq2Of5gMvA5C0KoiGBRTvwtUXJaKkplWAU86MfJHDIBhkbZlPjhemwn4Ksy1yJhKXKkrV1IjN8BC6re8imxQV1tHLq78oieY2SxXFMr+psFAiuEX9HNi5FKTb5ruF/5WdLKUgDPyJ+ertai8m+Ud+Pf2N1Dyaw==")
                       .build();
               log.info("MpesaTransactionStatusRequestBody :{}",requestBody);


                MpesaTransactionStatusResponse response = null;
               try {
                    response = mpesaApiClient.getTransactionStatus(requestBody);

                    log.info("response from safaricom {} : ",response);

                    if(response.getResponseCode().equals("0")){
                        //save something to db
                        log.info("response : {}",response);
                        return response;
                    }else {
                        //save description and code to db



                        MpesaTransactionStatusResponse  responsewhennotzero = MpesaTransactionStatusResponse.builder()
                                .responseCode(response.getResponseCode())
                                .transactionStatus(response.getResponseDescription())
                                .build();

                        log.info("response : {}",responsewhennotzero);

                        return responsewhennotzero;
                    }

               }catch (Exception e){
                   log.error("we were unable to retrieve transaction status because of : {}",e.getMessage());

                   assert response != null;
                   MpesaTransactionStatusResponse  responsewhenexception = MpesaTransactionStatusResponse.builder()

                           .errorCode(response.getErrorCode())
                           .requestId(response.getRequestId())
                           .errorMessage(response.getErrorMessage())
                           .build();

                   return responsewhenexception;
                   
                  // throw new RuntimeException("we were unable to retrieve transaction status : "+e.getMessage());
               }





        }
}

