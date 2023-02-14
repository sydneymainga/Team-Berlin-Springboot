package com.spaceyatech.berlin.requests;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
public class OtpVerifyRequest {
    @NonNull
   private UUID userId;
    @NonNull
   private String otp;
}
