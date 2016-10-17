package com.likou.common.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by home on 2016/10/18.
 */
public class RequestUtils {

    public static String getRequstURL(HttpServletRequest request){

        StringBuffer requestURL = request.getRequestURL();
        return requestURL.toString();
    }
}
