package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.BuildingTypeModel;
/**
 *    模块：基础信息  表：建筑类型 BuildingType
 *    建筑类型的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */

@Mapper
public interface IBuildingTypeMapper {
	//增加
	public void create(BuildingTypeModel buildTypeModel) throws Exception;
	//修改
	public void update(BuildingTypeModel buildTypeModel) throws Exception;
	//删除
	public void delete(BuildingTypeModel buildTypeModel) throws Exception;
	//查询建筑类型的信息
	public List<BuildingTypeModel> selectBuildTypeListByAll() throws Exception;
	//取得指定建筑类型的信息
	public BuildingTypeModel selectBuildTypeByNo(String no) throws Exception;
	//取得所有部门列表,分页模式，不取关联的员工列表
	public List<BuildingTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得部门的个数
	public int selectCountByAll() throws Exception;

}
