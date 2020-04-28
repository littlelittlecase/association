package com.neusoft.managerment.baseinfo.service;

import java.util.Date;
import java.util.List;
import com.neusoft.managerment.baseinfo.model.CustomerModel;


/**
 *    模块：基础信息   表：客户 Customer
 *    author : 陈思颖
 *    version: 1.0
 */
public interface ICustomerService {
	//增加
	public void add(CustomerModel customer) throws Exception;
	//修改
	public void modify(CustomerModel customer) throws Exception;
	//删除
	public void delete(CustomerModel customer) throws Exception;
	//1 取得所有客户列表,无关联客户类型
	public List<CustomerModel> getListByAll() throws Exception;
	//2 取得所有客户列表,关联客户类型,分页模式
	public List<CustomerModel> getListByAllWithCustomerTypeWithPage(int rows,int page) throws Exception;
	//3 取得指定客户对象
	public CustomerModel getByCustomerNo(int customerno) throws Exception;
	//4 取得客户的个数
	public int getCountByAll() throws Exception;
	//5 根据综合检索条件取得客户列表
	public List<CustomerModel> getListByConditionWithPage(int typeno, String ccode,  String cname, 
			 Date feestartdate,  Date feeenddate, int rows,int page) throws Exception;
	//6 根据综合检索条件取得客户个数 
	public int getCountByCondition(int typeno, String ccode,  String cname, 
			 Date feestartdate,  Date feeenddate) throws Exception;
	//7 取得客户的页数
	public int getPageCountByAll(int rows) throws Exception;
	//8  根据综合检索条件取得客户页数 
	public int getPageCountByConditionWithPage(int typeno, String ccode, String cname,
			Date feestartdate, Date feeenddate, int rows) throws Exception;
	
}
