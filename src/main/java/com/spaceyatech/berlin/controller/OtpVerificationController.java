package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.repository.OtpRepository;
import com.spaceyatech.berlin.requests.AccountRequest;
import com.spaceyatech.berlin.requests.OtpVerifyRequest;
import com.spaceyatech.berlin.response.AccountResponse;
import com.spaceyatech.berlin.response.OtpVerifyResponse;
import com.spaceyatech.berlin.services.otp.OtpVerifyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/Otp")
@Tag(name="Otp verification Controller")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OtpVerificationController {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private OtpVerifyService otpVerifyService;

    @PostMapping("/otpVerify")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<OtpVerifyResponse> verifyOtp(@NotBlank @RequestBody @Valid OtpVerifyRequest otpVerifyRequest){


        OtpVerifyResponse otpVerifyResponse = otpVerifyService.verifyOtp(otpVerifyRequest);

        return ResponseEntity.status(HttpStatus.OK).body(otpVerifyResponse);

    }
}
