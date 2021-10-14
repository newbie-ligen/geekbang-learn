package com.sqz.springmultidatasource.aop;

import com.sqz.springmultidatasource.datasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SwitchDataSourceAOP {

    // 这里切到你的方法目录
    @Around("execution(* com.sqz.springmultidatasource.service.*.*(..))")
    public Object process(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName();
        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")
                || methodName.startsWith("query")) {
            // 读
            DataSourceContextHolder.setDbType("readDataSource");
        } else {
            // 切换dataSource
            DataSourceContextHolder.setDbType("writeDataSource");
        }
        Object proceed = null;
        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        DataSourceContextHolder.clearDbType();
        return proceed;
    }

}
