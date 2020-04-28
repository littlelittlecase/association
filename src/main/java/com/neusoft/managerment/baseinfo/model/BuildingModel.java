package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    模块：基础信息  表：楼宇  Building
 *    楼宇的Model类
 * @Author: 吕淑兰
 */
@Alias("Building")
@Data
public class BuildingModel implements Serializable {
	private int no=0; //序号
	private AreaModel area=null; //小区号
	private String code=null; //楼号
	private String address=null; //楼宇地址
	private BuildingTypeModel buildingtype=null; //楼宇结构编号
	private String direction=null;  //楼宇朝向
	private int home=0; //居民数
	private int house=0; //公建数
		
}
