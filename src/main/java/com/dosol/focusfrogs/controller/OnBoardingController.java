package com.dosol.focusfrogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnBoardingController {

    @GetMapping("/on")
    public String onP() {
        return "on";
    }
}
