package com.wuyl.service.thread;

import com.wuyl.dao.OperationLogDao;
import com.wuyl.utils.SpringContextUtils;
import com.wuyl.vo.OperationLogVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OperationLogThread extends Thread {
    private Logger logger = LogManager.getLogger();
    private OperationLogVO operationLogVO;

    public OperationLogThread(){

    }
    public OperationLogThread(OperationLogVO operationLogVO){
        super();
        this.operationLogVO = operationLogVO;
    }

    public void run(){
        super.run();
        try{
            OperationLogDao operationLogDao = (OperationLogDao) SpringContextUtils.getBean("operationLogDao");
            operationLogDao.insertOperatorLog(operationLogVO);
            System.out.println("自增的主键："+operationLogVO.getId());
        }catch (Exception e){
            logger.error("OperationLogThread error!",e);
        }
    }
}
