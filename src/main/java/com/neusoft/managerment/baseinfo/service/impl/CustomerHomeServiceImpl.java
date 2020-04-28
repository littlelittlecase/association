package com.neusoft.managerment.baseinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.ICustomerHomeMapper;
import com.neusoft.managerment.baseinfo.model.CustomerHomeModel;
import com.neusoft.managerment.baseinfo.service.ICustomerHomeService;
/**
 *    模块：基础信息   表：客户房间 CustomerHome
 *    author : 陈思颖
 *    version: 1.0
 */

//客户房间的业务实现类
@Service("customerHomeService")
@Transactional(rollbackFor=Exception.class)
public class CustomerHomeServiceImpl implements ICustomerHomeService {

	@Autowired
	private ICustomerHomeMapper customerHomeMapper=null;
	
	@Override
	public void add(CustomerHomeModel customerhome) throws Exception {
		
		customerHomeMapper.create(customerhome);
	}

	@Override
	public void modify(CustomerHomeModel customerhome) throws Exception {
		
		customerHomeMapper.update(customerhome);
	}

	@Override
	public void delete(CustomerHomeModel customerhome) throws Exception {
		
		customerHomeMapper.delete(customerhome);
	}

	@Override
	public List<CustomerHomeModel> getListByAll() throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectListByAll();
	}

	@Override
	public List<CustomerHomeModel> getListByAllWithFKWithPage(int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectListByAllWithFKWithPage(rows*(page-1), rows);
	}

	@Override
	public CustomerHomeModel getByCHNO(int chno) throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectByCHNO(chno);
	}

	@Override
	public int getCountByAll() throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectCountByAll();
	}

	@Override
	public List<CustomerHomeModel> getListByConditionWithPage(int customerno, Date livedate, Date receivedate, int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectListByConditionWithPage(customerno, livedate, receivedate, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(int customerno,Date livedate, Date receivedate) throws Exception {
		// TODO Auto-generated method stub
		return customerHomeMapper.selectCountByCondition(customerno,livedate, receivedate);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
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
	public int getPageCountByConditionWithPage(int customerno, Date livedate, Date receivedate ,int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(customerno,livedate, receivedate);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}



}
