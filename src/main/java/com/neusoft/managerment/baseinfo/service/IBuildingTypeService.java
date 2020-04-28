package com.neusoft.managerment.baseinfo.service;

import java.util.List;

import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.model.BuildingTypeModel;

/**
 *    模块：基础信息  表：建筑类型 BuildingType
 *   建筑类型业务层接口
 * @Author:吕淑兰
 */

public interface IBuildingTypeService {
	//增加
	public void add(BuildingTypeModel buildTypeModel) throws Exception;
	//修改
	public void modify(BuildingTypeModel buildTypeModel) throws Exception;
	//删除
	public void delete(BuildingTypeModel buildTypeModel) throws Exception;
	//查询建筑类型的信息
	public List<BuildingTypeModel> getBuildTypeListByAll() throws Exception;
	//取得指定建筑类型的信息
	public BuildingTypeModel getBuildTypeByNo(String no) throws Exception;
	//取得所有建筑类型列表,分页模式
	public List<BuildingTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得建筑类型的个数
	public int getCountByAll() throws Exception;
	//取得建筑类型页数
	public int getPageCountByAll(int rows) throws Exception;
}
