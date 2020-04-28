package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *    模块：基础信息   表：客户 Customer
 *    author : 陈思颖
 *    version: 1.0
 */
@Alias("customer")
@Data
public class CustomerModel implements Serializable {

	private int customerno = 0;
	private CustomerTypeModel customertype = null;
	private String ccode = null;
	private String cname = null;
	private String cardcode = null;
	private String mobile = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feestartdate = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feeenddate = null;
	private String cstatus = null;
	
}
