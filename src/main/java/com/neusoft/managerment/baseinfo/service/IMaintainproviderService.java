package com.neusoft.managerment.baseinfo.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;

/**
 *    模块：基础信息   表：维修单位 类型 maintainprovider
 *    author : 张梓琪
 *    version: 1.0
 */
public interface IMaintainproviderService {
	//增加
	public void add(MaintainProviderModel maintainmodel)throws Exception;
	//删除
	public void delete(MaintainProviderModel maintainmodel)throws Exception;
	//修改
	public void update(MaintainProviderModel maintainmodel)throws Exception;
	//查询
	public List<MaintainProviderModel>getall()throws Exception;
    
	//根据综合检索条件取得维修单位列表
	public List<MaintainProviderModel> getListByConditionWithPage(
				String pname,
				String paddress,
				int rows, int page
				)throws Exception;
	//根据综合检索条件取得维修单位个数
	public int getCountByCondition (
				String pname,
				String paddress
				)throws Exception;
	//取得页数
	public int getPageByConditionWithPage(
				String pname,
				String paddress,
				int rows) throws Exception;

	
}
