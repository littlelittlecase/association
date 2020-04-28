package com.neusoft.managerment.admin.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息模块的系统操作员Model
 */
@Alias("AdminUser")
@Data
public class UserInfoModel {

	private String uuserid = null;
	private String upassword = null;
	private String uname = null;
	private String userstatus = null;
}
