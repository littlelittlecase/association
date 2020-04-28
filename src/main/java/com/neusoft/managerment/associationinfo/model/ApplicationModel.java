package com.neusoft.managerment.associationinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.neusoft.managerment.studentinfo.model.StudentModel;

import lombok.Data;
/**
 *    模块：社团审核信息   表：社团审核表 Application
 *    社团审核的Model类
 */
@Alias("ApplicationModel")
@Data
public class ApplicationModel implements Serializable {
    private String appid = null; //审核编号
	private String appname = null;   //审核社团名称
	private StudentModel student = null;    //申请学生名字
	private String appstatus = null;	//申请的社团状态
	private String reason = null; //申请理由

}
