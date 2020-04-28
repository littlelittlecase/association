package com.neusoft.managerment.associationinfo.service.imlp;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.associationinfo.mapper.IAssociationMapper;
import com.neusoft.managerment.associationinfo.model.AssociationModel;
import com.neusoft.managerment.associationinfo.service.IAssociationService;


//学院的业务实现类
@Service("associationservice")
public class AssociationServiceImpl implements IAssociationService {

	private SqlSessionFactory sessionfactory = null;
	@Autowired 
	private IAssociationMapper associationmapper = null;
	
	@Override
	public void add(AssociationModel associationmodel) throws Exception {
		// TODO Auto-generated method stub
		associationmapper.create(associationmodel);
		
	}

	@Override
	public void delete(AssociationModel associationmodel) throws Exception {
		// TODO Auto-generated method stub
		associationmapper.delete(associationmodel);
		
	}

	@Override
	public void update(AssociationModel associationmodel) throws Exception {
		// TODO Auto-generated method stub
		associationmapper.update(associationmodel);
		
	}

	@Override
	public List<AssociationModel> getall() throws Exception {
		// TODO Auto-generated method stub
		return associationmapper.selectListByAll();
	}

	@Override
	public List<AssociationModel> getListByConditionWithPage(String associationname,  int rows, int page)
			throws Exception {
		return associationmapper.selectListByConditionWithPage(associationname,  rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String associationname) throws Exception {
		// TODO Auto-generated method stub
		return associationmapper.selectCountByCondition(associationname);
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageByConditionWithPage(String associationname,int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(associationname);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public List<AssociationModel> getname() throws Exception {
		return associationmapper.selectname();
	}

	@Override
	public List<AssociationModel> getbyname(String associationname) throws Exception {
		List<AssociationModel> list = associationmapper.selectListByName(associationname);
		return list;
	}

	//get academyid
	@Override
	public AssociationModel getByName(String associationid) throws Exception {
		// TODO Auto-generated method stub
		return associationmapper.selectbyname(associationid);
	}

}
