package com.spaceyatech.berlin.requests;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
public class AccountRequest {

    private String display_photo;
    @NonNull
    private String name;

    private String bio_data;
    @NonNull
    private UUID userId;
}
