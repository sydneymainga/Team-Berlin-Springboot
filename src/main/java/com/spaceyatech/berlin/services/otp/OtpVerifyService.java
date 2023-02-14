package com.spaceyatech.berlin.services.otp;
import com.spaceyatech.berlin.models.User;
import com.spaceyatech.berlin.repository.OtpRepository;
import com.spaceyatech.berlin.requests.OtpVerifyRequest;
import com.spaceyatech.berlin.response.OtpVerifyResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OtpVerifyService {
    @Autowired
    private OtpRepository otpRepository;
    public OtpVerifyResponse verifyOtp(OtpVerifyRequest otpVerifyRequest) {

        UUID userID = otpVerifyRequest.getUserId();
       String otpCode = otpVerifyRequest.getOtp();

       log.info("userID {} and otp {}",userID,otpCode);

        OtpVerifyResponse otpVerifyResponse;
        User presavedOtp;


        Optional<User> otpOptional = Optional.of(otpRepository.findById(userID).get());

        if(otpOptional.isPresent()) {

            if(!otpOptional.get().getVerified_code()){   // if user otp is not verified ...
                presavedOtp = otpOptional.get();

                if (presavedOtp.getVerification_code().equals(otpCode)) {
                    // OTP code is correct, mark user as verified
                    presavedOtp.setVerified_code(true);
                    otpRepository.save(presavedOtp);
                    otpVerifyResponse= OtpVerifyResponse.builder()
                            .message("OTP verification successful")
                            .build();
                    log.info("OTP verification successful");

                }else{
                    otpVerifyResponse= OtpVerifyResponse.builder()
                            .message("Invalid OTP code")
                            .build();
                    log.error("Invalid OTP code");
                }
            }else{
                otpVerifyResponse= OtpVerifyResponse.builder()
                        .message("user already verified")
                        .build();
                log.error("user already verified");
            }


        } else {
            // OTP code not found in database, return error response
            otpVerifyResponse= OtpVerifyResponse.builder()
                    .message("OTP/USERID provided code not found")
                    .build();
            log.error("OTP/USERID provided code not found");
        }

            log.info("otp verification response {} ",otpVerifyResponse);
        return otpVerifyResponse;




    }
}
