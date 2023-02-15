package com.souls.utils;

import com.souls.vo.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Aspect
public class QueryAspectInfo {
    @Resource
    RedisUtil redisUtil = null;
    //使用一个返回值为空并且方法体为空的函数来定义切入点
    @Pointcut("execution(* com.souls.service.impl.*.query*(..))")
    private void myPointCut(){}

    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("开始分页查询。。。");

        //拿到被切入方法的类的全限定名及其方法名
        String method = joinPoint.getTarget().getClass().getName() + "-" + joinPoint.getSignature().getName();
        StringBuffer stf = new StringBuffer(1000);

        stf.append(method).append("?");

        //获取调用方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 1; i < args.length; i++) {
            stf.append(args[i].getClass().getName()).append("=");
            stf.append(args[i]).append("&");
        }

        System.out.println(stf);

        //第一个参数是pageInfo
        PageInfo pageInfo = (PageInfo) args[0];
        if(pageInfo.getUuid() == null||"".equals(pageInfo.getUuid())){
            String uuid = QueryPools.searchUUidByMethod(stf.toString(),redisUtil);
            if (uuid==null){
                Object resultData = joinPoint.proceed();//直接调用原方法

                uuid = UUID.randomUUID().toString();
                QueryPools.addQueryData(uuid,(List<Object>)resultData,redisUtil);
                QueryPools.addQueryMethod(stf.toString(),uuid,redisUtil);
            }
            pageInfo.setUuid(uuid);
        }
        return QueryPools.getDataFromPools(pageInfo,redisUtil);
    }

}
