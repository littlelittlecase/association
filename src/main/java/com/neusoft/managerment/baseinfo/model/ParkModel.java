package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *   模块：基础信息  表：车位 Park
 *   车位的Model类
 * @Author: 吕淑兰
 */
@Alias("park")
@Data
public class ParkModel implements Serializable {

	private int no = 0; //车位序号
	private ParkTypeModel parkType=null; //类型编号
	private String code = null; //车位编码
	private BuildingModel building=null; //楼号
	private String location = null; //位置
	private BigDecimal area = null; //面积
	private String parkstatus = null; //是否关联客户(Y/N)
	private BigDecimal rentprice = null; //出租价格
	private String rentunit = null; //租赁单位(天,月,季,年)
	private String feestatus = null; //收费状态:Y收费,N:不收费
	
}
