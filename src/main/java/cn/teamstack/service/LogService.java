package cn.teamstack.service;

import cn.teamstack.common.core.ack.ACK;
import cn.teamstack.common.core.ack.AppCode;
import cn.teamstack.common.core.bean.PageResponse;
import cn.teamstack.common.core.exception.AppException;
import cn.teamstack.common.core.util.IdentityHelper;
import cn.teamstack.dto.request.ConfigReq;
import cn.teamstack.entity.LogConfig;
import cn.teamstack.repository.LogMapper;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouli on 2017/8/12.
 */
@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogMapper logMapper;

    public LogConfig getById(String logId) {
        LogConfig logConfig = logMapper.findById(logId);
        logger.info("logConfig:{}", JSON.toJSONString(logConfig));
        return logConfig;
    }

    public LogConfig add(LogConfig logConfig) {
        if (logConfig == null) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        if (StringUtils.isEmpty(logConfig.name)) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        if (StringUtils.isEmpty(logConfig.path)) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        logConfig.logId = IdentityHelper.createId();
        logConfig.createTime = new Date();
        logConfig.modifyTime = new Date();
        logConfig.isValid = true;
        logMapper.insert(logConfig);
        return logConfig;
    }

    public void edit(String logId, LogConfig config) {
        if (config == null) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        if (StringUtils.isEmpty(logId)) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        if (StringUtils.isEmpty(config.name)) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        if (StringUtils.isEmpty(config.path)) {
            throw new AppException(AppCode.MAPI_CODE, ACK.REQUEST_PARAMETER_MISS);
        }
        logger.info("config:{}", JSON.toJSONString(config));
        LogConfig logConfig = logMapper.findById(logId);
        logConfig.ip = config.ip;
        logConfig.name = config.name;
        logConfig.path = config.path;
        logConfig.tags = config.tags;
        logConfig.createTime = new Date();
        logConfig.modifyTime = new Date();
        logger.info("logConfig:{}", JSON.toJSONString(logConfig));
        logMapper.update(logConfig);
    }

    public void remove(String logId) {
        logMapper.delete(logId);
    }

    public PageResponse list(ConfigReq configReq) {
        if (configReq == null) {
            configReq = new ConfigReq();
            configReq.setPage(1);
            configReq.setPageSize(20);
        } else if (configReq.getPage() == null
                || configReq.getPageSize() == null) {
            configReq.setPage(1);
            configReq.setPageSize(20);
        }

        long total = logMapper.count();
        List<LogConfig> list = logMapper.find(configReq.getOffset(), configReq.getPageSize());
        return new PageResponse<>(list, total, configReq.getPage(), configReq.getPageSize());
    }

    public List<LogConfig> dropdown() {
        return logMapper.find(null, null);
    }

    public List<String> getFiles(String logId) {
        LogConfig logConfig = logMapper.findById(logId);
        String path = logConfig.path; // 路径
        logger.info("path：{}", path);
        List<String> files = Lists.newArrayList();
        if (path.endsWith(".log")) {
            String file = path.substring(path.lastIndexOf("/") + 1);
            files.add(file);
            return files;
        }

        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return files;
        }

        File fa[] = f.listFiles();
        if (fa == null || fa.length == 0) {
            System.out.println("file not exists");
            return files;
        }
        for (File fs : fa) {
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
                System.out.println(fs.getName());
                if (fs.getName().endsWith(".log")) {
                    files.add(fs.getName());
                }
            }
        }
        return files;
    }
}
