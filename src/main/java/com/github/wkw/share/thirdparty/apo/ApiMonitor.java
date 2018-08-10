package com.github.wkw.share.thirdparty.apo;

import com.alibaba.fastjson.JSONObject;
import com.github.wkw.share.Constants;
import com.mysql.jdbc.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.util.locale.LocaleUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by GoGo on  2018/8/10
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Aspect
@Component
public class ApiMonitor {
    private static final Logger logger = LoggerFactory.getLogger(ApiMonitor.class);


    ThreadLocal<Long> startTime = new ThreadLocal<Long>();


    @Pointcut("execution(public * com.github.wkw..*.*controller.*Controller.*(..))")
    @Order(1)
    public void controllerPoint() {
    }

    @Before("controllerPoint()")
    public void doBeforeControllerPoint(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        logger.info("<====================================================================");
        String temp = request.getHeader(Constants.HTTP_HEADER.APP_ID);
        if (!StringUtils.isNullOrEmpty(temp)) {
            logger.info("表头信息:：APP_ID =>" + temp);
        }

        temp = request.getHeader(Constants.HTTP_HEADER.APP_VERSION);
        if (!StringUtils.isNullOrEmpty(temp)) {
            logger.info("表头信息：APP_VERSION =>" + temp);
        }

        temp = request.getHeader(Constants.HTTP_HEADER.APP_MODEL);
        if (!StringUtils.isNullOrEmpty(temp)) {
            logger.info("表头信息：APP_MODEL =>" + temp);
        }

        temp = request.getHeader(Constants.HTTP_HEADER.NETWORK);
        if (!StringUtils.isNullOrEmpty(temp)) {
            logger.info("表头信息：NETWORK =>" + temp);
        }

        temp = request.getHeader(Constants.HTTP_HEADER.LANGUAGE);

        logger.info("请求来源:  => " + request.getRemoteAddr());
        logger.info("请求URL: " + request.getRequestURL().toString());
        logger.info("请求方式: " + request.getMethod());
        logger.info("响应方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("---------------------------------------------------------------------");
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "controllerPoint()", returning = "rst")
    public void doAfterControllerPoint(JoinPoint joinPoint, Object rst) {
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));

        logger.info("返回数据: {}", JSONObject.toJSON(rst).toString());
        logger.info("====================================================================>");
    }
}
