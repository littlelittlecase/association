package com.xiaozhang.managerment.student.service;
import java.util.List;

import com.xiaozhang.managerment.studentinfo.model.StudentModel;

/*
   *      张梓琪
 * 学生信息的业务接口
 * */
public interface IStudentService {
	 //增加
	public void add(StudentModel studentmodel)throws Exception;
	//删除
	public void delete(StudentModel studentmodel)throws Exception;
	//修改
	public void update(StudentModel studentmodel)throws Exception;
	//查询
	public List<StudentModel>getall()throws Exception;
	
	//获取学生名
	public List<StudentModel>getname()throws Exception;
	//获取指定学生
	public StudentModel getByName(String studentid)throws Exception;
    
	//根据综合检索条件取得学生列表
	public List<StudentModel> getListByConditionWithPage(
				String studentname,
				String academyname,
				int rows, int page
				)throws Exception;
	//根据综合检索条件取得学生个数
	public int getCountByCondition (
			String studentname,
			String academyname
				)throws Exception;
	//取得页数
	public int getPageByConditionWithPage(
			String studentname,
			String academyname,
				int rows) throws Exception;
	public List<StudentModel> getbyname(String academyname) throws Exception;
	
}
