package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;

/**
 *    模块：系统基础信息模块   表：维修单位maintainprovide
 *    author : 张梓琪
 *    version: 1.0
 */
//维修单位的Mapper接口
@Mapper
public interface IMaintainProviderMapper {
	//增加
	public void create (MaintainProviderModel maintanprovidermodel) throws Exception;
	//删除 
	public void delete (MaintainProviderModel maintanprovidermodel)throws Exception;
	//查询
	public List<MaintainProviderModel>selectall()throws Exception;
	//修改 
	public void update (MaintainProviderModel maintanprovidermodel)throws Exception;
	//	取得所有维修单位单列表，分页
	public List<MaintainProviderModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows)throws Exception;
	//取得维修单位个数
	public int selectcountbyall()throws Exception;
	//根据综合检索条件取得维修单位列表
	public List<MaintainProviderModel> selectListByConditionWithPage(
			@Param("pname") String pname,
			@Param("paddress") String paddress,
			@Param("start") int start,
 			@Param("rows") int rows
			)throws Exception;
	//根据综合检索条件取得维修单位个数
	public int selectCountByCondition (
			@Param("pname") String pname,
			@Param("paddress") String paddress
			)throws Exception;
	

}
