package com.linoer.app.interceptor;

import com.linoer.app.common.Constants;
import com.linoer.app.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizedInterceptor implements HandlerInterceptor {

    // 定义不需要拦截的请求
    private static final String[] IGNORE_URL = {"/loginForm", "/login", "/404.html"};

    /**
     * 该方法将会在Controller处理之前执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        // 不需要拦截
        for (String s: IGNORE_URL) {
            if(servletPath.contains(s)){
                flag = true;
                break;
            }
        }

        if (!flag){
            // 1：获取user session
            User user = (User) httpServletRequest.getSession().getAttribute(Constants.USER_SESSION);
            // 2：判断是否登录
            if(user == null){
                httpServletRequest.setAttribute("message", "please login first!");
                httpServletRequest.getRequestDispatcher(Constants.LOGIN).forward(httpServletRequest, httpServletResponse);
                return flag;
            }{
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 该方法只有在preHandler返回true时才执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法只有在preHandler返回true时才执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
