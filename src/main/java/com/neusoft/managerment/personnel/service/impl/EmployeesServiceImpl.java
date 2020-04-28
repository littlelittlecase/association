package com.neusoft.managerment.personnel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.personnel.mapper.IEmployeesMapper;
import com.neusoft.managerment.personnel.model.EmployeesModel;
import com.neusoft.managerment.personnel.service.IEmployeesService;
/**
 *    author : 丘嘉茹
 *    version: 1.0
 *    人事管理信息模块的员工档案管理信息实现类
 */

@Service("employeesService")
@Transactional(rollbackFor=Exception.class)
public class EmployeesServiceImpl implements IEmployeesService {
	@Autowired
	private IEmployeesMapper employeesMapper=null;
	//添加
	@Override
	public void add(EmployeesModel employees) throws Exception {
		// TODO Auto-generated method stub
		employeesMapper.create(employees);
	}
	//修改
	@Override
	public void modify(EmployeesModel employees) throws Exception {
		// TODO Auto-generated method stub
		employeesMapper.update(employees);
	}
	//删除
	@Override
	public void delete(EmployeesModel employees) throws Exception {
		// TODO Auto-generated method stub
		employeesMapper.delete(employees);
	}
	//获取指定的员工信息
	@Override
	@Transactional(readOnly = true)
	public EmployeesModel getEmpByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return employeesMapper.selectEmpByID(id);
	}


	//获取全部员工信息
	@Override
	@Transactional(readOnly = true)
	public List<EmployeesModel> getEmpListByAll() throws Exception {
		// TODO Auto-generated method stub
		return employeesMapper.selectEmpListByAll();
	}

	//检索条件
	@Override
	public List<EmployeesModel> getListByConditionWithDepartments(int departmentNo, int idd, String sex, int age,
			String mobile, Date startJoinDate, Date endJoinDate, double minSal, double maxSal, int rows, int page)
			throws Exception {
		// TODO Auto-generated method stub
		return employeesMapper.selectListByConditionWithDepartments(departmentNo, idd, sex, age, mobile, startJoinDate, endJoinDate, minSal, maxSal, rows*(page-1), rows);
	}
	//检索个数
	@Override
	public int getCountByConditionWithDepartments(int departmentNo, int idd, String sex, int age, String mobile,
			Date startJoinDate, Date endJoinDate, double minSal, double maxSal) throws Exception {
		// TODO Auto-generated method stub
		return employeesMapper.selectCountByCondition(departmentNo, idd, sex, age, mobile, startJoinDate, endJoinDate, minSal, maxSal);
	}
	//检索页数
	@Override
	public int getPageCountByConditionWithDepartments(int departmentNo, int idd, String sex, int age, String mobile,
			Date startJoinDate, Date endJoinDate, double minSal, double maxSal, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByConditionWithDepartments(departmentNo, idd, sex, pageCount, mobile, startJoinDate, endJoinDate, minSal, maxSal);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
}


