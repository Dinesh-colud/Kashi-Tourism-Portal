package com.project.loginPage.controllers;

import com.project.loginPage.entities.User;
import com.project.loginPage.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/regForm")
    public String submitForm(@ModelAttribute("user") User user,
                             HttpServletRequest request,
                             Model model){

        boolean status = userService.userRegister(user);

        if(status){

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            return "redirect:/profile";
        }
        else{
            model.addAttribute("error","Email Already Exists, Try Again");
            return "Register";
        }
    }

    @GetMapping("/registerForm")
    public String openRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "Register";
    }


    @GetMapping("/")
    public String root(){
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String openLoginPage(Model model){
        model.addAttribute("user", new User());
        return "Login";
    }

    @PostMapping("/loginForm")
    public String submitLoginForm(@ModelAttribute("user") User user,
                                  HttpServletRequest request,
                                  Model model){

        User validUser = userService.loginUser(user.getEmail(), user.getPassword());

        if(validUser != null){

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", validUser);

            return "redirect:/profile";
        }
        else {
            model.addAttribute("error", "email and password not matched");
            model.addAttribute("user", new User());
            return "Login";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session != null){
            session.invalidate();
        }
        return "redirect:/loginForm";
    }
}
