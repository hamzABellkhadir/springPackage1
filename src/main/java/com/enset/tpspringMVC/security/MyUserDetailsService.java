package com.enset.tpspringMVC.security;

import com.enset.tpspringMVC.entities.Users;
import com.enset.tpspringMVC.entities.UsersRoles;
import com.enset.tpspringMVC.repositories.UserInter;
import com.enset.tpspringMVC.repositories.UserRoleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInter userInter;
    @Autowired
    private UserRoleInter userRoleInter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user=  userInter.findById(username);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        Users tempUser =user.get();
        Collection<UsersRoles> listRoles = userRoleInter.findAllByUsername(tempUser.getUsername());

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(UsersRoles role : listRoles){
            System.out.println(role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        }

        return new User(tempUser.getUsername(),tempUser.getPassword(),authorities);
    }
}
