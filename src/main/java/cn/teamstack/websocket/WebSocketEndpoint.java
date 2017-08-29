package cn.teamstack.websocket;

import cn.teamstack.dto.request.WebSocketReq;
import cn.teamstack.service.LogService;
import cn.teamstack.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketEndpoint.applicationContext = applicationContext;
    }

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("新的WebSocket请求开启 session:" + session.getId());
    }

    /**
     * 接受发来的请求
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        if (StringUtils.isNotEmpty(message)) {
            WebSocketReq req = JSON.parseObject(message, WebSocketReq.class);
            logger.info("req:{}", JSON.toJSONString(req));
            switch (req.order) {
                case "tail":
                    tailLogs(req, session);
                    break;
                case "cmd":
                    cmdInfo(req, session);
                    break;
                case "search":
                    search(req, session);
                    break;
            }
        }
    }

    // 执行tail -f命令
    private void tailLogs(WebSocketReq req, Session session) {
        System.out.println("执行tail -f命令:" + req.content);
        if (StringUtils.isEmpty(req.content)) {
            return;
        }
        LogService logService = (LogService) applicationContext.getBean("logService");
        String logPath = logService.getById(req.id).path;
        logger.info("日志路径：{}", logPath);
        try {
            process = Runtime.getRuntime().exec("tail -50f " + logPath);
            inputStream = process.getInputStream();
            // 启动新的线程，防止InputStream阻塞处理WebSocket的线程
            SendThread thread = new SendThread(inputStream, session);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cmdInfo(WebSocketReq req, Session session) {
        try {
            if (StringUtils.isEmpty(req.content)) {
                return;
            }
            String cmd=req.content;
            LogService logService = (LogService) applicationContext.getBean("logService");
            String logPath = logService.getById(req.id).path;
            logPath = logPath.substring(0, logPath.lastIndexOf("/"));
            logger.info("日志路径：{}", logPath);
            File dir = new File(logPath);//只能在日志目录执行对应命令

            List<String> cmds = Lists.newArrayList();
            cmds.add("/bin/sh");
            cmds.add("-c");
            cmds.add(cmd);

            String[] c = cmds.toArray(new String[cmds.size()]);
            logger.info("c:{}", JSON.toJSONString(c));
            process = Runtime.getRuntime().exec(c, null, dir);

            InputStream inputStream = process.getInputStream();

            // 启动新的线程，防止InputStream阻塞处理WebSocket的线程
            SendThread thread = new SendThread(inputStream, session);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void search(WebSocketReq req, Session session) {
        try {
            if (StringUtils.isEmpty(req.content)) {
                return;
            }
            LogService logService = (LogService) applicationContext.getBean("logService");
            String logPath = logService.getById(req.id).path;
            logger.info("日志路径：{}", logPath);

            List<String> cmds = Lists.newArrayList();
            cmds.add("/bin/sh");
            cmds.add("-c");
            cmds.add("grep " + req.content + " " + logPath);

            String[] c = cmds.toArray(new String[cmds.size()]);
            logger.info("c:{}", JSON.toJSONString(c));
            process = Runtime.getRuntime().exec(c, null, null);

            InputStream inputStream = process.getInputStream();

            // 启动新的线程，防止InputStream阻塞处理WebSocket的线程
            SendThread thread = new SendThread(inputStream, session);
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
        if (process != null) {
            process.destroy();
        }
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }

}
