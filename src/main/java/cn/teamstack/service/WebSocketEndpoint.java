package cn.teamstack.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhouli on 2017/8/14.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketEndpoint implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

    private Process process;
    private InputStream inputStream;

    private static ApplicationContext applicationContext = null;

    private LogService logService;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketEndpoint.applicationContext = applicationContext;
    }



    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            // 执行tail -f命令
            logger.info("session:{},query:{}", JSON.toJSONString(session.getId()),JSON.toJSONString(session.getQueryString()));
            LogService logService= (LogService) applicationContext.getBean("logService");
            String logPath = logService.getById(2).path;
            logger.info("日志路径：{}", logPath);
            process = Runtime.getRuntime().exec("tail -f " + logPath);
            inputStream = process.getInputStream();

            // 启动新的线程，防止InputStream阻塞处理WebSocket的线程
            WebLogThread thread = new WebLogThread(inputStream, session);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
        try {
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (process != null)
            process.destroy();
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }


}
