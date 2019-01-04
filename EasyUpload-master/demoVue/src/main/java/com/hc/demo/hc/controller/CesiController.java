package com.hc.demo.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CesiController {
    @RequestMapping("ss")
    public ModelAndView ss() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cesi");
        return modelAndView;
    }
}
