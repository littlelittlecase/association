package com.neusoft.managerment.schoolinfo.model;

import java.io.Serializable;


import org.apache.ibatis.type.Alias;

/**
 *    模块：学校基础信息   表：学院表 academy
 *    学院的Model类
 */

import lombok.Data;
/**
 *    模块：学校基础信息   表：学院表 Academy
 *    学院的Model类
 */
@Alias("AcademyModel")
@Data
public class AcademyModel implements Serializable {
	private String academyid = null;      // 学院编号
	private String academyname = null;   //学院名称
	private int  academycount = 0;

}
