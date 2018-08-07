package com.cyb.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cyb on 2018/1/17.
 */
public class UserManager {

    private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();

    public static void addUser(String token, User user){
        userMap.put(token, user);
    }

    public static User getUser(String token){
        return userMap.get(token);
    }
}
