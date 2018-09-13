package com.test.database.dao.impl;

import com.test.database.dao.AlarmDao;
import com.test.database.entity.Alarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AlarmDaoImpl implements AlarmDao{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Alarm> findAlarm(){
        String sql = "select id,userName from t_user";
        Long st = new Date().getTime();
        List<Alarm> alarmList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Alarm.class));
        Long et = new Date().getTime();
        logger.info(String.valueOf(et -st )+"毫秒");
        return alarmList;
    }

    @Override
    public Alarm findAlarmById(String id) {
        String sql = "select * from t_user where id = ?";
        Alarm alarm = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Alarm.class),id);
        return alarm;
    }

    @Override
    public void saveAlarm1(Alarm alarm) {
        String sql = "insert into t_user(id,userName) VALUES (?,?)";
        Object val[] = {alarm.getId(),alarm.getUserName()};
        Long st = new Date().getTime();
        jdbcTemplate.update(sql,val);
        Long et = new Date().getTime();
        logger.info(String.valueOf( et - st )+"毫秒");
    }

    @Override
    public void saveAlarm2(Alarm alarm) {
        String sql = "insert into t_user(id,userName) VALUES (?,?)";
        Object val[] = {alarm.getId(),alarm.getUserName()};
        Long st = new Date().getTime();

        jdbcTemplate.update(sql,val);
        Long et = new Date().getTime();
        logger.info(String.valueOf(et - st));
    }


}
