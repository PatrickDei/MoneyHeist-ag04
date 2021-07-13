package org.agency04.software.moneyheist.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class FindIdentityByUrlInterceptor implements HandlerInterceptor {

    public String lastIdentity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        System.out.println(request.getRequestURI());

        this.lastIdentity = request.getRequestURI();

        return true;
    }

    public Integer getLastUrlId(){
        return (this.lastIdentity.startsWith("/member/") || this.lastIdentity.startsWith("/heist/")) ? Integer.valueOf(this.lastIdentity.split("/")[2]) : null;
    }
}