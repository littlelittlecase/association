package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.HouseTypeModel;

/**
 *    模块：基础信息   表：户型  HouseType
 *    author : 陈思颖
 *    version: 1.0
 */
//户型的Mapper接口
@Mapper
public interface IHouseTypeMapper {
	//增加
	public void create(HouseTypeModel houseTypeModel) throws Exception;
	//修改
	public void update(HouseTypeModel houseTypeModel) throws Exception;
	//删除
	public void delete(HouseTypeModel houseTypeModel) throws Exception;
	//取得户型的信息
	public List<HouseTypeModel> selectListByAll() throws Exception;
	//取得单个户型的信息
	public HouseTypeModel selectByTypeNo(int typeno) throws Exception;
	//取得户型列表,有分页
	public List<HouseTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;

}
