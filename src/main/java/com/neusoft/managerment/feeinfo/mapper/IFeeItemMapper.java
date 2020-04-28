package com.neusoft.managerment.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.FeeItemModel;
import com.neusoft.managerment.feeinfo.model.FeeTypeModel;


/**
 *  模块：基础信息   表：收费项目  FeeItem
 *收费项目的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */

@Mapper
public interface IFeeItemMapper {
	//增加收费项目
	public void create(FeeItemModel feeItemModel) throws Exception;
	//修改收费项目
	public void update(FeeItemModel feeItemModel) throws Exception;
	//删除收费项目
	public void delete(FeeItemModel feeItemModel) throws Exception;
	//取得单位列表
	public List<FeeItemModel> selectListByUnit() throws Exception;
	//取得收费项目列表
	public List<FeeItemModel> selectListByAll() throws Exception;
	//取得指定收费项目的信息
	public FeeItemModel selectByNoWithFeeType(int no) throws Exception;
	//取得所有收费项目列表,分页模式
	//public List<FeeItemModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	
	//取得收费项目的个数
	public int selectCountByAll() throws Exception;
	
	//根据综合检索条件取得收费项目列表
	public List<FeeItemModel> selectListByConditionWithPage(
			@Param("unit") String unit,
			@Param("feetypeNo") int feetypeNo,
			@Param("cycle") String cycle,
			@Param("status") String status,
			@Param("start") int start,
			@Param("rows") int rows)throws Exception;
	
	//根据综合检索条件取得收费项目个数
	public int selectCountByCondition(
			@Param("unit") String unit,
			@Param("feetypeNo") int feetypeNo,
			@Param("cycle") String cycle,
			@Param("status") String status)throws Exception;
	
}
