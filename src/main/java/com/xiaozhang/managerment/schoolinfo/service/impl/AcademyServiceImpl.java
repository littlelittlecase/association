package com.xiaozhang.managerment.schoolinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaozhang.managerment.schoolinfo.mapper.IAcademyMapper;
import com.xiaozhang.managerment.schoolinfo.model.AcademyModel;
import com.xiaozhang.managerment.schoolinfo.service.IAcademyService;


//学院的业务实现类
@Service("academyservice")
public class AcademyServiceImpl implements IAcademyService {

	private SqlSessionFactory sessionfactory = null;
	@Autowired 
	private IAcademyMapper academymapper = null;
	
	@Override
	public void add(AcademyModel academymodel) throws Exception {
		// TODO Auto-generated method stub
		academymapper.create(academymodel);
		
	}

	@Override
	public void delete(AcademyModel academymodel) throws Exception {
		// TODO Auto-generated method stub
		academymapper.delete(academymodel);
		
	}

	@Override
	public void update(AcademyModel academymodel) throws Exception {
		// TODO Auto-generated method stub
		academymapper.update(academymodel);
		
	}

	@Override
	public List<AcademyModel> getall() throws Exception {
		// TODO Auto-generated method stub
		return academymapper.selectListByAll();
	}

	@Override
	public List<AcademyModel> getListByConditionWithPage(String academyname,  int rows, int page)
			throws Exception {
		return academymapper.selectListByConditionWithPage(academyname,  rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String academyname) throws Exception {
		// TODO Auto-generated method stub
		return academymapper.selectCountByCondition(academyname);
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageByConditionWithPage(String academyname,int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(academyname);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public List<AcademyModel> getname() throws Exception {
		return academymapper.selectname();
	}

	@Override
	public List<AcademyModel> getbyname(String academyname) throws Exception {
		List<AcademyModel> list = academymapper.selectListByName(academyname);
		return list;
	}

	//get academyid
	@Override
	public AcademyModel getByName(String academyid) throws Exception {
		// TODO Auto-generated method stub
		return academymapper.selectbyname(academyid);
	}

}
