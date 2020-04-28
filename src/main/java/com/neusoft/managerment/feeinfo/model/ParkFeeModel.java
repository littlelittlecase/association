package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
   *   系统基础信息模块的停车位缴费Model
 */
@Alias("ParkFee")
@Data
public class ParkFeeModel implements Serializable {
	private int parkfeeno = 0;
	private String feeyear = null;
	private String feemonth = null;
	private int spay = 0;
	private int derate = 0;
	private Date stardate = null;
	private Date enddate = null;
	private String feestatus = null;

}
