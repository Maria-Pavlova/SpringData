package com.example.workshopnextleveltechnologiesmvc.web.controller;

import com.example.workshopnextleveltechnologiesmvc.service.models.LoginModel;
import com.example.workshopnextleveltechnologiesmvc.service.models.UserRegisterModel;
import com.example.workshopnextleveltechnologiesmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("user/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(UserRegisterModel userRegisterModel){
        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())){
            return super.redirect("/users/register");
        }
        this.userService.registerUser(userRegisterModel);
        return super.redirect("/users/login");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("user/login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(LoginModel loginModel){
        if (userService.login(loginModel).isEmpty()){
            return super.redirect("/users/login");
        }
        return super.redirect("/home");
    }
}
