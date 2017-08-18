package cn.teamstack.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by zhouli on 2015/10/20.
 */
public class TokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 创建token码
     *
     * @return
     */
    public static String createToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * 创建token,并将token保存在视图与session中
     *
     * @param model
     * @param request
     * @param prefix  前缀，表示不同业务逻辑（注意请勿重复）
     * @return
     */
    public static String makeToken(Model model, HttpServletRequest request, String prefix) {
        String token = TokenUtil.createToken();
        model.addAttribute(prefix + "_token", token);
        HttpSession session = request.getSession();
        session.setAttribute(prefix + "_token", token);
        return token;
    }

    /**
     * 是否重复提交表单
     *
     * @param request
     * @param prefix  前缀，表示不同业务逻辑（注意请勿重复）
     * @return true：重复提交，false：非重复
     */
    public static boolean isRepeatSubmit(HttpServletRequest request, String prefix) {
        //获取用户token
        String client_token = request.getParameter(prefix + "_token");
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if (client_token == null) {
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute(prefix + "_token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if (server_token == null) {
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!client_token.equals(server_token)) {
            return true;
        }
        return false;
    }

    /**
     * 移除token，业务成功时调用
     *
     * @param request
     * @param prefix
     */
    public static void removeToken(HttpServletRequest request, String prefix) {
        request.getSession().removeAttribute(prefix + "_token"); //移除session中的token
    }
}
