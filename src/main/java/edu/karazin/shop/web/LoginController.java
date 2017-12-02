package edu.karazin.shop.web;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loadLogin(Model model) {
        model.addAttribute("userData", new User());
        return "bookstore-login";
    }

    @GetMapping(value = "signup")
    public String loadSighUpPage(Model model){
        model.addAttribute("userData", new User());
        return "bookstore-signup";
    }

    @PostMapping
    public String signUpHandling(User user){
        userService.createUser(user);
        return "redirect:/login";
    }
}
