package com.test.database.controller;

import com.test.database.entity.Alarm;
import com.test.database.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "告警管理")
@RestController
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @ApiOperation(value = "获取所有用户")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Alarm> getAlarmList(){
        log.info("test");
        log.debug("这是一个debug日志...");
        return alarmService.findAlarm();
    }

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/save/alarm",method = RequestMethod.POST)
    public String saveAlarm(){
        Alarm alarm = new Alarm("5","kju");
        alarmService.saveAlarm(alarm);
        return "测试数据";
    }

    @ApiOperation(value = "获取告警")
    @ApiImplicitParam(name = "id",value = "告警id",required = true,dataType = "String")
    @RequestMapping(value = "/getAlarm",method = RequestMethod.POST)
    public Alarm getAlarm(@RequestParam String id){//备注：用@RequestParam，就不用在url路径中给{}参数
        return alarmService.findAlarmById(id);
    }
    @ApiOperation("获取告警1")
    @ApiImplicitParam(name = "id",value = "告警1",required = true ,dataType = "String")
    @RequestMapping(value = "/getAlarm1/{id}",method = RequestMethod.POST)
    public Alarm getAlarm1(@PathVariable String id){//备注：用@PathVariable，就要在url路径中给参数
        return alarmService.findAlarmById(id);
    }
}
