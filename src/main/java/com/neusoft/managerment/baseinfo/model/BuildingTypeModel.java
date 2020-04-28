package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
/**
 *   模块：基础信息  表：建筑类型 BuildingType
 *   建筑类型的Model类
 * @Author: 吕淑兰
 */

@Alias("buildingtype")
@Data
public class BuildingTypeModel implements Serializable {

	private String no=null; //类型编号
	private String name=null; //类型名称
	
	
}
