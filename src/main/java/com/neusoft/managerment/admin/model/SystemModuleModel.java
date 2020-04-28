package com.neusoft.managerment.admin.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的系统模块Model
 */
@Alias("SystemModule")
@Data
public class SystemModuleModel {

	private int mno = 0;
	private String mname = null;
}
