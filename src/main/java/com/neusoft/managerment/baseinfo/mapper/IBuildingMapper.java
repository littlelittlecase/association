package com.neusoft.managerment.baseinfo.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.AreaModel;
import com.neusoft.managerment.baseinfo.model.BuildingModel;

/**
 * 模块：基础信息  表：楼宇  Building
 * 楼宇的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */

@Mapper
public interface IBuildingMapper {
	//增加
	public void create(BuildingModel buildModel) throws Exception;
	//修改
	public void update(BuildingModel buildModel) throws Exception;
	//删除
	public void delete(BuildingModel buildModel) throws Exception;
	//取得所有楼宇列表
	public List<BuildingModel> selectListByAll() throws Exception;
	//取得所有楼宇列表,关联Area,分页模式
	public List<BuildingModel> selectListByAllWithArea() throws Exception;
	//取得所有楼宇列表,关联BuildingType,分页模式
	public List<BuildingModel> selectListByAllWithBuildType() throws Exception;
	//取得所有楼宇列表,关联Area和BuildingType
	public List<BuildingModel> selectListByAllWithAreaAndBuildType() throws Exception;
	//取得所有楼宇列表,关联Area和BuildingType,分页模式
	public List<BuildingModel> selectListByAllWithAreaAndBuildTypeWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;

	//取得指定楼宇的楼宇列表,参数:areaNo 
	public List<BuildingModel> selectListByArea(int areaNo) throws Exception;
	//取得指定建筑类型的楼宇列表,参数:buildTypeNo 
	//public List<BuildingModel> selectListByBuildType(String buildTypeNo) throws Exception;
	//取得指定楼宇的信息
	public BuildingModel selectByNo(String no) throws Exception;
	//取得所有楼宇列表,分页模式
	public List<BuildingModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得楼宇的个数
	public int selectCountByAll() throws Exception;
	//根据综合检索条件取得楼宇列表,关联Area和BuildingType,分页
	public List<BuildingModel> selectListByConditionWithAreaAndBuildTypeWithPage(
			@Param("areaNo") int areaNo,
			@Param("buildingtypeNo") String buildingtypeNo,
			@Param("code") String code,
			@Param("direction") String direction,
			@Param("minhome") int minhome,
			@Param("maxhome") int maxhome,
			@Param("minhouse") int minhouse,
			@Param("maxhouse") int maxhouse, 
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//根据综合检索条件取得楼宇个数
	public int selectCountByCondition(
			@Param("areaNo") int areaNo,
			@Param("buildingtypeNo") String buildingtypeNo,
			@Param("code") String code,
			@Param("direction") String direction,
			@Param("minhome") int minhome,
			@Param("maxhome") int maxhome,
			@Param("minhouse") int minhouse,
			@Param("maxhouse") int maxhouse) throws Exception;

}

