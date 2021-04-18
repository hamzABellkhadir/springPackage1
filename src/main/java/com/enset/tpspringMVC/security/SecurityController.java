package com.enset.tpspringMVC.security;

import com.enset.tpspringMVC.entities.Users;
import com.enset.tpspringMVC.entities.UsersRoles;
import com.enset.tpspringMVC.repositories.UserInter;
import com.enset.tpspringMVC.repositories.UserRoleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SecurityController {

    @Autowired
    private UserInter userInter;
    @Autowired
    private UserRoleInter userRoleInter;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/createAcount")
    public String creatAcount(Model model){
        model.addAttribute("user",new Users());
        model.addAttribute("passwordConfirme","");

        return "creatAcountForm";
    }
    @PostMapping(path="/saveTheNewAcount")
    public String insertUser(@Valid Users user, BindingResult bindingResult, Model model,String passwordConfirme) {
        if(bindingResult.hasErrors()) return "redirect:createAcount?error";
        if(passwordConfirme.compareTo( user.getPassword())!=0)  return "redirect:/createAcount?passwordError";
        System.out.println("************** user"+passwordConfirme);
        Optional<Users> temp = this.userInter.findById(user.getUsername());
        if(temp.isPresent())
            return "redirect:/createAcount?userExistError";

        BCryptPasswordEncoder b= new BCryptPasswordEncoder();
        user.setPassword(b.encode(user.getPassword()));

        this.userInter.save(user);
        this.userRoleInter.save(new UsersRoles(null,user.getUsername(),"USER"));
        return "redirect:/createAcount?saved";
    }

    @GetMapping(path="/")
    public String home() {
        return "redirect:/index";
    }
}
