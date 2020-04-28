package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.ICustomerTypeMapper;
import com.neusoft.managerment.baseinfo.model.CustomerTypeModel;
import com.neusoft.managerment.baseinfo.service.ICustomerTypeService;
/**
 *    模块：基础信息   表：客户类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */

//客户类型的业务实现类
@Service("customerTypeService")
@Transactional(rollbackFor=Exception.class)
public class CustomerTypeServiceImpl implements ICustomerTypeService {
	@Autowired
	private ICustomerTypeMapper customerTypeMapper=null;
	
	@Override
	public void add(CustomerTypeModel customertype) throws Exception {
		customerTypeMapper.create(customertype);

	}

	@Override
	public void modify(CustomerTypeModel customertype) throws Exception {
		customerTypeMapper.update(customertype);

	}

	@Override
	public void delete(CustomerTypeModel customertype) throws Exception {
		customerTypeMapper.delete(customertype);

	}

	@Override
	public List<CustomerTypeModel> getListByAll() throws Exception {
		
		return customerTypeMapper.selectListByAll();
	}

	@Override
	public CustomerTypeModel getListByTypeNo(int typeno) throws Exception {
		
		return customerTypeMapper.selectListByTypeNo(typeno);
	}


}
