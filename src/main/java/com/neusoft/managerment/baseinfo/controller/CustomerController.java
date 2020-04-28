package com.neusoft.managerment.baseinfo.controller;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.AreaModel;
import com.neusoft.managerment.baseinfo.model.CustomerModel;
import com.neusoft.managerment.baseinfo.service.ICustomerService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息  表：客户 Customer
 *    author : 陈思颖
 *    version: 1.0
 */
//客户的控制类
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService=null;
	
	//增加客户
	@PostMapping("/add")
	public ResultMessage<CustomerModel> add(CustomerModel customer) throws Exception {
		customerService.add(customer);
		return new ResultMessage<CustomerModel>("OK","增加客户成功");
	}
	//修改客户
	@PostMapping("/modify")
	public ResultMessage<CustomerModel> modify(CustomerModel customer) throws Exception {
		customerService.modify(customer);
		return new ResultMessage<CustomerModel>("OK","修改客户成功");
	}
	//删除客户
	@PostMapping("/delete")
	public ResultMessage<CustomerModel> delete(CustomerModel customer) throws Exception {
		customerService.delete(customer);
		return new ResultMessage<CustomerModel>("OK","删除客户成功");
	}

	//1 取得所有客户列表,无关联客户类型
	@GetMapping(value="/list/all")
	public List<CustomerModel> getListByAll() throws Exception{
		return customerService.getListByAll();
	}
	
	//2 取得所有客户列表,关联客户类型,分页模式
	@GetMapping(value="/list/all/page")
	public ResultMessage<CustomerModel> getListByAllWithCustomerTypeWithPage(@RequestParam(required = false,defaultValue ="10") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<CustomerModel> result=new ResultMessage<CustomerModel>("OK","取得客户列表分页模式成功");
		result.setCount(customerService.getCountByAll());
		result.setPageCount(customerService.getPageCountByAll(rows));
		result.setList(customerService.getListByAllWithCustomerTypeWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//3 取得指定客户对象
	@GetMapping("/get")
	public CustomerModel getByCustomerNo(int customerno) throws Exception{
		return customerService.getByCustomerNo(customerno);
	}
	
	//4 取得客户的个数
	

	//5 根据综合检索条件取得客户列表
	@GetMapping(value="/list/condition/page")
	public ResultMessage<CustomerModel> getListByConditionWithPage(
		@RequestParam(required = false,defaultValue ="0") int typeno,
		@RequestParam(required = false,defaultValue ="") String ccode,
		@RequestParam(required = false,defaultValue ="") String cname,
		@DateTimeFormat(pattern = "yyyy-MM-dd") 
		@RequestParam(required = false,defaultValue ="") Date feestartdate,
		@DateTimeFormat(pattern = "yyyy-MM-dd") 
		@RequestParam(required = false,defaultValue ="") Date feeenddate, 
		@RequestParam(required = false,defaultValue ="20") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) 
				throws Exception{
	ResultMessage<CustomerModel> result=new ResultMessage<CustomerModel>("OK","检索取得员工列表分页成功");
	result.setCount(customerService.getCountByCondition(typeno, ccode, cname, feestartdate, feeenddate));
	result.setPageCount(customerService.getPageCountByConditionWithPage(typeno, ccode, cname, feestartdate, feeenddate,  rows));
	result.setList(customerService.getListByConditionWithPage(typeno, ccode, cname,  feestartdate, feeenddate, rows, page));
	result.setPage(page);
	result.setRows(rows);
	
	return result;
	}
	
	//6 根据综合检索条件取得客户个数 
	
	
	
}
