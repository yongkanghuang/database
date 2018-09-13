package com.test.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alarm implements Serializable{

    private String id;
    private String userName;

}
