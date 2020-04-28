package com.neusoft.managerment.associationinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.associationinfo.model.ApplicationModel;
import com.neusoft.managerment.associationinfo.model.AssociationModel;



/**
 *    模块：社团基础信息   表：社团审核表 application
 *    社团审核的mapper类
 */
@Mapper
public interface IApplicationMapper {
	
		//u  修改方法
		public void update(ApplicationModel applitionmodel) throws Exception;
		//R  查询 取得列表 只取的院系
		public List<ApplicationModel> selectListByAll() throws Exception;
		public List<ApplicationModel> selectListByName(String appname) throws Exception;
		//查询社团名称
		public List<ApplicationModel>selectname()throws Exception;
		
		//取得所有社团列表,分页模式
	   public List<ApplicationModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	   
	   //取得社团个数
	   public int selectcountbyall() throws Exception;
	   //取得指定社团
	   public ApplicationModel selectbyname(String appid) throws Exception;
	   
	 //根据综合检索条件取得社团列表,分页
	 	public List<ApplicationModel> selectListByConditionWithPage(
	 			@Param("appname") String appname,
	 			
	 			@Param("start") int start,
	 			@Param("rows") int rows) throws Exception;
	 	
	 	//根据综合检索条件取得社团个数
	 	public int selectCountByCondition(
	 			@Param("appname") String appname
	 			)throws Exception;

}
