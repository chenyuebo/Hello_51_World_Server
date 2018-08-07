package com.cyb.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cyb on 2018/1/17.
 */
public class User {

    public Map<String, Object> reqNoMap;
    private Map<String, RateLimiter> rateLimiterMap;
    /**
     * 用户是否开始请求
     */
    private boolean isStarted = false;
    /**
     * 用户开始请求时间
     */
    private long startTimeSecond;

    public User(){
        reqNoMap = new ConcurrentHashMap<String, Object>();
        rateLimiterMap = new HashMap<String, RateLimiter>();
        rateLimiterMap.put("c1", RateLimiter.create(20));
        rateLimiterMap.put("c2", RateLimiter.create(20));
        rateLimiterMap.put("c3", RateLimiter.create(20));
        rateLimiterMap.put("c4", RateLimiter.create(20));
        rateLimiterMap.put("c5", RateLimiter.create(20));
    }

    public RateLimiter getRateLimiter(String channelId){
        return rateLimiterMap.get(channelId);
    }

    public boolean hasStartTimeCounter(){
        return isStarted;
    }

    public void startTimeCounter(){
        isStarted = true;
        startTimeSecond = System.currentTimeMillis() / 1000;
    }

    public boolean isOver(){
        if(hasStartTimeCounter()){
            long time = System.currentTimeMillis() / 1000 - startTimeSecond;
            boolean isOver =  time > 600;
            if(isOver){
                reqNoMap.clear();
            }
            return isOver;
        }
        return false;
    }

    public boolean isServerBusy(String channel){
        int time = (int) (System.currentTimeMillis() / 1000 - startTimeSecond);
//        if(time > 60){
//            if(channel.equals("c1")){
//                if(time > 120 && time < 150 || time > 180 && time < 210){
//                    return true;
//                }
//            }
//            if(channel.equals("c2")){
//                if(time > 120 && time < 150 || time > 180 && time < 210){
//                    return true;
//                }
//            }
//            if(channel.equals("c3")){
//                if(time > 120 && time < 180 || time > 210 && time < 270){
//                    return true;
//                }
//            }
//            if(channel.equals("c4")){
//                if(time > 120 && time < 180 || time > 210 && time < 270){
//                    return true;
//                }
//            }
//            if(channel.equals("c5")){
//                if(time > 120 && time < 270 || time > 270 && time < 390){
//                    return true;
//                }
//            }
//        }
//        if(time > 60){
//            if(channel.equals("c1")){
//                if(time > 120 && time < 150 || time > 180 && time < 270){
//                    return true;
//                }
//            }
//            if(channel.equals("c2")){
//                if(time > 120 && time < 150 || time > 180 && time < 270){
//                    return true;
//                }
//            }
//            if(channel.equals("c3")){
//                if(time > 120 && time < 180 || time > 210 && time < 330){
//                    return true;
//                }
//            }
            if(channel.equals("c4")){
                if(time > 180 && time < 240 || time > 270 && time < 390){
                    return true;
                }
            }
//            if(channel.equals("c5")){
//                if(time > 120 && time < 270 || time > 270 && time < 390){
//                    return true;
//                }
//            }
//        }
        return false;
    }
}
