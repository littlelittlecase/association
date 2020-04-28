package com.neusoft.managerment.personnel.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.personnel.model.DepartmentsModel;




/**
 *    author : 丘嘉茹
 *    version: 1.0
 *    人事管理信息模块的部门管理信息dao
 */
//部门管理的mapper接口
@Mapper
public interface IDepartmentsMapper {
	//增加
	public void create(DepartmentsModel deptModel) throws Exception;
	//修改
	public void update(DepartmentsModel deptModel) throws Exception;
	//删除
	public void delete(DepartmentsModel deptModel) throws Exception;
	//查询所有部门信息
	public List<DepartmentsModel> selectDeptListByAll() throws Exception;
	//查询部门信息,不取员工，分页
	public List<DepartmentsModel> selectDeptListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//返回指定部门的信息，同时取得关联的员工列表
	public DepartmentsModel selectDeptByNo(int deptno) throws Exception;
	//取得个数
	public int selectCountByAll() throws Exception;
	//取得检索后的个数
	public int selectCountByCondition(@Param("departmentNo") int departmentNo,@Param("departmentName") String departmentName) throws Exception;
	//取得检索后的部门表
	public List<DepartmentsModel> selectDeptByCondition(@Param("departmentNo") int departmentNo,@Param("departmentName") String departmentName, @Param("start") int start,@Param("rows") int rows) throws Exception;	
}
