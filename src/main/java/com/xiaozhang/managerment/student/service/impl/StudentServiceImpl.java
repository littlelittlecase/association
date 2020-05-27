package com.xiaozhang.managerment.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaozhang.managerment.schoolinfo.model.MajoyModel;
import com.xiaozhang.managerment.student.mapper.IStudentMapper;
import com.xiaozhang.managerment.student.service.IStudentService;
import com.xiaozhang.managerment.studentinfo.model.StudentModel;


//学生的业务实现类
@Service("studentservice")
public class StudentServiceImpl implements IStudentService  {
	
	private SqlSessionFactory sessionfactory = null;
	@Autowired
	private IStudentMapper studentMapper = null;
	@Override
	public void add(StudentModel studentmodel) throws Exception {
		// TODO Auto-generated method stub
		studentMapper.create(studentmodel);
		
	}
	@Override
	public void delete(StudentModel studentmodel) throws Exception {
		// TODO Auto-generated method stub
		studentMapper.delete(studentmodel);
		
	}
	@Override
	public void update(StudentModel studentmodel) throws Exception {
		// TODO Auto-generated method stub
		studentMapper.update(studentmodel);
		
	}
	@Override
	public List<StudentModel> getall() throws Exception {
		return studentMapper.selectListByAll();
	}
	@Override
	public List<StudentModel> getname() throws Exception {
		return studentMapper.selectname();
	}
	@Override
	public List<StudentModel> getbyname(String academyname) throws Exception {
		List<StudentModel> list = studentMapper.selectListByName(academyname);
		return list;
	}
	@Override
	public StudentModel getByName(String studentid) throws Exception {
		// TODO Auto-generated method stub
		return studentMapper.selectbyname(studentid);
	}
	@Override
	
	public List<StudentModel> getListByConditionWithPage(String studentname, String academyname, int rows, int page)
			throws Exception {
		return studentMapper.selectListByConditionWithPage(studentname,academyname,rows*(page-1), rows);
	}
	
	@Transactional(readOnly = true)
	@Override
	public int getPageByConditionWithPage(String studentname, String academyname, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(studentname,studentname);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	@Override
	public int getCountByCondition(String studentname, String academyname) throws Exception {
		return studentMapper.selectCountByCondition(studentname,academyname);

	}

	

}

	


