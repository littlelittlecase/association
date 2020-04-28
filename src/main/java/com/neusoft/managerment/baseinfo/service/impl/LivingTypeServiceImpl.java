package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.ILivingTypeMapper;
import com.neusoft.managerment.baseinfo.model.LivingTypeModel;
import com.neusoft.managerment.baseinfo.service.ILivingTypeService;

/**
 *    模块：基础信息   表：居住类型 LivingType
 *    author : 陈思颖
 *    version: 1.0
 */

//客户类型的业务实现类
@Service("livingTypeService")
@Transactional(rollbackFor=Exception.class)
public class LivingTypeServiceImpl implements ILivingTypeService {
	@Autowired
	private ILivingTypeMapper livingTypeMapper=null;
	
	@Override
	public void add(LivingTypeModel livingtype) throws Exception {
		livingTypeMapper.create(livingtype);

	}

	@Override
	public void modify(LivingTypeModel livingtype) throws Exception {
		livingTypeMapper.update(livingtype);

	}

	@Override
	public void delete(LivingTypeModel livingtype) throws Exception {
		livingTypeMapper.delete(livingtype);

	}

	@Override
	public List<LivingTypeModel> getListByAll() throws Exception {
		
		return livingTypeMapper.selectListByAll();
	}

	@Override
	public LivingTypeModel getListByTypeNo(int typeno) throws Exception {
		
		return livingTypeMapper.selectListByTypeNo(typeno);
	}


}
