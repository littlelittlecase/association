package com.neusoft.managerment.feeinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.feeinfo.mapper.IMaintainRecordMapper;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.model.MaintainRecordModel;
import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.feeinfo.service.IMaintainRecordService;
import com.neusoft.managerment.message.ResultMessage;
/*
 * 
 * 维护维修业务层接口的实现类
 * @Author: 张梓琪
 */
@Service

public class MaintainRecordServiceImpl implements IMaintainRecordService{


	@Autowired
	private IMaintainRecordMapper maintainrecordmapper = null;

	@Override
	public void add(MaintainRecordModel maintainrecord) throws Exception {
		maintainrecordmapper.insert(maintainrecord);
		
	}

	@Override
	public void modify(MaintainRecordModel maintainrecord) throws Exception {
		maintainrecordmapper.update(maintainrecord);
		
	}

	@Override
	public void delete(MaintainRecordModel maintainrecord) throws Exception {
		maintainrecordmapper.delete(maintainrecord);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<MaintainRecordModel> getListByAllWithPage(int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return maintainrecordmapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	public int getcountbyall() throws Exception {
		
		return maintainrecordmapper.selectcountbyall();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		
		int pageCount=0;
		int count=this.getcountbyall();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
//综合检索部分
	@Override
	public int getCountByCondition(int mtypeno, int roomno, Date startDate, Date endDate, String wstatus,
			int providerno) throws Exception {
		
		return maintainrecordmapper.selectCountByCondition(mtypeno, roomno, startDate, endDate, wstatus, providerno);
	}
//查询页数
	@Override
	public int getPageByConditionWithPage(
							int mtypeno, 
							int roomno,
							Date startDate, 
							Date endDate,
							String wstatus,
							int providerno, 
							int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(mtypeno, roomno, startDate, endDate, wstatus, providerno);
				
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
		
	

	@Override
	public List<MaintainRecordModel> getListByConditionWithPage(int mtypeno, int roomno, Date startDate, Date endDate,
			String wstatus, int providerno, int rows, int page) throws Exception {
		
		return maintainrecordmapper.selectListByConditionWithPage(mtypeno, roomno, startDate, endDate, wstatus, providerno, rows*(page-1), rows);
	}

	//取得指定的维修保单
	@Override
	public MaintainRecordModel getByNo(int recordno) throws Exception {
		
		return maintainrecordmapper.selectByNo(recordno);
	}

	//查询所有维修表单
	@Override
	public List<MaintainRecordModel> getListByALL() throws Exception {
		
		return maintainrecordmapper.selectListByAll();
	}

	//查询所有维修类型
	@Override
	public List<M_typeModel> gettype() throws Exception {
		
		return maintainrecordmapper.selecttype();
	}

	@Override
	public List<WempModel> getwemp() throws Exception {
		// TODO Auto-generated method stub
		return maintainrecordmapper.selectwemp();
	}

	@Override
	public List<RoomModel> getroom() throws Exception {
		
		return maintainrecordmapper.selectroom();
	}

	@Override
	public List<MaintainProviderModel> getprovider() throws Exception {
		// TODO Auto-generated method stub
		return maintainrecordmapper.selectprovider();
	}
	}
	



