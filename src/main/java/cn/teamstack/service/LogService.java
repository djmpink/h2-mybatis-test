package cn.teamstack.service;

import cn.teamstack.entity.LogConfig;
import cn.teamstack.repository.LogMapper;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2017/8/12.
 */
@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogMapper logMapper;

    public LogConfig getById(Integer id) {
        LogConfig logConfig =logMapper.findById(id);
        logger.info("logConfig:{}", JSON.toJSONString(logConfig));
        return logConfig;
    }

    public LogConfig add(LogConfig logConfig) {
        logMapper.insert(logConfig);
        return logConfig;
    }
}
