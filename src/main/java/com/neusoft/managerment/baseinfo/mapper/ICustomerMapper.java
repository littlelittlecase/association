package com.neusoft.managerment.baseinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.neusoft.managerment.baseinfo.model.CustomerModel;

/**
 *    模块：基础信息   表：客户 Customer
 *    author : 陈思颖
 *    version: 1.0
 */
//客户的Mapper接口
@Mapper
public interface ICustomerMapper {
	//增加
	public void create(CustomerModel customerModel) throws Exception;
	//修改
	public void update(CustomerModel customerModel) throws Exception;
	//删除
	public void delete(CustomerModel customerModel) throws Exception;
	//1 取得客户的信息,无关联客户类型
	public List<CustomerModel> selectListByAll() throws Exception;
	//2 取得所有客户列表,关联客户类型,分页模式
	public List<CustomerModel> selectListByAllWithCustomerTypeWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;	
	//3 取得指定客户的信息
	public CustomerModel selectByCustomerNo(int customerno) throws Exception;
	//4 取得客户的个数
	public int selectCountByAll() throws Exception;
	//5 根据综合检索条件取得客户列表
	public List<CustomerModel> selectListByConditionWithPage(
			@Param("typeno") int typeno, 
			@Param("ccode") String ccode, 
			@Param("cname") String cname,
			@Param("feestartdate") Date feestartdate, 
			@Param("feeenddate") Date feeenddate,
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//6 根据综合检索条件取得客户个数 
	public int selectCountByCondition(
			@Param("typeno") int typeno, 
			@Param("ccode") String ccode, 
			@Param("cname") String cname, 
			@Param("feestartdate") Date feestartdate, 
			@Param("feeenddate") Date feeenddate) throws Exception;
	//7  取得客户的页数
	public int selectPageCountByAll(int rows);
	//8  根据综合检索条件取得客户页数 
	public int selectPageCountByConditionWithPage(int typeno, String ccode, String cname, 
			Date feestartdate, Date feeenddate, int rows) throws Exception;

}
