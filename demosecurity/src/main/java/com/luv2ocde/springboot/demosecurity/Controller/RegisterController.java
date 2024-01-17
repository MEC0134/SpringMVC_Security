package com.luv2ocde.springboot.demosecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {



    @GetMapping("/register")
    public String registerEmployee() {

        return "pages/register";
    }





}
