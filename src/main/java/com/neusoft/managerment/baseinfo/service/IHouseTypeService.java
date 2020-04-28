package com.neusoft.managerment.baseinfo.service;

import java.util.List;

import com.neusoft.managerment.baseinfo.model.HouseTypeModel;


/**
 *    模块：基础信息   表：户型  HouseType
 *    author : 陈思颖
 *    version: 1.0
 */
public interface IHouseTypeService {
	//增加
	public void add(HouseTypeModel housetype) throws Exception;
	//修改
	public void modify(HouseTypeModel housetype) throws Exception;
	//删除
	public void delete(HouseTypeModel housetype) throws Exception;
	//取得所有客户列表
	public List<HouseTypeModel> getListByAll() throws Exception;
	//取得单个户型
	public HouseTypeModel getByTypeNo(int typeno) throws Exception;
	//取得所有户型列表,分页模式
	public List<HouseTypeModel> getListByAllWithPage(int rows,int page) throws Exception;

}
