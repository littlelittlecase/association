package com.neusoft.managerment.baseinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.ParkTypeModel;

/**
 *   模块：基础信息   表：车位类型 ParkType
 *  车位类型业务层接口
 * @Author:吕淑兰
 */
public interface IParkTypeService {
	//增加车位类型
	public void add(ParkTypeModel packTypeModel) throws Exception;
	//修改车位类型
	public void modify(ParkTypeModel packTypeModel) throws Exception;
	//删除车位类型
	public void delete(ParkTypeModel packTypeModel) throws Exception;
	//查询车位类型的信息
	public List<ParkTypeModel> getListByAll() throws Exception;
	//取得指定车位类型的信息
	public ParkTypeModel getByNo(int no) throws Exception;
	//取得所有车位类型列表,分页模式
	public List<ParkTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得车位类型的个数
	public int getCountByAll() throws Exception;
	//取得车位类型页数
	public int getPageCountByAll(int rows) throws Exception;
}
