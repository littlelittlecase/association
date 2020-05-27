package com.xiaozhang.managerment.schoolinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiaozhang.managerment.schoolinfo.model.AcademyModel;
/**
 *    模块：学校基础信息   表：学院表 Academy
 *    学院的mapper类
 */
@Mapper
public interface IAcademyMapper {
	
	//c  增添方法
		public void create(AcademyModel academymodel) throws Exception;
		//u  修改方法
		public void update(AcademyModel academymodel) throws Exception;
		//d  删除方法
		public void delete(AcademyModel academymodel) throws Exception;
		
		//R  查询 取得列表 只取的院系
		public List<AcademyModel> selectListByAll() throws Exception;
		public List<AcademyModel> selectListByName(String academyname) throws Exception;
		//查询学院名称
		public List<AcademyModel>selectname()throws Exception;
		
		//取得所有学院列表,分页模式
	   public List<AcademyModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	   
	   //取得学院个数
	   public int selectcountbyall() throws Exception;
	   //取得指定学院
	   public AcademyModel selectbyname(String academyid) throws Exception;
	   
	 //根据综合检索条件取得学院列表,分页
	 	public List<AcademyModel> selectListByConditionWithPage(
	 			@Param("academyname") String academyname,
	 			
	 			@Param("start") int start,
	 			@Param("rows") int rows) throws Exception;
	 	
	 	//根据综合检索条件取得新闻个数
	 	public int selectCountByCondition(
	 			@Param("academyname") String academyname
	 			)throws Exception;

}
