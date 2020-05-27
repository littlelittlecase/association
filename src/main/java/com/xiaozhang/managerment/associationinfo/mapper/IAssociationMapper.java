package com.xiaozhang.managerment.associationinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiaozhang.managerment.associationinfo.model.AssociationModel;



/**
 *    模块：社团基础信息   表：社团表 association
 *    社团的mapper类
 */
@Mapper
public interface IAssociationMapper {
	
	//c  增添方法
		public void create(AssociationModel associationmodel) throws Exception;
		//u  修改方法
		public void update(AssociationModel associationmodel) throws Exception;
		//d  删除方法
		public void delete(AssociationModel associationmodel) throws Exception;
		
		//R  查询 取得列表 只取的院系
		public List<AssociationModel> selectListByAll() throws Exception;
		public List<AssociationModel> selectListByName(String associationname) throws Exception;
		//查询社团名称
		public List<AssociationModel>selectname()throws Exception;
		
		//取得所有社团列表,分页模式
	   public List<AssociationModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	   
	   //取得社团个数
	   public int selectcountbyall() throws Exception;
	   //取得指定社团
	   public AssociationModel selectbyname(String academyid) throws Exception;
	   
	 //根据综合检索条件取得社团列表,分页
	 	public List<AssociationModel> selectListByConditionWithPage(
	 			@Param("associationname") String academyname,
	 			
	 			@Param("start") int start,
	 			@Param("rows") int rows) throws Exception;
	 	
	 	//根据综合检索条件取得社团个数
	 	public int selectCountByCondition(
	 			@Param("associationname") String academyname
	 			)throws Exception;

}
