package com.enset.tpspringMVC.repositories;

import com.enset.tpspringMVC.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleInter extends JpaRepository<UsersRoles, String> {

}
