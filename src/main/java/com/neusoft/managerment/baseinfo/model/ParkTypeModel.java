package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *   模块：基础信息  表：车位类型 ParkType
 *   车位类型的Model类
 * @Author: 吕淑兰
 */
@Alias("ParkType")
@Data
public class ParkTypeModel implements Serializable {

	private int no = 0; //类型编号
	private String name = null; //类型名称
	
}
