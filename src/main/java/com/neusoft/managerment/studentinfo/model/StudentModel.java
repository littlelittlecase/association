package com.neusoft.managerment.studentinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.neusoft.managerment.associationinfo.model.AssociationModel;
import com.neusoft.managerment.office.model.NewsModel;
import com.neusoft.managerment.schoolinfo.model.AcademyModel;
import com.neusoft.managerment.schoolinfo.model.MajoyModel;

import lombok.Data;


/**
 *    author : 张梓琪
 *    version: 1.0
 *   学生基础信息的Model
 */
@Alias("StudentModel")
@Data
public class StudentModel implements Serializable {

	private String studentid = null;   //学生编号
    private String studentname = null;   //学生名字
	private String sex = null;      	//学生性别
	//外键academyname
	private AcademyModel academy =null;    //学院名称
	//外键majoyname
	private MajoyModel majoy = null;		//专业名称
	private String studentphone = null;		//学生电话
	//外键associationname
	private AssociationModel association = null;	//社团名称
	
	
	
}
