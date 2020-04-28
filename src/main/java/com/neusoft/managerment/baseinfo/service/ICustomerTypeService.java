package com.neusoft.managerment.baseinfo.service;
import java.util.List;
import com.neusoft.managerment.baseinfo.model.CustomerTypeModel;

/**
 *    模块：基础信息   表：客户 类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
public interface ICustomerTypeService {
	//增加
	public void add(CustomerTypeModel customertype) throws Exception;
	//修改
	public void modify(CustomerTypeModel customertype) throws Exception;
	//删除
	public void delete(CustomerTypeModel customertype) throws Exception;
	//取得所有客户类型列表
	public CustomerTypeModel getListByTypeNo(int typeno) throws Exception;
	//取得指定客户类型列表
	public List<CustomerTypeModel> getListByAll() throws Exception;

}
