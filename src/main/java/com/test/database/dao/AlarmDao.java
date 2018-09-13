package com.test.database.dao;

import com.test.database.entity.Alarm;

import java.util.List;

public interface AlarmDao {

    public List<Alarm> findAlarm();

    public Alarm findAlarmById(String id);

    public void saveAlarm1(Alarm alarm);

    public void saveAlarm2(Alarm alarm);
}
