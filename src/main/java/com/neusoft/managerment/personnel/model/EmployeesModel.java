package com.neusoft.managerment.personnel.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

/**
 *    author : 丘嘉茹
 *    version: 1.0
 *    人事管理信息模块的员工档案管理信息Model
 */
@Alias("Employees")
@Data
public class EmployeesModel implements Serializable {

	private String id = null;
	private DepartmentsModel departments = null;
	private String empname = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joindate = null;
	private String sex = null;
	private int age = 0;
	private String mobile = null;
	private String wx = null;
	private String mail = null;
	private double salary=0;
}
