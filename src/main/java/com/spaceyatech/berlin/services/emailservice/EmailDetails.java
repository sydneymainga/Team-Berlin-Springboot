package com.spaceyatech.berlin.services.emailservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDetails {

    private String recipient;

    private String emailBody;

    private String subject;


}
