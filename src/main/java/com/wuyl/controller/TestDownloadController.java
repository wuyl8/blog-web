package com.wuyl.controller;

import com.wuyl.utils.DownLoadUtils;
import com.wuyl.utils.servlet.MultipleServiceServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@WebServlet("/blog/testDownload")
public class TestDownloadController extends MultipleServiceServlet {
    private Log log = LogFactory.getLog(TestDownloadController.class);

    public void test(HttpServletRequest req, HttpServletResponse resp){
        log.info("hi is my testMutipleServlet!");
    }
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("enter download method...");

        String fileName = request.getParameter("fileName");
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/assets/" + fileName);

        FileInputStream fileInputStream = new FileInputStream(realPath);

        String mimeType = servletContext.getMimeType(fileName);
        response.setHeader("content-type", mimeType);
        String agent = request.getHeader("user-agent");
        fileName = DownLoadUtils.getFileName(agent, fileName);
        response.setHeader("content-disposition", "attachment;filename=" + fileName);

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fileInputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        fileInputStream.close();
    }
}
