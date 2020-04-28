package com.neusoft.managerment.baseinfo.service;

import java.util.Date;
import java.util.List;

import com.neusoft.managerment.baseinfo.model.CustomerHomeModel;
import com.neusoft.managerment.baseinfo.model.CustomerModel;


/**
 *    模块：基础信息   表：客户房间 CustomerHome
 *    author : 陈思颖
 *    version: 1.0
 */
public interface ICustomerHomeService {
	//增加
	public void add(CustomerHomeModel customerhome) throws Exception;
	//修改
	public void modify(CustomerHomeModel customerhome) throws Exception;
	//删除
	public void delete(CustomerHomeModel customerhome) throws Exception;
	//1 取得所有客户房间列表,无关联
	public List<CustomerHomeModel> getListByAll() throws Exception;
	//2 取得所有客户房间列表,有关联,分页模式
	public List<CustomerHomeModel> getListByAllWithFKWithPage(int rows,int page) throws Exception;
	//3 取得指定客户房间对象
	public CustomerHomeModel getByCHNO(int chno) throws Exception;
	//4 取得客户房间的个数
	public int getCountByAll() throws Exception;
	//5 根据综合检索条件取得客户房间列表
	public List<CustomerHomeModel> getListByConditionWithPage(int customerno, Date livedate, Date receivedate, int rows,int page) throws Exception;
	//6 根据综合检索条件取得客户房间个数 
	public int getCountByCondition(int customerno,Date livedate, Date receivedate) throws Exception;
	//7 取得客户房间的页数
	public int getPageCountByAll(int rows) throws Exception;
	//8  根据综合检索条件取得客户房间页数 
	public int getPageCountByConditionWithPage(int customerno, Date livedate, Date receivedate, int rows) throws Exception;
}
