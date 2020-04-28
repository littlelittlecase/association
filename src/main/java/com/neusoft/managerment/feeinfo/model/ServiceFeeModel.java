package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;



import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的服务费收入Model
 */
@Alias("ServiceFee")
@Data
public class ServiceFeeModel implements Serializable {

	private int feeno = 0;
	private int spno = 0;
	private int sfqty = 0;
	private int sfamount = 0;
	private String sfpayer = null;
	private String receiveuserid = null;
	private Date receivedate = null;
	private String checkuser = null;
	private Date checkdate = null;
}
