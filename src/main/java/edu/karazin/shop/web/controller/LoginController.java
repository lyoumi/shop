package edu.karazin.shop.web.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "login")
    public String loadLogin(Model model) {
        model.addAttribute("userData", new User());
        return "bookstore-login";
    }

    @GetMapping(value = "signup")
    public String loadSighUpPage(Model model){
        model.addAttribute("user", new User());
        return "bookstore-signup";
    }

    @PostMapping(path = "signup")
    public String signUpHandling(@ModelAttribute @Valid User user, @RequestParam(name = "confirmationPassword") String confirmationPassword, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "signup";
        }else {
            if (user.getPassword().equals(confirmationPassword)){
                user.setUsername(user.getUsername().replaceAll(" ", ""));
                user.setPassword(user.getPassword().replaceAll(" ", ""));
                if(userService.createUser(user)) return "redirect:/login";
            }
            return "redirect:/signup";
        }

    }
}
