package com.spaceyatech.berlin.response;

import com.spaceyatech.berlin.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AllUsersResponse {

    private String username;
    private String email;
    private String phone_number;
    private String date_created;

    //@Builder.Default
    //@Singular
    private Set<Role> role = new HashSet<>();
}
