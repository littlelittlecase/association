package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的房间缴费Model
 */
@Alias("RoomFee")
@Data
public class RoomFeeModel implements Serializable {
	private int roomfeeno = 0;
	private String feeyear = null;
	private String feemonth = null;
	private int roomno = 0;
	private int feeitemno = 0;
	private int spay = 0;
	private int apay = 0;
	private int derate = 0;
	private Date startdate = null; 
	private Date enddate = null;
	private String feestatus = null;

}
