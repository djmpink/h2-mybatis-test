package cn.teamstack.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
public class WebSocketEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

    @Value("${customer.log.file.path}")
    private String logPath="/Users/zhouli/project/pomelo/log-dashboard/log-dashboard-core/log/process.log";

    private Process process;
    private InputStream inputStream;

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            // 执行tail -f命令
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
