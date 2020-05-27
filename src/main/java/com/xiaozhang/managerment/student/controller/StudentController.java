package com.xiaozhang.managerment.student.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozhang.managerment.message.ResultMessage;
import com.xiaozhang.managerment.student.service.IStudentService;
import com.xiaozhang.managerment.studentinfo.model.StudentModel;


/**
 *    模块：学校基础信息  表：专业表  student
 */
@RestController
@RequestMapping(value="/student")
public class StudentController {
	
	@Autowired
	IStudentService studentservice = null;

	//查询所有专业
	@RequestMapping(value="/tolist")
	public List<StudentModel> getall() throws Exception {
		return studentservice.getall();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<StudentModel> add(StudentModel studentmodel) throws Exception {
		studentservice.add(studentmodel);
		return new ResultMessage<>("OK", "增加学生成功");
	}

	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<StudentModel> delete(StudentModel studentmodel) throws Exception {
		studentservice.delete(studentmodel);
	return new ResultMessage<StudentModel>("OK", "删除学生成功");
	}

	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<StudentModel> update(StudentModel studentmodel) throws Exception {
		studentservice.update(studentmodel);
		return new ResultMessage<StudentModel>("OK", "修改学生成功");
	}

	//检索学生列表，分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<StudentModel> getListByConditionWithPage(	
			@RequestParam(required = false,defaultValue ="") String academyname,
		@RequestParam(required = false,defaultValue ="") String studentname,
		@RequestParam(required = false,defaultValue ="10") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
	ResultMessage<StudentModel> result=new ResultMessage<StudentModel>("OK","学生列表分页模式成功");	
	result.setList(studentservice.getListByConditionWithPage(studentname,academyname, rows, page));
	result.setCount(studentservice.getCountByCondition(academyname,studentname));
	result.setPageCount(studentservice.getPageByConditionWithPage(studentname,academyname,rows));
	result.setPage(page);
	result.setRows(rows);
	return result;
	}
	
	//获取学院名称
	@RequestMapping(value="/list/name")
	public List<StudentModel>getname()throws Exception{
		return studentservice.getname();
	}


	//取得学院编号
	@RequestMapping(value="/get")
	public ResultMessage<StudentModel>get (String studentid)throws Exception{
		ResultMessage<StudentModel> result = new ResultMessage<StudentModel>("ok","取得学生成功");
		System.out.println(result);
		result.setModel(studentservice.getByName(studentid));
		return result;
		
	}
	

	
	
}
