package com.spaceyatech.berlin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class OtpVerifyResponse {

    private String message;

}
