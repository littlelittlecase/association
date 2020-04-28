package com.neusoft.managerment.student.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.studentinfo.model.StudentModel;

//学生的Mapper层接口
@Mapper
public interface IStudentMapper {
	//c  增添方法
	public void create(StudentModel studentmodel) throws Exception;
	//u  修改方法
	public void update(StudentModel studentmodel) throws Exception;
	//d  删除方法
	public void delete(StudentModel studentmodel) throws Exception;
	
	//R  查询 取得列表 只取的新闻
	public List<StudentModel> selectListByAll() throws Exception;
	public List<StudentModel> selectListType(String studentid) throws Exception;
	//查询学生名字
	public List<StudentModel>selectname()throws Exception;
	
	//取得所有学生列表,分页模式
   public List<StudentModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
   
   //取得学生个数
   public int selectcountbyall() throws Exception;
   //取得指定学生
   public StudentModel selectbyname(String studentid) throws Exception;
   
 //根据综合检索条件取得学生列表,分页
 	public List<StudentModel> selectListByConditionWithPage(
 			@Param("academyname") String academyname,
 			@Param("studentname") String studentname,
 			@Param("start") int start,
 			@Param("rows") int rows) throws Exception;
 	
 	//根据综合检索条件取得关联学院表和学生 ，取得个数
 	public int selectCountByCondition(
 			@Param("academyname") String academyname,
 			@Param("studentname") String studentname
 			) throws Exception;
	public List<StudentModel> selectListByName(String academyname);

   
	

}
