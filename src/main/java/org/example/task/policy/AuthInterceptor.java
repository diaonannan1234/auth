package org.example.task.policy;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;




public class AuthInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        request.setAttribute("auth_user_ame","alice1");
        request.setAttribute("auth_url",request.getRequestURI());
        request.setAttribute("auth_act",request.getMethod());
       return  true;
    }
}
