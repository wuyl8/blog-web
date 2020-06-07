package com.wuyl.utils.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MultipleServiceServlet extends HttpServlet {
    private Log log =  LogFactory.getLog(MultipleServiceServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String serviceName = req.getParameter("serviceName");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(serviceName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            log.error("Dispatcher Service["+serviceName+" is not exist.]",e);
        } catch (IllegalAccessException e) {
            log.error("method param error,please check",e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
    }
}
