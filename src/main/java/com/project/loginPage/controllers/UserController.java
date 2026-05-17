package com.project.loginPage.controllers;

import com.project.loginPage.entities.User;
import com.project.loginPage.entities.UserFeedback;
import com.project.loginPage.services.FeedbackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("loggedInUser") == null){
            return "redirect:/loginForm";
        }

        User user = (User) session.getAttribute("loggedInUser");

        model.addAttribute("name", user.getName());
        model.addAttribute("feedback", new UserFeedback());

        return "profile";
    }

    @PostMapping("/feedbackForm")
    public String submitForm(@ModelAttribute("feedback") UserFeedback user, RedirectAttributes redirectAttributes){
        feedbackService.save(user);

        redirectAttributes.addFlashAttribute("success", "Feedback Send");

        return "redirect:/profile";
    }
}
