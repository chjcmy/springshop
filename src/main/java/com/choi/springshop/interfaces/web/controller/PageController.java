package com.choi.springshop.interfaces.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by <NAME> on 2017-03-08. @Choi
 *
 */
@Controller
public class PageController {
    @RequestMapping(value = "/start")
    public ModelAndView startPayment() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("checkout");
        modelAndView.addObject("response", "넘길_값");

        return modelAndView;
    }

}
