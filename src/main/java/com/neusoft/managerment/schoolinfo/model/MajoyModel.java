package com.neusoft.managerment.schoolinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
/**
 *    模块：学校基础信息   表：专业表 Majoy
 *    专业的Model类
 */
@Alias("MajoyModel")
@Data
public class MajoyModel implements Serializable {
	
	private String majoyname = null;  //专业名称
	private String majoyid = null;  //专业编号
	//外键
	private AcademyModel academy = null; //学院

}
