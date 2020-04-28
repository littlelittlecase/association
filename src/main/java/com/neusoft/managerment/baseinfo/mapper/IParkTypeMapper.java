package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.AreaModel;
import com.neusoft.managerment.baseinfo.model.ParkTypeModel;

/**
 *  模块：基础信息   表：车位类型 ParkType
 * 车位类型的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */
@Mapper
public interface IParkTypeMapper {
	//增加车位类型
	public void create(ParkTypeModel packTypeModel) throws Exception;
	//修改车位类型
	public void update(ParkTypeModel packTypeModel) throws Exception;
	//删除车位类型
	public void delete(ParkTypeModel packTypeModel) throws Exception;
	//查询车位类型的信息
	public List<ParkTypeModel> selectListByAll() throws Exception;
	//取得指定车位类型的信息
	public ParkTypeModel selectByNo(int no) throws Exception;
	//取得所有车位类型列表,分页模式
	public List<ParkTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得车位类型的个数
	public int selectCountByAll() throws Exception;
}
