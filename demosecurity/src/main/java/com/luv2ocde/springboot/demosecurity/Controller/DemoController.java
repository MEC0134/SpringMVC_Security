package com.luv2ocde.springboot.demosecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage() {

        return "index";
    }

    @GetMapping("/leaders")
    public String leaderPage() {

        return "leaders";
    }

    @GetMapping("/systems")
    public String systemsPage() {

        return "systems";
    }


}
