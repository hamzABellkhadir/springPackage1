package com.enset.tpspringMVC.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users_roles")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UsersRoles {

    @Id
    private String username;

    private String role;
}
