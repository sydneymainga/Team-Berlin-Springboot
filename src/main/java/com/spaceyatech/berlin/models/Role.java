package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Role")
public class Role extends BaseEntity{
    //id from base entity
    @Column(name="role_name", length=45)
    private String name;
    @Column(name ="role_col",length=45)
    private String rolecol;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "role")
    @JsonIgnore
    private Set<User> user = new HashSet<>();

}
