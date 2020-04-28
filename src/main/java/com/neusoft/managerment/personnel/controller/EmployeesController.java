package com.neusoft.managerment.personnel.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.neusoft.managerment.message.ResultMessage;
import com.neusoft.managerment.personnel.model.DepartmentsModel;
import com.neusoft.managerment.personnel.model.EmployeesModel;
import com.neusoft.managerment.personnel.service.IEmployeesService;
/**
 *    author : 丘嘉茹
 *    version: 1.0
 *    人事管理信息模块的员工档案管理信息
 */

@RestController
@RequestMapping("employees")
public class EmployeesController {
	@Autowired
	private IEmployeesService employeesService=null;
	//增加
	@PostMapping("/add")
	public ResultMessage<EmployeesModel> add(EmployeesModel employees) throws Exception {
		employeesService.add(employees);
		return new ResultMessage<EmployeesModel>("OK","增加员工信息成功");
	}
	//修改
	@PostMapping("/modify")
	public ResultMessage<EmployeesModel> modify(EmployeesModel employees) throws Exception {
		employeesService.modify(employees);
		return new ResultMessage<EmployeesModel>("OK","修改员工信息成功");
	}
	//删除
	@PostMapping("/delete")
	public ResultMessage<EmployeesModel> delete(EmployeesModel employees) throws Exception {
		employeesService.delete(employees);
		return new ResultMessage<EmployeesModel>("OK","删除员工信息成功");
	}
	//取得指定的员工信息
	@GetMapping("/get")
	public ResultMessage<EmployeesModel> getEmpByID(int id) throws Exception{
		ResultMessage<EmployeesModel> result=new ResultMessage<EmployeesModel>("OK","取得员工信息成功");
		result.setModel(employeesService.getEmpByID(id));
		return result;
	}
	
	//
	@GetMapping(value="/list/condition/page")
	public ResultMessage<EmployeesModel> getListByAllWithPage(
			@RequestParam(required = false,defaultValue ="0") int departmentNo,
			@RequestParam(required = false,defaultValue ="0") int idd,
			@RequestParam(required = false,defaultValue ="") String sex,
			@RequestParam(required = false,defaultValue ="0") int age, 
			@RequestParam(required = false,defaultValue ="") String mobile,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date startJoinDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endJoinDate,
			@RequestParam(required=false,defaultValue="0")double minSal,
			@RequestParam(required=false,defaultValue="0")double maxSal,
			@RequestParam(required = false,defaultValue ="10") int rows,
			@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<EmployeesModel> result=new ResultMessage<EmployeesModel>("OK","取得员工列表分页成功");
		result.setCount(employeesService.getCountByConditionWithDepartments(departmentNo, idd, sex, age, mobile, startJoinDate, endJoinDate, minSal, maxSal));
		result.setPageCount(employeesService.getPageCountByConditionWithDepartments(departmentNo, idd, sex, age, mobile, startJoinDate, endJoinDate, minSal, maxSal, rows));
		result.setList(employeesService.getListByConditionWithDepartments(departmentNo, idd, sex, age, mobile, startJoinDate, endJoinDate, minSal, maxSal, rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得所有客户列表，无分页
	@GetMapping(value="/list/all")
	public List<EmployeesModel> getListByAll() throws Exception{
		return employeesService.getEmpListByAll();
	}
}
