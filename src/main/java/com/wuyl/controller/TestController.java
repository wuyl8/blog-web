package com.wuyl.controller;

import com.mysql.jdbc.PreparedStatement;
import com.wuyl.utils.servlet.MultipleServiceServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@WebServlet("/blog/test")
public class TestController extends MultipleServiceServlet {
    private Log log =  LogFactory.getLog(TestController.class);
    @Value("#{dataSource.url}")
    private String url;
    @Value("#{dataSource.driverClassName}")
    private String name;
    @Value("#{dataSource.username}")
    private String user;
    @Value("#{dataSource.password}")
    private String password;

    public void test(HttpServletRequest req, HttpServletResponse resp){
        log.info("hi is my testMutipleServlet!");
    }

    public void insertTestData(HttpServletRequest req, HttpServletResponse resp) {
        log.info("start jdbc connect...");
        Connection conn;
        try{
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            insert(conn);
            log.info("jdbc connect success!");
        }catch (ClassNotFoundException e){
            log.error("jdbc driver error! please check",e);
        }catch (SQLException e){
            log.error("jdbc connection error,please check jdbc.properties",e);
        }catch (Exception e){
            log.error("sql exception",e);
        }
    }

    private static void insert(Connection conn) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 开始时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO offer (offer_name,offer_desc,picture,creater,create_date,update_date,remark) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix;
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                Date date = new Date();
                for (int j = 1; j <= 10000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + "销售品").append(i * j).append("','123'").append(",'123456'").append(",'admin'")
                            .append(",'").append("2016-08-12 14:43:26").append("','").append(simpleDateFormat.format(date)).append("','备注'").append("),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                //suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("100万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}
