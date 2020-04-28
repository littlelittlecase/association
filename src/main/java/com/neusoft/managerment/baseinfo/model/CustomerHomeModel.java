package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *    模块：基础信息   表：客户房间 CustomerHome
 *    author : 陈思颖
 *    version: 1.0
 */
@Alias("customerhome")
@Data
public class CustomerHomeModel implements Serializable {

	private int chno = 0;
	private CustomerModel customer = null;
	private RoomModel room = null;
	private LivingTypeModel livingtype = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date livedate = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date receivedate = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feestartdate = null;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feeenddate = null;
	private int humancount = 0;	
}
