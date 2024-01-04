package com.luv2ocde.springboot.demosecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage() {

        return "pages/index";
    }

    @GetMapping("/leaders")
    public String leaderPage() {

        return "pages/leaders";
    }

    @GetMapping("/systems")
    public String systemsPage() {

        return "pages/systems";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {

        return "alerts/forbidden";
    }


}
