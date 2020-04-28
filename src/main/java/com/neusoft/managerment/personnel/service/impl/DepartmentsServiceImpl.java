package com.neusoft.managerment.personnel.service.impl;
/**
 *    author : 丘嘉茹
 *    version: 1.0
 *    人事管理信息模块的部门管理信息实现类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.neusoft.managerment.personnel.mapper.IDepartmentsMapper;
import com.neusoft.managerment.personnel.mapper.IEmployeesMapper;
import com.neusoft.managerment.personnel.model.DepartmentsModel;
import com.neusoft.managerment.personnel.service.IDepartmentsService;
@Service
@Transactional(rollbackFor=Exception.class)
public class DepartmentsServiceImpl implements IDepartmentsService {

	@Autowired
	private IDepartmentsMapper departmentsMapper=null;
	@Autowired
	private IEmployeesMapper employeesMapper=null;
	//添加
	@Override
	public void add(DepartmentsModel departments) throws Exception {
		// TODO Auto-generated method stub
		departmentsMapper.create(departments);
	}
	//修改
	@Override
	public void modify(DepartmentsModel departments) throws Exception {
		// TODO Auto-generated method stub
		departmentsMapper.update(departments);
	}
	//删除
	@Override
	public void delete(DepartmentsModel departments) throws Exception {
		// TODO Auto-generated method stub
		departmentsMapper.delete(departments);
	}
    //获取全部部门
	@Override
	@Transactional(readOnly = true)
	public List<DepartmentsModel> getDeptListByAll() throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDeptListByAll();
	}
    //分页模式获取全部部门
	@Override
	@Transactional(readOnly = true)
	public List<DepartmentsModel> getDeptListByAllWithPage(int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDeptListByAllWithPage(rows*(page-1), rows);
	}
	//取得选定部门的详细信息，包括员工
	@Override
	@Transactional(readOnly = true)
	public DepartmentsModel getDeptByNo(int deptno) throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDeptByNo(deptno);
	}
	//取得部门个数
	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectCountByAll();
	}
	//取得获取全部门的页数
	@Override
	@Transactional(readOnly = true)
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

    //检索
	@Override
	public List<DepartmentsModel> getDeptByCondition(int departmentNo, String departmentName, int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectDeptByCondition(departmentNo,departmentName,rows*(page-1), rows);
	}
   //检索所得个数
	@Override
	public int getCountByCondition(int departmentNo, String departmentName) throws Exception {
		// TODO Auto-generated method stub
		return departmentsMapper.selectCountByCondition(departmentNo, departmentName);
	}
	//检索所得页数
	@Override
	public int getPageCountByCondition(int departmentNo, String departmentName, int rows) throws Exception {
		// TODO Auto-generated method stub
		int pageCount=0;
		int count=this.getCountByCondition(departmentNo, departmentName);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}


}
