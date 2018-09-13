package com.test.database.service;

import com.test.database.entity.Alarm;

import java.util.List;

public interface AlarmService {

    public List<Alarm> findAlarm();

    public void saveAlarm(Alarm alarm);

    public Alarm findAlarmById(String id);
}
