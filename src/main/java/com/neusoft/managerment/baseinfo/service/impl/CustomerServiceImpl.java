package com.neusoft.managerment.baseinfo.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.neusoft.managerment.baseinfo.mapper.ICustomerMapper;
import com.neusoft.managerment.baseinfo.model.CustomerModel;
import com.neusoft.managerment.baseinfo.service.ICustomerService;
/**
 *    模块：基础信息   表：客户 Customer
 *    author : 陈思颖
 *    version: 1.0
 */

//客户的业务实现类
@Service("customerService")
@Transactional(rollbackFor=Exception.class)
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerMapper customerMapper=null;
	
	@Autowired
	private RestTemplate rest = null;
	
	@Value("${neusoftcservice.url}")
	private String url = null;
	
	@Override
	public void add(CustomerModel customer) throws Exception {
		customerMapper.create(customer);

	}

	@Override
	public void modify(CustomerModel customer) throws Exception {
		customerMapper.update(customer);

	}

	@Override
	public void delete(CustomerModel customer) throws Exception {
		customerMapper.delete(customer);

	}

	@Override
	public List<CustomerModel> getListByAll() throws Exception {
		System.out.println(url);
		List<CustomerModel> list=rest.getForObject(url+"customer/list/all", List.class);
		
		return list;		
		//return customerMapper.selectListByAll();
	}

	@Override
	public List<CustomerModel> getListByAllWithCustomerTypeWithPage(int rows, int page) throws Exception {
		
		return customerMapper.selectListByAllWithCustomerTypeWithPage(rows*(page-1), rows);
	}
	
	@Override
	public CustomerModel getByCustomerNo(int customerno) throws Exception {
		
		return customerMapper.selectByCustomerNo(customerno);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return customerMapper.selectCountByAll();
	}

	@Override
	public List<CustomerModel> getListByConditionWithPage(int typeno, String ccode, String cname, Date feestartdate, Date feeenddate,  int rows, int page) throws Exception {
		
		return customerMapper.selectListByConditionWithPage(typeno, ccode, cname, feestartdate, feeenddate,  rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(int typeno, String ccode, String cname, Date feestartdate, Date feeenddate) throws Exception {
		
		return customerMapper.selectCountByCondition(typeno, ccode, cname, feestartdate, feeenddate);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception{
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public int getPageCountByConditionWithPage(int typeno, String ccode, String cname, Date feestartdate, Date feeenddate, int rows) throws Exception {

		int pageCount=0;
		int count=this.getCountByCondition(typeno, ccode, cname,feestartdate, feeenddate);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}



}
