package com.neusoft.managerment.feeinfo.service;

import java.util.Date;
import java.util.List;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.model.MaintainRecordModel;
import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.message.ResultMessage;
import com.neusoft.managerment.office.model.NewsModel;

/*
 * 维修报修业务层接口
 * @Author: 张梓琪
 */
public interface IMaintainRecordService {

	//增加
	public void add(MaintainRecordModel maintainrecord) throws Exception;
	//修改
	public void modify(MaintainRecordModel maintainrecord) throws Exception;
	//删除
	public void delete(MaintainRecordModel maintainrecord) throws Exception;
	//取得所有维修保单列表,分页模式
    public List<MaintainRecordModel> getListByAllWithPage(int rows,int page) throws Exception;
    //取得维修类型
    public List<M_typeModel> gettype()throws Exception;
    //取得维修工人
    public List<WempModel> getwemp()throws Exception;
    //取得房间
    public List<RoomModel> getroom()throws Exception;
    //取得维修公司
    public List<MaintainProviderModel> getprovider()throws Exception;
    //取得维修保单个数
    public int getcountbyall() throws Exception;
    //取得指定的维修保单
    public MaintainRecordModel getByNo(int recordno)throws Exception;
   //取得维修页数
	public int getPageCountByAll(int rows) throws Exception;
	//根据综合检索取得保单个数
	public int getCountByCondition(
			int mtypeno,
			int roomno,
			Date startDate,
			Date endDate, 
			String wstatus,
			int providerno
			)throws Exception;
	//根据综合检索取得保单页数
	public int getPageByConditionWithPage(
			int mtypeno,
			int roomno,
			Date startDate,
			Date endDate, 
			String wstatus,
			int providerno,
			int rows
			)throws Exception;
	//根据综合检索条件取得保单列表
	public List<MaintainRecordModel> getListByConditionWithPage(
			int mtypeno,
			int roomno,
			Date startDate,
			Date endDate, 
			String wstatus,
			int providerno,
			int rows,
			int page
			)throws Exception;
	//查询所有保单列表
	public List<MaintainRecordModel> getListByALL() throws Exception;
}
