package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的服务Model
 */
@Alias("service")
@Data
public class ServiceModel implements Serializable {

	private int spno = 0;
	private int typeno = 0;
	private String spname = null;
	private double price = 0;
	private String spdesc = null;
}
