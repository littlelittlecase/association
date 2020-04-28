package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IParkMapper;
import com.neusoft.managerment.baseinfo.model.ParkModel;
import com.neusoft.managerment.baseinfo.service.IParkService;

/**
 *    模块：基础信息  表：车位  Park
 * 车位业务层接口的实现类
 * @Author: 吕淑兰
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParkServiceImpl implements IParkService {

	@Autowired
	public IParkMapper parkMapper=null;
	
	@Override
	public void add(ParkModel parkModel) throws Exception {
		parkMapper.create(parkModel);

	}

	@Override
	public void modify(ParkModel parkModel) throws Exception {
		parkMapper.update(parkModel);

	}

	@Override
	public void delete(ParkModel parkModel) throws Exception {
		
		parkMapper.delete(parkModel);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByAll() throws Exception {
		
		return parkMapper.selectListByAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByAllWithParkType() throws Exception {
		
		return parkMapper.selectListByAllWithParkType();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByAllWithBuilding() throws Exception {
		
		return parkMapper.selectListByAllWithBuilding();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByAllWithParkTypeAndBuilding() throws Exception {
		
		return parkMapper.selectListByAllWithParkTypeAndBuilding();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByParkType(int ParkTypeNo) throws Exception {
		
		return parkMapper.selectListByParkType(ParkTypeNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByBuilding(int buildingNo) throws Exception {
		
		return parkMapper.selectListByBuilding(buildingNo);
	}

	@Override
	@Transactional(readOnly = true)
	public ParkModel getByNo(int no) throws Exception {
		
		return parkMapper.selectByNo(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return parkMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return parkMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPagaCountByAll(int rows) throws Exception {
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
	public List<ParkModel> getListByConditionWithParkTypeAndBuildingWithPage(int parkTypeNo, int buildingNo,
			String parkstatus, String rentunit, String feestatus, String mixarea, String maxarea, String minrentprice,
			String maxrentprice, int rows, int page) throws Exception {
		
		return parkMapper.selectListByConditionWithParkTypeAndBuildingWithPage(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(int parkTypeNo, int buildingNo, String parkstatus, String rentunit, String feestatus,
			String mixarea, String maxarea, String minrentprice, String maxrentprice) throws Exception {
		
		return parkMapper.selectCountByCondition(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice);
	}

	@Override
	public int getPageCountByCondition(int parkTypeNo, int buildingNo, String parkstatus, String rentunit,
			String feestatus, String mixarea, String maxarea, String minrentprice, String maxrentprice, int rows)
			throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
