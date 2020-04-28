package com.neusoft.managerment.baseinfo.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.neusoft.managerment.baseinfo.model.CustomerHomeModel;
import com.neusoft.managerment.baseinfo.model.CustomerModel;


/**
 *    模块：基础信息   表：客户房间 CustomerHome
 *    author : 陈思颖
 *    version: 1.0
 */
//客户房间的Mapper接口
@Mapper
public interface ICustomerHomeMapper {
	//增加
	public void create(CustomerHomeModel customerHomeModel) throws Exception;
	//修改
	public void update(CustomerHomeModel customerHomeModel) throws Exception;
	//删除
	public void delete(CustomerHomeModel customerHomeModel) throws Exception;
	//1 取得客户房间的信息,无关联
	public List<CustomerHomeModel> selectListByAll() throws Exception;
	//2 取得所有客户房间列表,有关联,分页模式
	public List<CustomerHomeModel> selectListByAllWithFKWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;	
	//3 取得指定客户房间的信息
	public CustomerHomeModel selectByCHNO(int chno) throws Exception;
	//4 取得客户房间的个数
	public int selectCountByAll() throws Exception;
	//5 根据综合检索条件取得客户房间列表
	public List<CustomerHomeModel> selectListByConditionWithPage(
			@Param("customerno") int customerno, 
			@Param("livedate") Date livedate, 
			@Param("receivedate") Date receivedate, 
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//6 根据综合检索条件取得客户房间个数 
	public int selectCountByCondition(
			@Param("customerno") int customerno, 
			@Param("livedate") Date livedate, 
			@Param("receivedate") Date receivedate
			) throws Exception;
	//7  取得客户房间的页数
	public int selectPageCountByAll(int rows);
	//8  根据综合检索条件取得客户房间页数 
	public int selectPageCountByConditionWithPage(int customerno, Date livedate, Date receivedate, int rows) throws Exception;
}
