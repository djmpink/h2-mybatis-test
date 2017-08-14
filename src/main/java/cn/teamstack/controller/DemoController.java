package cn.teamstack.controller;

import cn.teamstack.entity.DemoEntity;
import cn.teamstack.service.DemoService;
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
 * Created by zhouli on 2017/8/12.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private DemoService demoService;

    @ApiOperation(value = "根据id获取Demo对象", notes = "根据id获取Demo对象")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public DemoEntity getDemoById(@PathVariable("id") String id) {

        logger.info("getDemoById：{}", id);
        return demoService.getById(id);
    }

    @ApiOperation(value = "新增Demo对象", notes = "新增Demo对象")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public DemoEntity addDemo(@RequestBody DemoEntity demoEntity) {
        logger.info("addDemo");
        return demoService.add(demoEntity);
    }
}