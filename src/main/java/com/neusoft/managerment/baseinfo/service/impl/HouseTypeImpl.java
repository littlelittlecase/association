package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.managerment.baseinfo.mapper.IHouseTypeMapper;
import com.neusoft.managerment.baseinfo.model.HouseTypeModel;
import com.neusoft.managerment.baseinfo.service.IHouseTypeService;
/**
 *    模块：基础信息   表：户型  HouseType
 *    author : 陈思颖
 *    version: 1.0
 */
//户型的业务实现类
@Service("houseTypeService")
@Transactional(rollbackFor=Exception.class)
public class HouseTypeImpl implements IHouseTypeService {

	@Autowired
	private IHouseTypeMapper houseTypeMapper=null;
	
	@Override
	public void add(HouseTypeModel housetype) throws Exception {
		houseTypeMapper.create(housetype);

	}

	@Override
	public void modify(HouseTypeModel housetype) throws Exception {
		houseTypeMapper.update(housetype);

	}

	@Override
	public void delete(HouseTypeModel housetype) throws Exception {
		houseTypeMapper.delete(housetype);

	}

	@Override
	public List<HouseTypeModel> getListByAll() throws Exception {
		
		return houseTypeMapper.selectListByAll();
	}

	@Override
	public HouseTypeModel getByTypeNo(int typeno) throws Exception {
		
		return houseTypeMapper.selectByTypeNo(typeno);
	}

	@Override
	public List<HouseTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return houseTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}


}
