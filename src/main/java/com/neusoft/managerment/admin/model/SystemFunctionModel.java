package com.neusoft.managerment.admin.model;

import org.apache.ibatis.type.Alias;



import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的系统功能Model
 */
@Alias("SystemFunction")
@Data
public class SystemFunctionModel {

	private int funno = 0;
	private int mno = 0;
	private String funame = null;
	private String funurl = null;
	private int levelno = 0; 
	
}
