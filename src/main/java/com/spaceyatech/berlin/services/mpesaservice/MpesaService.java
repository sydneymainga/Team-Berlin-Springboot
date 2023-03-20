package com.spaceyatech.berlin.services.mpesaservice;


import com.spaceyatech.berlin.client.MpesaApiClient;
import com.spaceyatech.berlin.requests.MpesaC2bRequest;
import com.spaceyatech.berlin.requests.MpesaExpressRequest;
import com.spaceyatech.berlin.requests.MpesaTransactionStatusRequestBody;
import com.spaceyatech.berlin.response.MpesaResponse;
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

        public MpesaResponse getTransactionStatus(MpesaTransactionStatusRequestBody request, String transactionId) throws IOException {

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


                MpesaResponse response = null;
               try {
                    response = mpesaApiClient.getTransactionStatus(requestBody);

                    log.info("response from safaricom {} : ",response);

                    if(response.getResponseCode().equals("0")){
                        //save something to db
                        log.info("response : {}",response);
                        return response;
                    }else {
                        //save description and code to db



                        MpesaResponse responsewhennotzero = MpesaResponse.builder()
                                .responseCode(response.getResponseCode())
                                .transactionStatus(response.getResponseDescription())
                                .build();

                        log.info("response : {}",responsewhennotzero);

                        return responsewhennotzero;
                    }

               }catch (Exception e){
                   String error = "we were unable to retrieve transaction status because of : {}"+e.getMessage();
                   log.error(error);

                   MpesaResponse responsewhenexception = MpesaResponse.builder()

                           .errorCode("0.001")
                           .requestId("response.getRequestId()")
                           .errorMessage(error)
                           .build();
                   log.info(String.valueOf(responsewhenexception));
                   return responsewhenexception;
                   
                  // throw new RuntimeException("we were unable to retrieve transaction status : "+e.getMessage());
               }





        }

    public MpesaResponse customerToBusiness(MpesaC2bRequest mpesaC2bRequest)throws IOException {

            MpesaC2bRequest request = MpesaC2bRequest.builder()
                    .billRefNumber(mpesaC2bRequest.getBillRefNumber())
                    .msisdn(mpesaC2bRequest.getMsisdn())
                    .commandID(mpesaC2bRequest.getCommandID())
                    .shortCode(mpesaC2bRequest.getShortCode())
                    .amount(mpesaC2bRequest.getAmount())
               .build();
        log.info("c2b requestBody in mpesa-service : {}",mpesaC2bRequest);

        MpesaResponse response = null;
        try {
            response = mpesaApiClient.getC2bTransaction(request);

            log.info("response from safaricom c2b {} : ",response);

            if(response.getResponseCode().equals("0")){
                //save something to db
                log.info("response c2b : {}",response);
                return response;
            }else {
                //save description and code to db



                MpesaResponse responsewhennotzero = MpesaResponse.builder()
                        .responseCode(response.getResponseCode())
                        .transactionStatus(response.getResponseDescription())
                        .build();

                log.info("response c2b : {}",responsewhennotzero);

                return responsewhennotzero;
            }

        }catch (Exception e){
            //log.error("we were unable to c2b trans because of : {}",e.getMessage());

            String error = "unable to sent c2b request because of : {}"+e.getMessage();
            log.error(error);

            MpesaResponse responsewhenexception = MpesaResponse.builder()

                    .errorMessage(error)

                    .build();


            return responsewhenexception;

            // throw new RuntimeException("we were unable to retrieve transaction status : "+e.getMessage());
        }


    }

    public MpesaResponse mpesaExpressStkPush(MpesaExpressRequest mpesaExpressRequest) throws IOException{

            MpesaExpressRequest request = MpesaExpressRequest.builder()

                    .businessShortCode(mpesaExpressRequest.getBusinessShortCode())
                    .password(mpesaExpressRequest.getPassword())
                    .timestamp(mpesaExpressRequest.getTimestamp())
                    .transactionType(mpesaExpressRequest.getTransactionType())
                    .amount(mpesaExpressRequest.getAmount())
                    .partyA(mpesaExpressRequest.getPartyA())
                    .partyB(mpesaExpressRequest.getPartyB())
                    .phoneNumber(mpesaExpressRequest.getPhoneNumber())
                    .callBackURL(mpesaExpressRequest.getCallBackURL())
                    .accountReference(mpesaExpressRequest.getAccountReference()) //figure out how to generate it dynamically and store it in db
                    .transactionDesc(mpesaExpressRequest.getTransactionDesc())

                    .build();

            log.info("mpesaExpressStkPush requestBody in mpesa-service : {}",request);
        MpesaResponse response = null;
        try {
            response = mpesaApiClient.sendmpesaExpressStkPush(request);

            log.info("response from safaricom mpesaExpressStkPush {} : ",response);

            if( response.getResponseCode()!= null && response.getResponseCode().equals("0")){
                //save something to db
                log.info("response mpesaExpressStkPush : {}",response);
                return response;
            }else {
                //save description and code to db



                MpesaResponse responsewhennotzero = MpesaResponse.builder()
                        .responseCode(response.getResponseCode())
                        .transactionStatus(response.getResponseDescription())
                        .build();

                log.info("response mpesaExpressStkPush : {}",responsewhennotzero);

                return responsewhennotzero;
            }

        }catch (Exception e){
            String error = "unable to sent stk push request because of : {}"+e.getMessage();
            log.error(error);

            MpesaResponse responsewhenexception = MpesaResponse.builder()

                    .errorMessage(error)

                    .build();

            log.info(String.valueOf(responsewhenexception));

            return responsewhenexception;

            // throw new RuntimeException("we were unable to retrieve transaction status : "+e.getMessage());
        }
    }
}

