package com.neusoft.managerment.schoolinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



import com.neusoft.managerment.schoolinfo.model.MajoyModel;
/**
 *    模块：学校基础信息   表：专业表 Majoy
 *    专业    的mapper类
 */
@Mapper
public interface IMajoyMapper {
	
	//c  增添方法
		public void create(MajoyModel majoymodel) throws Exception;
		//u  修改方法
		public void update(MajoyModel majoymodel) throws Exception;
		//d  删除方法
		public void delete(MajoyModel majoymodel) throws Exception;
		
		//R  查询 取得列表 只取的院系
		public List<MajoyModel> selectListByAll() throws Exception;
		public List<MajoyModel> selectListByName(String majoyname) throws Exception;
		//查询学院名称
		public List<MajoyModel>selectname()throws Exception;
		
		//取得所有学院列表,分页模式
	   public List<MajoyModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	   
	   //取得学院个数
	   public int selectcountbyall() throws Exception;
	   //取得指定学院
	   public MajoyModel selectbyname(String majoyid) throws Exception;
	   
	 //根据综合检索条件取得学院列表,分页
	 	public List<MajoyModel> selectListByConditionWithPage(
	 			@Param("majoyname") String majoyname,
	 			
	 			@Param("start") int start,
	 			@Param("rows") int rows) throws Exception;
	 	
	 	//根据综合检索条件取得新闻个数
	 	public int selectCountByCondition(
	 			@Param("majoyname") String majoyname
	 			)throws Exception;

}
