package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IMaintainProviderMapper;
import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.service.IMaintainproviderService;
/**
 *    模块：基础信息   表：维修单位 maintainprovider
 *    author : 张梓琪
 *    version: 1.0
 */

//客户类型的业务实现类
@Service("MainTainService")
@Transactional(rollbackFor=Exception.class)
public class MaintainproviderServiceImpl implements IMaintainproviderService{

	@Autowired
	public IMaintainProviderMapper maintainmapper = null;
	@Override
	public void add(MaintainProviderModel maintainmodel) throws Exception {
		maintainmapper.create(maintainmodel);
		
	}

	@Override
	public void delete(MaintainProviderModel maintainmodel) throws Exception {
		maintainmapper.delete(maintainmodel);
		
	}

	@Override
	public void update(MaintainProviderModel maintainmodel) throws Exception {
		maintainmapper.update(maintainmodel);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<MaintainProviderModel> getListByConditionWithPage(String pname, String paddress, int rows, int page)
			throws Exception {
		
		return maintainmapper.selectListByConditionWithPage(pname, paddress, rows*(page-1), rows);
				
	}

	@Override
	public int getCountByCondition(String pname, String paddress) throws Exception {
		
		return maintainmapper.selectCountByCondition(pname, paddress);
	}

	@Override
	public int getPageByConditionWithPage(String pname, String paddress, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(pname, paddress);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public List<MaintainProviderModel> getall() throws Exception {
		
		return maintainmapper.selectall();
	}

}
