package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IBuildingTypeMapper;
import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.model.BuildingTypeModel;
import com.neusoft.managerment.baseinfo.service.IBuildingTypeService;
/**
 *    模块：基础信息  表：建筑类型 BuildingType
 *  建筑类型业务层接口的实现类
 * @Author: 吕淑兰
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class BuildingTypeServiceImpl implements IBuildingTypeService {
	@Autowired
	private IBuildingTypeMapper buildTypeMapper=null;
	
	@Override
	public void add(BuildingTypeModel buildTypeModel) throws Exception {
		buildTypeMapper.create(buildTypeModel);

	}

	@Override
	public void modify(BuildingTypeModel buildTypeModel) throws Exception {
		buildTypeMapper.update(buildTypeModel);

	}

	@Override
	public void delete(BuildingTypeModel buildTypeModel) throws Exception {
		buildTypeMapper.delete(buildTypeModel);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingTypeModel> getBuildTypeListByAll() throws Exception {
		
		return buildTypeMapper.selectBuildTypeListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public BuildingTypeModel getBuildTypeByNo(String no) throws Exception {
		
		return buildTypeMapper.selectBuildTypeByNo(no);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BuildingTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		return buildTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return buildTypeMapper.selectCountByAll();
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
