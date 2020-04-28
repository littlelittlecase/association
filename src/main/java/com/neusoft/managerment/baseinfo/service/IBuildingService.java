package com.neusoft.managerment.baseinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.BuildingModel;


/**
 *    模块：基础信息  表：楼宇  Building
 *   楼宇业务层接口
 * @Author:吕淑兰
 */

public interface IBuildingService {
	//增加
	public void add(BuildingModel buildModel) throws Exception;
	//修改
	public void modify(BuildingModel buildModel) throws Exception;
	//删除
	public void delete(BuildingModel buildModel) throws Exception;
	//查询楼宇的信息
	public List<BuildingModel> getListByAll() throws Exception;
	//查询楼宇的信息,关联Area
	public List<BuildingModel> getListByAllWithArea() throws Exception;
	//查询楼宇的信息,关联BuildingType
	public List<BuildingModel> getListByAllWithBuildType() throws Exception;
	//查询楼宇的信息,关联Area和BuildingType
	public List<BuildingModel> getListByAllWithAreaAndBuildType() throws Exception;

	//取得所有楼宇列表,关联Area和BuildingType.分页模式
	public List<BuildingModel> getListByAllWithAreaAndBuildTypeWithPage(int rows,int page) throws Exception;
	//取得指定小区的楼宇列表,参数:areaNo 
	public List<BuildingModel> getListByArea(int areaNo) throws Exception;
	//取得指定建筑类型的楼宇列表,参数:buildTypeNo 
	//public List<BuildingModel> getListByBuildType(int buildTypeNo) throws Exception;
	//取得指定楼宇的信息
	public BuildingModel getByNo(String no) throws Exception;
	//取得所有楼宇列表,分页模式
	public List<BuildingModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得楼宇的个数
	public int getCountByAll() throws Exception;
	//取得楼宇页数
	public int getPageCountByAll(int rows) throws Exception;
	
	//根据综合检索条件取得楼宇列表,关联Area和BuildingType,分页
	public List<BuildingModel> getListByConditionWithAreaAndBuildTypeWithPage(
			int areaNo,
			String buildingtypeNo,
			String code,
			String direction,
			int minhome,
			int maxhome,
			int minhouse,
			int maxhouse, 
			int rows, int page) throws Exception;
	
	//根据综合检索条件取得楼宇个数
	public int getCountByCondition(
			int areaNo,
			String buildingtypeNo,
			String code,
			String direction,
			int minhome,
			int maxhome,
			int minhouse,
			int maxhouse) throws Exception;
	
	//根据综合检索条件取得楼宇显示的页数
	public int getPageByConditionWithPage(
			int areaNo,
			String buildingtypeNo,
			String code,
			String direction,
			int minhome,
			int maxhome,
			int minhouse,
			int maxhouse,
			int rows) throws Exception;
	
}
