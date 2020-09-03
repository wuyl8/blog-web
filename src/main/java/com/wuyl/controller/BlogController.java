package com.wuyl.controller;

import com.wuyl.service.BlogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private Log log =  LogFactory.getLog(BlogController.class);
    //private static ApplicationContext applicationContext;
    @Autowired
    private BlogService blogService;

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

    @GetMapping("/userDetail")
    public String userDetail(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        log.info("进入controller");
        List<Map> list = blogService.qryUserDetail();
        for(Map map:list){
            System.out.println(map);
        }
        model.addAttribute("name", name);
        return "blog";
    }
}
