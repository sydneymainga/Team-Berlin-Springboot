package com.spaceyatech.berlin.response;

import com.spaceyatech.berlin.models.Role;
import liquibase.pro.packaged.B;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
//@Builder
@NoArgsConstructor
public class AllUsersResponse {

    private String username;
    private String email;
    private String phone_number;
    private String date_created;
    private List<Role> role;


}
