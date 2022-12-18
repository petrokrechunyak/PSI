package com.tests.task7_8.controller;

import com.tests.task7_8.entity.User;
import com.tests.task7_8.entity.dto.UserDto;
import com.tests.task7_8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller("/")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        System.out.println(user);
        if(user != null)
            return "redirect:/";
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userRepository.getByLoginAndPassword(login, password);
        if(user == null) {
            model.addAttribute("errorMsg", "Дані для авторизації невірні");
            return "login";
        }
        UserDto userDto = new UserDto(
                user.getName(),
                user.getLogin()
        );

        request.getSession().setAttribute("user", userDto);

        return "redirect:/";
    }

    @GetMapping("/")
    public String getHome(Model model, HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if(user == null)
            return "redirect:/login";
        model.addAttribute("user", user.getName());
        return "home";
    }

    @GetMapping("/names")
    public String getNames(Model model, HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if(user == null)
            return "redirect:/login";
        model.addAttribute("users", userRepository.findAll());
        return "names";
    }

    @PostMapping("/logout")
    public String postLogout(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if(user != null) {
            request.getSession().setAttribute("user", null);
        }
        return "redirect:/login";
    }

}
