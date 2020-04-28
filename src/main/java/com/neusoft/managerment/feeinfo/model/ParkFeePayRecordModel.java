package com.neusoft.managerment.feeinfo.model;


import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的车位收费付款记录Model
 */
@Alias("ParkFeePayRecord")
@Data
public class ParkFeePayRecordModel implements Serializable {
	private int payno = 0;
	private int parkfeeno = 0;
	private int paytypeno = 0;
	private int payamount = 0;
	private Date payDate = null;
	private String payperson = null;
	private String mobile = null;
	private String invoicecode = null;
	private String paynotecode = null;
	private String paydesc = null;

}
