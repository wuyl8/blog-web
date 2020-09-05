package com.wuyl.utils.servlet;

import com.wuyl.utils.SpringContextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MultipleServiceServlet extends HttpServlet {
    //private Log log =  LogFactory.getLog(MultipleServiceServlet.class);
    Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String serviceName = req.getParameter("serviceName");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(serviceName, HttpServletRequest.class, HttpServletResponse.class);
            String name = ClassUtils.getShortNameAsProperty(this.getClass());
            declaredMethod.invoke(SpringContextUtils.getBean(name),req,resp);
        } catch (NoSuchMethodException e) {
            log.error("Dispatcher Service["+serviceName+" is not exist.]",e);
        } catch (IllegalAccessException e) {
            log.error("method param error,please check",e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
    }
}
