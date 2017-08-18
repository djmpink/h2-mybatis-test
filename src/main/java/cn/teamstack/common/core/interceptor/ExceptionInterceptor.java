package cn.teamstack.common.core.interceptor;


import com.alibaba.fastjson.JSON;
import cn.teamstack.common.core.bean.Response;
import cn.teamstack.common.core.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;

public class ExceptionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
//            if(NetworkUtils.isAjaxRequest(request)){//限制ajax请求
//                processException(response, ex);
//            }
            processException(response, ex);
        }
    }

    public static void processException(HttpServletResponse response, Throwable ex) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        byte[] error = JSON.toJSONBytes(errorResponseDTO(ex));
        response.setContentLength(error.length);
        try (OutputStream os = response.getOutputStream()) {
            os.write(error);
        }
    }

    @NotNull
    private static Response errorResponseDTO(Throwable ex) {
//        logger.projectInfo("request error:", ex);
        if (ex instanceof AppException) {
            AppException exception = (AppException) ex;
            logger.info("[业务异常][AppException][code:{}],[msg:{}]", exception.code, exception.msg);
            return Response.failed(exception.code, exception.msg);
        }
        return Response.error();
    }
}