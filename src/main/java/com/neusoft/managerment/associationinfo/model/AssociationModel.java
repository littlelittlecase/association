package com.neusoft.managerment.associationinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.neusoft.managerment.studentinfo.model.StudentModel;

import lombok.Data;
/**
 *    模块：社团基础信息   表：社团信息表 Association
 *    社团的Model类
 */
@Alias("AssociationModel")
@Data
public class AssociationModel implements Serializable {
	private String associationid = null; //社团编号
	private String associationname = null;
	private String associationleader = null;    //会长名字
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date associationtime = null;		//成立时间
	private String associationstatus = null;	//社团状态

}
