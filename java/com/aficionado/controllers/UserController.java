package com.aficionado.controllers;

import com.aficionado.models.User;
import com.aficionado.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/{username}")
    public String getUser(@PathVariable(value = "username") String username, Model model) {
        User loggedInUser = userService.getLoggedInUser();
        User user = userService.findByUsername(username);

        boolean isSelfPage = loggedInUser.getUsername().equals(username);
        model.addAttribute("isSelfPage", isSelfPage);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/users")
    public String getUsers(@RequestParam(value = "filter", required = false) String filter, Model model) {
        List<User> users = new ArrayList<User>();

        User loggedInUser = userService.getLoggedInUser();


        if (filter == null) {
            filter = "all";
        }
        model.addAttribute("users", users);


        return "users";
    }
}
