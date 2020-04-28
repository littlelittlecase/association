package com.neusoft.managerment.feeinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.YearPriceModel;

/**
 *  模块：基础信息   表：年度价格  YearPrice
 *年度价格的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */

@Mapper
public interface IYearPriceMapper {
	//增加年度价格
	public void create(YearPriceModel yearPriceModel) throws Exception;
	//修改年度价格
	public void update(YearPriceModel yearPriceModel) throws Exception;
	//删除年度价格
	public void delete(YearPriceModel yearPriceModel) throws Exception;
	//取得年度价格表，关联收费项目
	//public List<YearPriceModel> selectListByAllWithFeeItem() throws Exception;
	//取得年度价格表，关联收费项目
	public List<YearPriceModel> selectListByAllWithFeeItem() throws Exception;
	//取得指定年度价格的信息，关联收费项目
	public YearPriceModel selectByFeeYearWithFeeItem(String feeyear) throws Exception;
	//为年度价格增加收费项目
	public void addFeeItem(@Param("feeyear") String feeyear,@Param("itemno") int[] itemno) throws Exception;
	//删除指定年度价格的收费项目
	public void deleteFeeItem(String feeyear) throws Exception;
	//根据综合检索条件取得年度价格列表
	public List<YearPriceModel> selectListByConditionWithFeeItemWithPage(
			@Param("feeyear") String feeyear,
			@Param("itemNo") int intemNo,
			@Param("minunitprice") String minunitprice,
			@Param("maxunitprice") String maxunitprice,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//根据综合检索条件取得年度价格个数
	public int selectCountByCondition(
			@Param("feeyear") String feeyear,
			@Param("itemNo") int intemNo,
			@Param("minunitprice") String minunitprice,
			@Param("maxunitprice") String maxunitprice,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate) throws Exception;
}
