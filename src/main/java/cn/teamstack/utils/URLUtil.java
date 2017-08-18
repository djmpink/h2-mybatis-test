package cn.teamstack.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * URL工具类
 *
 * @author LZQ
 */
public class URLUtil {
    /**
     * 获取没有后缀的uri
     *
     * @param uri
     * @return
     */
    public static final String getURIWithoutSuffix(String uri) {
        if (uri == null || "".equals(uri.trim())) {
            return uri;
        }

        int pointIndex = uri.indexOf(".");
        if (pointIndex == -1) {
            return uri;
        }

        return uri.substring(0, pointIndex);
    }

    /**
     * 获取没有后缀的uri
     *
     * @param url
     * @param contextPath
     * @return
     */
    public static final String getURIWithoutSuffix(String url, String contextPath) {
        if (url == null || "".equals(url.trim())) {
            return url;
        }

        int contextPathPonit = url.indexOf(contextPath);

        if (contextPathPonit != -1) {
            url = url.substring(contextPathPonit);
        }

        int pointIndex = url.indexOf(".");

        if (pointIndex == -1) {
            return url;
        } else {
            return url.substring(0, pointIndex);
        }
    }

    /**
     * 获取没有contextPath的uri
     *
     * @param uri
     * @param request
     * @return
     */
    public static final String getURLWithoutContextPath(String uri, HttpServletRequest request) {
        if (uri == null || "".equals(uri.trim())) {
            return uri;
        }

        String contextPath = request.getContextPath();
        if (contextPath == null || "".equals(contextPath.trim())) {
            return uri;
        }

        if (!uri.startsWith(contextPath)) {
            throw new RuntimeException("url does not contain contextPath !");
        }

        return uri.substring(contextPath.length());
    }

    /**
     * 是否是json请求
     *
     * @return
     */
    public static final boolean isAjaxUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();

        int suffixIndex = uri.lastIndexOf(".");
        if (suffixIndex != -1) {
            String suffix = uri.substring(suffixIndex + 1);
            if ("json".equals(suffix) || "jsonp".equals(suffix)) {
                return true;
            }
        }

        String format = request.getParameter("format");
        if ("json".equals(format) || "jsonp".equals(format)) {
            return true;
        }

        String accept = request.getHeader("Accept");
        if (accept.contains("application/json") || accept.contains("application/jsonp")) {
            return true;
        }

        return false;
    }

    public static final boolean isIndexPage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.equals(request.getContextPath() + "/") || uri.equals(request.getContextPath() + "/index")) {
            return true;
        }

        return false;
    }

    public static final boolean isJsonp(HttpServletRequest request) {
        String format = request.getParameter("format");
        if ("jsonp".equals(format)) {
            return true;
        }

        String accept = request.getHeader("Accept");
        if (accept.contains("application/jsonp")) {
            return true;
        }

        return false;
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }

        return String.valueOf(ip);
    }
}
