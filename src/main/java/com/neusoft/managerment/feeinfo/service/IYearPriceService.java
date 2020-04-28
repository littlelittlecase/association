package com.neusoft.managerment.feeinfo.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.FeeItemModel;
import com.neusoft.managerment.feeinfo.model.YearPriceModel;

/**
 *   模块：收费   表：年度价格  YearPrice
 *  年度价格业务层接口
 * @Author:吕淑兰
 */
public interface IYearPriceService {
	//增加年度价格
	public void add(YearPriceModel yearPriceModel) throws Exception;
	//修改年度价格
	public void modify(YearPriceModel yearPriceModel) throws Exception;
	//删除年度价格
	public void delete(YearPriceModel yearPriceModel) throws Exception;
	//取得年度价格表，关联收费项目
	public List<YearPriceModel> getListByAllWithFeeItem() throws Exception;
	//取得指定年度价格的信息，关联收费项目
	public YearPriceModel getByFeeYearWithFeeItem(String feeyear) throws Exception;
	//为年度价格增加收费项目
	public void addFeeItem(String feeyear,int[] itemno) throws Exception;
	//删除指定年度价格的收费项目
	public void deleteFeeItem(String feeyear) throws Exception;
	//根据综合检索条件取得年度价格列表
	public List<YearPriceModel> getListByConditionWithFeeItemWithPage(
			String feeyear,
			int intemNo,
			String minunitprice,
			String maxunitprice,
			Date startDate,
			Date endDate,
			int rows,
			int page) throws Exception;
	//根据综合检索条件取得年度价格个数
	public int getCountByCondition(
			String feeyear,
			int intemNo,
			String minunitprice,
			String maxunitprice,
			Date startDate,
			Date endDate) throws Exception;
	//根据综合检索条件取得年度价格页数
	public int getPageByCondition(
			String feeyear,
			int intemNo,
			String minunitprice,
			String maxunitprice,
			Date startDate,
			Date endDate,
			int rows) throws Exception;
}
