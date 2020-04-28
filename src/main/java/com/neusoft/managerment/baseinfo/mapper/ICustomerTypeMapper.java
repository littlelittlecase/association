package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.BuildingTypeModel;
import com.neusoft.managerment.baseinfo.model.CustomerModel;
import com.neusoft.managerment.baseinfo.model.CustomerTypeModel;

/**
 *    模块：基础信息   表：客户类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
//客户类型的Mapper接口
@Mapper
public interface ICustomerTypeMapper {
	//增加
	public void create(CustomerTypeModel customerTypeModel) throws Exception;
	//修改
	public void update(CustomerTypeModel customerTypeModel) throws Exception;
	//删除
	public void delete(CustomerTypeModel customerTypeModel) throws Exception;
	//取得所有客户类型
	public List<CustomerTypeModel> selectListByAll() throws Exception;
	//取得指定客户类型
	public CustomerTypeModel selectListByTypeNo(int typeno) throws Exception;

}
