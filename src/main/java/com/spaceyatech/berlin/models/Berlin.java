package com.spaceyatech.berlin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Berlin {

    /**this serves as the entity class ,the name berlin can be later renamed to anything that suits the project
    in all classes that it appears*/
}
