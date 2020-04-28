package com.neusoft.managerment.communityinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.managerment.communityinfo.mapper.ICommunityMapper;
import com.neusoft.managerment.communityinfo.model.CommunityActiveModel;
import com.neusoft.managerment.communityinfo.service.ICommunityService;
/*
 * 模块：社区模块
 * 社区活动业务实现类
 * @Author: 张梓琪
 */
@Service("actservice")
public class CommunityServiceimpl implements ICommunityService {
	private SqlSessionFactory sessionfactory = null;
	@Autowired
	private ICommunityMapper communityMapper = null;
	
	

	@Override
	public void add(CommunityActiveModel com) throws Exception {
		communityMapper.create(com);
		
	}

	@Override
	public void modify(CommunityActiveModel com) throws Exception {
		communityMapper.update(com);
		
	}

	@Override
	public void delete(CommunityActiveModel com) throws Exception {
		communityMapper.delete(com);
		
	}

	

	@Override
	public List<CommunityActiveModel> getListByAll() throws Exception {
		return communityMapper.selectListByAll();
	}

	

	@Override
	public List<CommunityActiveModel> getListByplace(String activeplace) throws Exception {
		List <CommunityActiveModel> list = communityMapper.selectListByPlace(activeplace);
		return list;
	}

	
	
	@Override
	public int getCountByConditionWithpage(int activeno, String activeplace, String activetype, String activecontent,
			Date startActiveDate, Date endActiveDate) throws Exception {
		
		return communityMapper.selectCountByConditionWithpage(activeno, activeplace, activetype, activecontent, startActiveDate, endActiveDate);
	}

	@Override
	public int getPageCountByConditionWithPage(int activeno, String activeplace, String activetype,
			String activecontent, Date startActiveDate, Date endActiveDate, int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByConditionWithpage(activeno, activeplace, activetype, activecontent, startActiveDate, endActiveDate);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	//根据索引查询，有分页
	@Override
	public List<CommunityActiveModel> getListByConditionWithPage(int activeno, String activeplace, String activetype,
			String activecontent, Date startActiveDate, Date endActiveDate, int rows, int page) throws Exception {
		
		return communityMapper.selectListByConditionWithPage(activeno, activeplace, activetype, activecontent, startActiveDate, endActiveDate, rows*(page-1), rows);
	}

	@Override
	public CommunityActiveModel getByNo(int activeno) throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.selectbyno(activeno);
	}

	@Override
	public List<CommunityActiveModel> gettype() throws Exception {
		// TODO Auto-generated method stub
		return communityMapper.selecttype();
	}

	@Override
	public List<CommunityActiveModel> getplace() throws Exception {
		
		return communityMapper.selectplace();
	}

	
	

	/*
	 * @Override public List<CommunityActiveModel> getListBytime(Date activetime)
	 * throws Exception { List <CommunityActiveModel> list =
	 * communityMapper.selectListByTime(activetime); return list; }
	 */

}
