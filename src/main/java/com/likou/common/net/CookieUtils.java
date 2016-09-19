package com.likou.common.net;

import com.likou.common.character.IDGen;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by jiangli on 16/9/18.
 */
public class CookieUtils {
    public static String MD5 = "lijiang";
    private static Random random = new Random();
    /**
     * 添加cookie
     * @param response
     * @param path
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response,String path ,String name, String value){
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    /**
     * 修改cookie
     * @param request
     * @param response
     * @param path
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。
     * 否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    public static void editCookie(HttpServletRequest request, HttpServletResponse response,String path , String name, String value){
        Cookie[] cookies = request.getCookies();
        if (null!=cookies){
            for(Cookie cookie : cookies){
                if(cookie.getName().trim().equals(name.trim())){
                    addCookie(response,path,name,value);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletRequest request,HttpServletResponse response, String path ,String name){
        Cookie[] cookies = request.getCookies();
        if (null!=cookies) {
            for(Cookie cookie : cookies){
                if(cookie.getName().trim().equals(name.trim())){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath(path);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * 获取指定的cookie值
     * @param request
     * @param name
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if (null!=cookies) {
            for(Cookie cookie : cookies){
                if(cookie.getName().trim().equals(name.trim())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param response
     * @param domain
     */
    public static void addCookieForLogin(HttpServletResponse response, String domain){
        int index = random.nextInt(32);
        String sessionID = DigestUtils.md5Hex(IDGen.get32ID());
        StringBuffer sb = new StringBuffer(sessionID);
        sb.insert(index,MD5);
        String t = DigestUtils.md5Hex(sb.toString());
        String i = Integer.toString(index);

        CookieUtils.addCookie(response,"/","i",i);
        CookieUtils.addCookie(response,"/","t",t);
        CookieUtils.addCookie(response,"/","sessionID",sessionID);
//        response.setHeader("Set-Cookie", "i="+i+";Path=/;Domain=mydning.com;Max-Age=1800;HttpOnly");
//        response.setHeader("Set-Cookie", "t="+t+";Path=/;Domain=mydning.com;Max-Age=1800;HttpOnly");
//        response.setHeader("Set-Cookie", "sessionID="+sessionID+";Path=/;Domain=mydning.com;Max-Age=1800;HttpOnly");

    }
}
