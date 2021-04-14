package com.enset.tpspringMVC.repositories;

import com.enset.tpspringMVC.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInter extends JpaRepository<Users, String> {

}
