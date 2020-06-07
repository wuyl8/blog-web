package com.wuyl.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/blog")
public class BlogController {
    private Log log =  LogFactory.getLog(BlogController.class);

    @GetMapping("/testBlog.do")
    public String blog(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        log.info("hi is my blog!");
        return "blog";
    }

    @PostMapping("/testBlog.do")
    public String blogDetail(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        log.info("hi is my blogDetail!");
        return "blog";
    }
}
