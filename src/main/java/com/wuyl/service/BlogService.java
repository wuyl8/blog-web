package com.wuyl.service;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //查询出用户全部基本信息
    Map qryUserById(int id);

    List qryUserDetail();
}
