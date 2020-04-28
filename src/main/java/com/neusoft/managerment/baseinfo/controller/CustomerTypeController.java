package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.managerment.baseinfo.model.CustomerTypeModel;
import com.neusoft.managerment.baseinfo.service.ICustomerTypeService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息  表：客户类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
//客户类型的控制类
@RestController
@RequestMapping("/customertype")
public class CustomerTypeController {
	@Autowired
	private ICustomerTypeService customerTypeService=null;
	
	//增加客户类型
	@PostMapping("/add")
	public ResultMessage<CustomerTypeModel> add(CustomerTypeModel customertype) throws Exception {
		customerTypeService.add(customertype);
		return new ResultMessage<CustomerTypeModel>("OK","增加客户类型成功");
	}
	//修改客户类型
	@PostMapping("/modify")
	public ResultMessage<CustomerTypeModel> modify(CustomerTypeModel customertype) throws Exception {
		customerTypeService.modify(customertype);
		return new ResultMessage<CustomerTypeModel>("OK","修改客户类型成功");
	}
	//删除客户类型
	@PostMapping("/delete")
	public ResultMessage<CustomerTypeModel> delete(CustomerTypeModel customertype) throws Exception {
		customerTypeService.delete(customertype);
		return new ResultMessage<CustomerTypeModel>("OK","删除客户类型成功");
	}
	//取得指定的客户类型
	@GetMapping("/get")
	public CustomerTypeModel getListByTypeNo(int typeno) throws Exception{
		return customerTypeService.getListByTypeNo(typeno);
	}
	
	//取得所有客户类型列表，无分页
	@GetMapping(value="/list/all")
	public List<CustomerTypeModel> getListByAll() throws Exception{
		return customerTypeService.getListByAll();
	}
	
}
