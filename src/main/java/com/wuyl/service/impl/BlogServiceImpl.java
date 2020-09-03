package com.wuyl.service.impl;

import com.wuyl.dao.UserDao;
import com.wuyl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private UserDao blogDao;

    @Override
    public Map qryUserById(int id) {
        return null;
    }

    @Override
    public List qryUserDetail() {
        return blogDao.qryUserDetail();
    }
}
