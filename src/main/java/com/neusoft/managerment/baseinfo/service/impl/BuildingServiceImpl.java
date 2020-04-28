package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IBuildingMapper;
import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.service.IBuildingService;

/**
 *    模块：基础信息  表：楼宇  Building
 * 楼宇业务层接口的实现类
 * @Author: 吕淑兰
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class BuildingServiceImpl implements IBuildingService {
	@Autowired
	private IBuildingMapper buildMapper=null;
	
	@Override
	public void add(BuildingModel buildModel) throws Exception {
		buildMapper.create(buildModel);

	}

	@Override
	public void modify(BuildingModel buildModel) throws Exception {
		buildMapper.update(buildModel);

	}

	@Override
	public void delete(BuildingModel buildModel) throws Exception {
		buildMapper.delete(buildModel);

	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAll() throws Exception {
		
		return buildMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAllWithArea() throws Exception {
		
		return buildMapper.selectListByAllWithArea();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAllWithBuildType() throws Exception {
		
		return buildMapper.selectListByAllWithBuildType();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAllWithAreaAndBuildType() throws Exception {
		
		return buildMapper.selectListByAllWithAreaAndBuildType();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByArea(int areaNo) throws Exception {
		
		return buildMapper.selectListByArea(areaNo);
	}

	/*
	 * @Override
	 * 
	 * @Transactional(readOnly = true) public List<BuildingModel>
	 * getListByBuildType(int buildTypeNo) throws Exception {
	 * 
	 * return buildMapper.selectListByBuildType(buildTypeNo); }
	 */

	@Override
	@Transactional(readOnly = true)
	public BuildingModel getByNo(String no) throws Exception {
		
		return buildMapper.selectByNo(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAllWithPage(int rows, int page) throws Exception {
		return buildMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return buildMapper.selectCountByAll();
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

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByAllWithAreaAndBuildTypeWithPage(int rows,int page) throws Exception {
		
		return buildMapper.selectListByAllWithAreaAndBuildTypeWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BuildingModel> getListByConditionWithAreaAndBuildTypeWithPage(int areaNo, String buildingtypeNo,
			String code, String direction, int minhome, int maxhome, int minhouse, int maxhouse, int rows, int page)
			throws Exception {
		return buildMapper.selectListByConditionWithAreaAndBuildTypeWithPage(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse, rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByCondition(int areaNo, String buildingtypeNo, String code, String direction, int minhome,
			int maxhome, int minhouse, int maxhouse) throws Exception {
		return buildMapper.selectCountByCondition(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse);
	}

	@Override
	public int getPageByConditionWithPage(int areaNo, String buildingtypeNo, String code, String direction, int minhome,
			int maxhome, int minhouse, int maxhouse, int rows) throws Exception {
		
		int pageCount=0;
		int count=this.getCountByCondition(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
