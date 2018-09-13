package com.test.database.service.impl;

import com.test.database.dao.AlarmDao;
import com.test.database.entity.Alarm;
import com.test.database.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly=false,propagation= Propagation.REQUIRES_NEW)
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    @Override
    public List<Alarm> findAlarm() {
        return alarmDao.findAlarm();
    }

    @Override
    public void saveAlarm(Alarm alarm) {
        alarmDao.saveAlarm1(alarm);
        alarm.setId("dddsfee343434fee");
        alarmDao.saveAlarm2(alarm);
    }

    @Override
    public Alarm findAlarmById(String id) {
        return alarmDao.findAlarmById(id);
    }
}
