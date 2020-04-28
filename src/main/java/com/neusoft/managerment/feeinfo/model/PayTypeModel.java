package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的付款Model
 */
@Alias("PayType")
@Data
public class PayTypeModel implements Serializable {
	private int typeno = 0;
	private String typename = null;

}
