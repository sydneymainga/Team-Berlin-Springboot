package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.requests.MpesaC2bRequest;
import com.spaceyatech.berlin.requests.MpesaExpressRequest;
import com.spaceyatech.berlin.requests.MpesaTransactionStatusRequestBody;
import com.spaceyatech.berlin.services.mpesaservice.MpesaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/Mpesa")
@Tag(name="Mpesa Daraja Controller")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MpesaController {

    @Autowired
    private MpesaService mpesaService;




   /*** MPesa Express Merchant initiated online payments AKA lipa na mpesa STK push*/
    @PostMapping("/mpesaExpressApi")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> mpesaExpressApi(@RequestBody @Valid MpesaExpressRequest mpesaExpressRequest) throws IOException{

        log.info("mpesaExpress requestBody {}",mpesaExpressRequest);

        return ResponseEntity.status(HttpStatus.OK).body(mpesaService.mpesaExpressStkPush(mpesaExpressRequest));
    }

    @PostMapping("/c2b")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> customerToBusiness(@RequestBody @Valid MpesaC2bRequest mpesaC2bRequest ) throws IOException{

        log.info("c2b requestBody{}",mpesaC2bRequest);

        return ResponseEntity.status(HttpStatus.OK).body(mpesaService.customerToBusiness(mpesaC2bRequest));
    }

    @PostMapping("/b2c")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> businessToCustomer( ){


        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }


    @GetMapping("/transactionStatus/{transactionId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> transactionStatus(@RequestBody @Valid MpesaTransactionStatusRequestBody request , @PathVariable("transactionId")  String transactionId) throws IOException {
        log.info("mpesa transactionId : {}",transactionId);
        log.info("mpesa request : {}",request);



        return ResponseEntity.status(HttpStatus.OK).body(mpesaService.getTransactionStatus(request,transactionId));
    }

    @GetMapping("/accountBalance")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> accountBalance( ){


        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PostMapping("/reversals")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> Reversals( ){


        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
