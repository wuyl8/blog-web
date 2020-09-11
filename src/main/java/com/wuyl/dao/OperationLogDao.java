package com.wuyl.dao;

import com.wuyl.vo.OperationLogVO;

public interface OperationLogDao {
    OperationLogVO qryOperatorLog();

    int insertOperatorLog(OperationLogVO operatorLog);
}
