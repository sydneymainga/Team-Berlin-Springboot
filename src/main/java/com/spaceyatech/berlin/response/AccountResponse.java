package com.spaceyatech.berlin.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spaceyatech.berlin.models.User;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class AccountResponse {

    private String display_photo;
    private String name;
    private Timestamp CreatedAT;
    private Timestamp UpdatedAT;
    private String bio_data;
    private UUID userId;
}
