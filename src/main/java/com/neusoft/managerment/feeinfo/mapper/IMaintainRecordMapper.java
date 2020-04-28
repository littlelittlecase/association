package com.neusoft.managerment.feeinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.model.MaintainRecordModel;
import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.message.ResultMessage;


/*
 * 维修报修的DAO层MyBatis Mapper接口
 * @Author: 张梓琪
 */

@Mapper
public interface IMaintainRecordMapper {
	
	//增添维修报修1 
	public void insert(MaintainRecordModel maintainrecord) throws Exception;
	//修改
	public void update(MaintainRecordModel maintainrecord) throws Exception;
	//删除
	public void delete(MaintainRecordModel maintainrecord) throws Exception;
	//取得指定的维修保单
	public MaintainRecordModel selectByNo(int recordno)throws Exception;
	
	//取得维修类型
	public List<M_typeModel> selecttype()throws Exception;
	//取得维修工人
	public List<WempModel> selectwemp()throws Exception;
	//取得房间号
	public List<RoomModel>selectroom()throws Exception;
	//取得维修公司
	public List<MaintainProviderModel> selectprovider()throws Exception;
	//取得所有维修表单列表,分页模式
	public List<MaintainRecordModel> selectListByAllWithPage(
		  @Param("start") int start,
		  @Param("rows") int rows) throws Exception;
	//取得维修类型个数
	public int selectcountbyall() throws Exception;
	//根据索引取得维修表单个数
	public int selectCountByCondition(
			@Param("mtypeno") int mtypeno,
			@Param("roomno") int roomno,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("wstatus") String wstatus,
			@Param("providerno") int providerno
			)throws Exception;
	//根据索引查询维修表单，关联m_type、room、wemp、maintainprovider
	public List<MaintainRecordModel> selectListByConditionWithPage(
			@Param("mtypeno") int mtypeno,
			@Param("roomno") int roomno,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("wstatus") String wstatus,
			@Param("providerno") int providerno,
			@Param("start") int start,
			@Param("rows") int rows
			)throws Exception;
	//查询所有维修表单
	public List<MaintainRecordModel> selectListByAll()throws Exception;
	
	
	

}
