package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的维修单位Model
 */
@Alias("MaintainProvider")
@Data
public class MaintainProviderModel implements Serializable {
	private int providerno = 0; //单位序号
	private String pname = null;//单位名称
	private String pcontact = null;//联系人
	private String paddress = null;//单位地址
	private String pmobile = null;//手机
	private String tel = null;//固定电话
	
}
