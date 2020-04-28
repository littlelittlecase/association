package com.neusoft.managerment.feeinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.FeeItemModel;

/**
 *   模块：收费   表：收费项目  FeeItem
 *  收费项目业务层接口
 * @Author:吕淑兰
 */
public interface IFeeItemService {
	//增加收费项目
	public void add(FeeItemModel feeItemModel) throws Exception;
	//修改收费项目
	public void modify(FeeItemModel feeItemModel) throws Exception;
	//删除收费项目
	public void delete(FeeItemModel feeItemModel) throws Exception;
	//取得单位列表
	public List<FeeItemModel> getListByUnit() throws Exception;
	//取得收费项目列表
	public List<FeeItemModel> getListByAll() throws Exception;
	//取得指定收费项目的信息
	public FeeItemModel getByNoWithFeeType(int no) throws Exception;
	//取得所有收费项目列表,分页模式
	//public List<FeeItemModel> getListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	
	//取得收费项目的个数
	public int getCountByAll() throws Exception;
	
	//根据综合检索条件取得收费项目列表
	public List<FeeItemModel> getListByConditionWithPage(
			String unit,
			int feetypeNo,
			String cycle,
			String status,
			int rows,
			int page)throws Exception;
	
	//根据综合检索条件取得收费项目个数
	public int getCountByCondition(
			String unit,
			int feetypeNo,
			String cycle,
			String status)throws Exception;
	
	//根据综合检索条件取得收费项目显示的页数
	public int selectPageByConditionWithPage(
			String unit,
			int feetypeNo,
			String cycle,
			String status,
			int rows)throws Exception;
	
}
