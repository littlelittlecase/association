package com.neusoft.managerment.baseinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.model.ParkModel;


/**
 * 模块：基础信息   表：车位 Park
 * 车位业务层接口
 * @Author:吕淑兰
 */

public interface IParkService {
	//增加车位
	public void add(ParkModel parkModel) throws Exception;
	//修改车位
	public void modify(ParkModel parkModel) throws Exception;
	//删除车位
	public void delete(ParkModel parkModel) throws Exception;
	//取得所有车位列表
	public List<ParkModel> getListByAll() throws Exception;
	//取得所有车位列表,关联ParkType
	public List<ParkModel> getListByAllWithParkType() throws Exception;
	//取得所有车位列表,关联Building
	public List<ParkModel> getListByAllWithBuilding() throws Exception;
	//取得所有车位列表,关联ParkType和Building
	public List<ParkModel> getListByAllWithParkTypeAndBuilding() throws Exception;
	//取得指定车位类型的车位列表,参数:parkTypeNo 
	public List<ParkModel> getListByParkType(int ParkTypeNo) throws Exception;
	//取得指定楼宇的车位列表,参数:buildingNo 
	public List<ParkModel> getListByBuilding(int buildingNo) throws Exception;
	//取得指定车位的信息
	public ParkModel getByNo(int no) throws Exception;
	//取得所有车位列表,分页模式
	public List<ParkModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得车位的个数
	public int getCountByAll() throws Exception;
	//取得车位页数
	public int getPagaCountByAll(int rows) throws Exception;
	//根据综合检索条件取得车位列表,关联ParkType和Building,分页	
	public List<ParkModel> getListByConditionWithParkTypeAndBuildingWithPage(
			int parkTypeNo,
			int buildingNo,
			String parkstatus,
			String rentunit,
			String feestatus,
			String mixarea,
			String maxarea,
			String minrentprice,
			String maxrentprice,
			int rows,
			int page) throws Exception;
	//根据综合检索条件取得车位个数
	public int getCountByCondition(
			int parkTypeNo,
			int buildingNo,
			String parkstatus,
			String rentunit,
			String feestatus,
			String mixarea,
			String maxarea,
			String minrentprice,
			String maxrentprice) throws Exception;	
	//根据综合检索条件取得车位页数
	public int getPageCountByCondition(
			int parkTypeNo,
			int buildingNo,
			String parkstatus,
			String rentunit,
			String feestatus,
			String mixarea,
			String maxarea,
			String minrentprice,
			String maxrentprice,
			int rows) throws Exception;	
}
