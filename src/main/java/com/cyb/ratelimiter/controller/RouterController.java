package com.cyb.ratelimiter.controller;

import com.cyb.ratelimiter.User;
import com.cyb.ratelimiter.UserManager;
import com.cyb.ratelimiter.utils.MD5;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by cyb on 2018/1/4.
 */
@Controller
public class RouterController {

    private Logger logger = Logger.getLogger(RouterController.class);

    @Value("${c1DelayMs}")
    private int c1DelayMs;
    @Value("${c2DelayMs}")
    private int c2DelayMs;
    @Value("${c3DelayMs}")
    private int c3DelayMs;
    @Value("${c4DelayMs}")
    private int c4DelayMs;
    @Value("${c5DelayMs}")
    private int c5DelayMs;

    @RequestMapping("/getNewToken")
    @ResponseBody
    public String getNewToken(ModelMap modelMap){
        String token = MD5.md5(System.currentTimeMillis() + "");
        UserManager.addUser(token, new User());
        return token;
    }

    // c1/apply.htm?reqNo=1&token=xxxx
    @RequestMapping("/c1/apply.htm")
    @ResponseBody
    public String apply1(String reqNo, String token, HttpServletRequest request, HttpServletResponse response){
        return handleRequest("c1", reqNo, token, request, response);
    }

    @RequestMapping("/c2/apply.htm")
    @ResponseBody
    public String apply2(String reqNo, String token, HttpServletRequest request, HttpServletResponse response){
        return handleRequest("c2", reqNo, token, request, response);
    }

    @RequestMapping("/c3/apply.htm")
    @ResponseBody
    public String apply3(String reqNo, String token, HttpServletRequest request, HttpServletResponse response){
        return handleRequest("c3", reqNo, token, request, response);
    }

    @RequestMapping("/c4/apply.htm")
    @ResponseBody
    public String apply4(String reqNo, String token, HttpServletRequest request, HttpServletResponse response){
        return handleRequest("c4", reqNo, token, request, response);
    }

    @RequestMapping("/c5/apply.htm")
    @ResponseBody
    public String apply5(String reqNo, String token, HttpServletRequest request, HttpServletResponse response){
        return handleRequest("c5", reqNo, token, request, response);
    }

    private String handleRequest(String channel, String reqNo, String token, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();

        // 根据用户的延时设置进行延时，线程sleep
//        sleep(channel);

        StringBuilder sb = new StringBuilder();
        sb.append("reqNo:").append(reqNo).append(",token:").append(token);

        // 判断token对应用户是否存在
        User user = UserManager.getUser(token);
        if(user == null){
            response.setStatus(401);
            return "请求校验失败";
        }
        if(user.isOver()){
            response.setStatus(502);
            return "比赛已结束";
        }
        // 验证请求码reqNo是否在1-30000区间内
        try{
            int no = Integer.parseInt(reqNo);
            if(no < 1 && no > 30000){
                response.setStatus(401);
                return "请求校验失败";
            }
//            if(no == 400){
//                response.setStatus(401);
//                return "彩蛋";
//            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(401);
            return "请求校验失败";
        }
        // 判断用户是否第一次请求，如果是第一次，则开始计时
        if(!user.hasStartTimeCounter()){
            user.startTimeCounter();
        }
        if(user.isServerBusy(channel)){
            response.setStatus(500);
            return "系统繁忙";
        }

        // 判断是否限流
        RateLimiter rateLimiter = user.getRateLimiter(channel);
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            response.setStatus(403);
            return "服务限流";
        }
        // 判断是否重复请求
        if (user.reqNoMap.containsKey(reqNo)) {
            response.setStatus(400);
            return "重复请求";
        }
        // 根据用户的延时设置进行延时，线程sleep
        sleep(channel);
        user.reqNoMap.put(reqNo, "");
        long end = System.currentTimeMillis();
        String result = end - start + "";
        sb.append(",返回值：" + result);
        logger.info(sb.toString());
        return result;

    }

    public void sleep(String channel){
        try {
            if ("c1".equals(channel)) {
                Thread.sleep(c1DelayMs);
            } else if ("c2".equals(channel)) {
                Thread.sleep(c2DelayMs);
            } else if ("c3".equals(channel)) {
                Thread.sleep(c3DelayMs);
            } else if ("c4".equals(channel)) {
                Thread.sleep(c4DelayMs);
            } else if ("c5".equals(channel)) {
                Thread.sleep(c5DelayMs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
