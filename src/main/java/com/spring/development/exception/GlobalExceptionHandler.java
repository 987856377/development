package com.spring.development.exception;

import com.spring.development.aspect.LogAspect;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.exception
 * @Author xuzhenkui
 * @Date 2020/4/22 9:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 处理空指针的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ResultJson exceptionHandler(HttpServletRequest request, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }

    /**
     * SQL 执行异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public ResultJson sqlExceptionHandler(HttpServletRequest request, NullPointerException e){
        logger.error("SQL 执行异常！原因是:",e);
        return ResultJson.failure(ResultCode.SERVICE_UNAVAILABLE);
    }


    /**
     * 处理其他异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public ResultJson exceptionHandler(HttpServletRequest request, Exception e){
        logger.error("未知异常！原因是:",e);
        return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
