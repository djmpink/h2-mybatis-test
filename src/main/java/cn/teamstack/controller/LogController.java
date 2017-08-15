package cn.teamstack.controller;

import cn.teamstack.entity.DemoEntity;
import cn.teamstack.entity.LogConfig;
import cn.teamstack.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhouli on 2017/8/15.
 */
@RestController
@RequestMapping("/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Resource
    private LogService logService;

    @ApiOperation(value = "根据id获取日志对象", notes = "根据id获取日志对象")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public LogConfig getById(@PathVariable("id") Integer id) {

        logger.info("getById：{}", id);
        return logService.getById(id);
    }

    @ApiOperation(value = "新增日志配置", notes = "新增Demo对象")
    @RequestMapping(value = "config/add",method = RequestMethod.POST)
    public LogConfig add(@RequestBody LogConfig logConfig) {
        return logService.add(logConfig);
    }
}
