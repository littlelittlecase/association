package com.neusoft.managerment.schoolinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.schoolinfo.mapper.IAcademyMapper;
import com.neusoft.managerment.schoolinfo.mapper.IMajoyMapper;
import com.neusoft.managerment.schoolinfo.model.AcademyModel;
import com.neusoft.managerment.schoolinfo.model.MajoyModel;
import com.neusoft.managerment.schoolinfo.service.IAcademyService;
import com.neusoft.managerment.schoolinfo.service.IMajoyService;


//学院的业务实现类
@Service("majoyservice")
public class MajoyServiceImpl implements IMajoyService {

	private SqlSessionFactory sessionfactory = null;
	@Autowired 
	private IMajoyMapper majoymapper = null;
	
	@Override
	public void add(MajoyModel majoymodel) throws Exception {
		// TODO Auto-generated method stub
		majoymapper.create(majoymodel);
		
	}

	@Override
	public void delete(MajoyModel majoymodel) throws Exception {
		// TODO Auto-generated method stub
		majoymapper.delete(majoymodel);
		
	}

	@Override
	public void update(MajoyModel majoymodel) throws Exception {
		// TODO Auto-generated method stub
		majoymapper.update(majoymodel);
		
	}

	@Override
	public List<MajoyModel> getall() throws Exception {
		// TODO Auto-generated method stub
		return majoymapper.selectListByAll();
	}

	@Override
	public List<MajoyModel> getListByConditionWithPage(String majoyname,  int rows, int page)
			throws Exception {
		return majoymapper.selectListByConditionWithPage(majoyname,  rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String majoyname) throws Exception {
		// TODO Auto-generated method stub
		return majoymapper.selectCountByCondition(majoyname);
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageByConditionWithPage(String majoyname,int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(majoyname);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public List<MajoyModel> getname() throws Exception {
		return majoymapper.selectname();
	}

	@Override
	public List<MajoyModel> getbyname(String majoyname) throws Exception {
		List<MajoyModel> list = majoymapper.selectListByName(majoyname);
		return list;
	}

	//get academyid
	@Override
	public MajoyModel getByName(String majoyid) throws Exception {
		// TODO Auto-generated method stub
		return majoymapper.selectbyname(majoyid);
	}

}
