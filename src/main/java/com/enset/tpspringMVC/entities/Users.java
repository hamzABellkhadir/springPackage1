package com.enset.tpspringMVC.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Size(min = 5)
    private String username;

    @NotNull
    private String 	password;

    private  boolean active=true;

}
