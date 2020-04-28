package com.neusoft.managerment.associationinfo.service.imlp;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.associationinfo.mapper.IApplicationMapper;
import com.neusoft.managerment.associationinfo.model.ApplicationModel;

import com.neusoft.managerment.associationinfo.service.IApplicationService;


//学院审核的业务实现类
@Service("applicationservice")
public class ApplicationServiceImpl implements IApplicationService{

	private SqlSessionFactory sessionfactory = null;
	@Autowired 
	private IApplicationMapper applicationmapper = null;
	
	@Override
	public void update(ApplicationModel applicationmodel) throws Exception {
		// TODO Auto-generated method stub
		applicationmapper.update(applicationmodel);

	}

	@Override
	public List<ApplicationModel> getall() throws Exception {
		// TODO Auto-generated method stub
		return applicationmapper.selectListByAll();
	}

	@Override
	public List<ApplicationModel> getbyname(String appname) throws Exception {
		List<ApplicationModel> list = applicationmapper.selectListByName(appname);
		return list;
	}

	@Override
	public List<ApplicationModel> getname() throws Exception {
		return applicationmapper.selectname();
	}

	@Override
	public ApplicationModel getByName(String appid) throws Exception {
		return applicationmapper.selectbyname(appid);

	}

	@Override
	public List<ApplicationModel> getListByConditionWithPage(String appname, int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return applicationmapper.selectListByConditionWithPage(appname,  rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String appname) throws Exception {
		return applicationmapper.selectCountByCondition(appname);

	}

	@Override
	@Transactional(readOnly = true)
	public int getPageByConditionWithPage(String appname, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(appname);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
