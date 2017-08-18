package cn.teamstack.service;

import cn.teamstack.common.core.ack.ACK;
import cn.teamstack.common.core.ack.AppCode;
import cn.teamstack.common.core.exception.AppException;
import cn.teamstack.common.core.util.IdentityHelper;
import cn.teamstack.dto.response.ConfigResp;
import cn.teamstack.entity.LogConfig;
import cn.teamstack.repository.LogMapper;
import cn.teamstack.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouli on 2017/8/12.
 */
@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogMapper logMapper;

    public LogConfig getById(Integer id) {
        LogConfig logConfig = logMapper.findById(id);
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

    public List<LogConfig> getList() {
        List<LogConfig>  list=logMapper.find();
        List<ConfigResp> resps= Lists.newArrayList();
        List<ConfigResp> children= Lists.newArrayList();
        list.forEach(l->{

        });
        logger.info("list:{}",JSON.toJSONString(list));
        return list;
    }
}
