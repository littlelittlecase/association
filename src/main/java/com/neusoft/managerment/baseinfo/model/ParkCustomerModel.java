package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的车位客户Model
 */
@Alias("parkcustomer")
@Data
public class ParkCustomerModel implements Serializable {
	private int pcno = 0;
	private int parkno = 0;
	private int customerno = 0;
	private String carcode = null;
	private Date startdate = null;
	private Date enddate = null;
	private String pcstatus = null;

}
