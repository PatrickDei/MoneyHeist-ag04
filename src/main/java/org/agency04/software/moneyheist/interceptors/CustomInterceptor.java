package org.agency04.software.moneyheist.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomInterceptor implements HandlerInterceptor {

    public String lastUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        System.out.println(request.getRequestURI());

        this.lastUrl = request.getRequestURI();

        return true;
    }

    public Integer getLastUrlId(){
        return (this.lastUrl.startsWith("/member/") || this.lastUrl.startsWith("/heist/")) ? Integer.valueOf(this.lastUrl.split("/")[2]) : null;
    }
}