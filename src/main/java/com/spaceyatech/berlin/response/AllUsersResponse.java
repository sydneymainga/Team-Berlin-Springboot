package com.spaceyatech.berlin.response;

import com.spaceyatech.berlin.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AllUsersResponse {

    private String username;
    private String email;
    private String phone_number;
    private String date_created;
    private List<String> role;

    //private Set<Role> role = new HashSet<>();
}
