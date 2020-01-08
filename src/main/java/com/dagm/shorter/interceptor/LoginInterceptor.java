package com.dagm.shorter.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dagm.shorter.res.BaseResult;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description: 登录检查拦截器
 * @author: Guimu
 * @create: 2018/04/18 18:54:33
 **/
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        /* //TODO 做是否登录验证，验证通过返回true。验证失败返回false。
         */
        log.info("clientIp:[{}]请求访问接口url:[{}]", request.getHeader("X-Real-Ip"),
            request.getRequestURL());
        //进行权限拦截
        return true;
    }

    private void littleAuthor(HttpServletResponse response) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        BaseResult<String> bwJsonResult = BaseResult.generateFailureRestlt("您没有权限执行此操作");
        writer.print(JSONObject.toJSONString(bwJsonResult, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat));
        response.flushBuffer();
        writer.close();
    }
}