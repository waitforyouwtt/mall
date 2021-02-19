package com.mowa.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param domain
     * @param path
     * @param name
     * @param value
     * @param maxAge
     * @param httpOnly
     */
    public static void addCookie(HttpServletResponse response, String domain, String path, String name,
                          String value, int maxAge, boolean httpOnly){

        Cookie cookie = new Cookie(name,value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    /**
     * 根据cookie 名称获取cookie
     * @param request
     * @param cookieNames
     * @return
     */
    public static Map<String,String> readCookie(HttpServletRequest request,String ... cookieNames){
        Map<String,String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                for (int i=0;i<cookieNames.length;i++){
                    if (cookieNames[i].equals(cookieName)){
                        cookieMap.put(cookieName,cookieValue);
                    }
                }
            }
        }
        return cookieMap;
    }
}
