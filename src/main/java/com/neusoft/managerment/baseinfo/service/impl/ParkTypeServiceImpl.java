package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IParkTypeMapper;
import com.neusoft.managerment.baseinfo.model.ParkTypeModel;
import com.neusoft.managerment.baseinfo.service.IParkTypeService;
/**
 *    模块：基础信息   表：车位类型 ParkType
 * 车位类型业务层接口的实现类
 * @Author: 吕淑兰
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParkTypeServiceImpl implements IParkTypeService {

	@Autowired
	public IParkTypeMapper parkTypeMapper=null;
	
	@Override
	public void add(ParkTypeModel packTypeModel) throws Exception {
		parkTypeMapper.create(packTypeModel);

	}

	@Override
	public void modify(ParkTypeModel packTypeModel) throws Exception {
		parkTypeMapper.update(packTypeModel);

	}

	@Override
	public void delete(ParkTypeModel packTypeModel) throws Exception {
		parkTypeMapper.delete(packTypeModel);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkTypeModel> getListByAll() throws Exception {
		
		return parkTypeMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ParkTypeModel getByNo(int no) throws Exception {
		
		return parkTypeMapper.selectByNo(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return parkTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return parkTypeMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
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

}
