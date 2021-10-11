package com.ay.interceptor;

import com.ay.annotation.CSRFToke;
import com.ay.enums.CSRFTokenBehavior;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.UUID;

/**
 * token 拦截器
 */
public class CSRFTokenInterceptor extends HandlerInterceptorAdapter {

    private static final String SPLIT_FLAG = "_";
    public static final String AVOID_RE_SUBMIT_TOKEN_KEY = "identifier_token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            if (isTokenBehavior((HandlerMethod) handler, CSRFTokenBehavior.CHECK)) {
                if (checkToken(request))
                    return true;
                else
                    return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (isTokenBehavior((HandlerMethod) handler, CSRFTokenBehavior.CREATE)) {
                String uuid = UUID.randomUUID().toString();
                String str = CSRFTokenInterceptor.class.getName();
                Object object = CSRFTokenInterceptor.class;
                System.out.println("uuid：" + uuid);
                Random random = new Random();
                uuid = uuid.replace(SPLIT_FLAG, String.valueOf(random.nextInt(100000)));
                String token = String.valueOf(System.currentTimeMillis());
                String transferToken = uuid + SPLIT_FLAG + token;
                request.setAttribute(AVOID_RE_SUBMIT_TOKEN_KEY, transferToken);
                request.getSession(true).setAttribute(uuid, token);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 判断：是否为Token 操作（生成token、校验token）
     *
     * @param handler
     * @param behavior token枚举
     * @return
     */
    private boolean isTokenBehavior(HandlerMethod handler, CSRFTokenBehavior behavior) {
        Method invokeMethod = handler.getMethod();
        CSRFToke CSRFToke = invokeMethod.getAnnotation(CSRFToke.class);
        if (CSRFToke != null && CSRFToke.behavior().equals(behavior))
            return true;
        return false;
    }

    /**
     * 验证 token
     *
     * @param request
     * @return
     */
    private boolean checkToken(HttpServletRequest request) {
        String clientToken = request.getParameter(AVOID_RE_SUBMIT_TOKEN_KEY);
        if (StringUtils.isEmpty(clientToken)) {
            return false;
        }

        String[] clientTokensDetail = StringUtils.split(clientToken, SPLIT_FLAG);
        if (clientTokensDetail.length == 2) {
            String uuid = clientTokensDetail[0];
            String token = clientTokensDetail[1];

            HttpSession session = request.getSession(true);
            synchronized (session) {

                String serverToken = (String) session.getAttribute(uuid);
                if (StringUtils.isNotEmpty(serverToken) && token.equals(serverToken)) {
                    request.getSession(true).removeAttribute(uuid);
                    return true;
                }
            }

        }
        return false;
    }
}
