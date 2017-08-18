package cn.teamstack.controller;

import cn.teamstack.common.core.bean.Response;
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

    @ApiOperation(value = "获取日志配置列表", notes = "获取日志配置列表")
    @RequestMapping( value = "config/list",method = RequestMethod.GET)
    public Response getList() {
        return Response.success(logService.getList());
    }

    //获取日志配置详情

    @ApiOperation(value = "新增日志配置", notes = "新增日志配置")
    @RequestMapping(value = "config/add",method = RequestMethod.POST)
    public Response add(@RequestBody LogConfig logConfig) {
        return Response.success(logService.add(logConfig));
    }

    //编辑日志配置

    //日志文件下载

    //执行命令行

    //搜索关键词


    @ApiOperation(value = "根据id获取日志对象", notes = "根据id获取日志对象")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public LogConfig getById(@PathVariable("id") Integer id) {

        logger.info("getById：{}", id);
        return logService.getById(id);
    }


}
