package com.enset.tpspringMVC.repositories;

import com.enset.tpspringMVC.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRoleInter extends JpaRepository<UsersRoles, Long> {

     Collection<UsersRoles> findAllByUsername(String username);
}
