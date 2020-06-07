package com.wuyl.controller;

import com.wuyl.utils.servlet.MultipleServiceServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/blog/test")
public class TestController extends MultipleServiceServlet {
    private Log log =  LogFactory.getLog(TestController.class);

    public void test(HttpServletRequest req, HttpServletResponse resp){
        log.info("hi is my testMutipleServlet!");
    }
}
