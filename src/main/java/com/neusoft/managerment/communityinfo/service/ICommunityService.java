package com.neusoft.managerment.communityinfo.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.communityinfo.model.CommunityActiveModel;


/*
  * 社区业务层接口 
 * @Author: 张梓琪
 */
public interface ICommunityService {
	
	//增加
	public void add(CommunityActiveModel com) throws Exception;
	//修改
	public void modify(CommunityActiveModel com) throws Exception;
	//删除
	public void delete(CommunityActiveModel com) throws Exception;
	//取得活动同地方的对象
	public List<CommunityActiveModel> getListByplace(String activeplace)throws Exception;

	/*
	 * //取得活动同時間类型的对象 public List<CommunityActiveModel> getListBytime(Date
	 * activetime)throws Exception;
	 */
	//取得所有活动列表 
	public List<CommunityActiveModel> getListByAll() throws Exception;
	//根据索引取得社区活动个数
	public int getCountByConditionWithpage(int activeno,String activeplace,String activetype,String activecontent,Date startActiveDate,Date endActiveDate)throws Exception;
	//根据索引取得社区活动页数
	public int getPageCountByConditionWithPage(int activeno,String activeplace,String activetype,String activecontent,Date startActiveDate,Date endActiveDate, int rows)throws Exception;
	//根据索引取得列表
	public List<CommunityActiveModel> getListByConditionWithPage(int activeno,String activeplace,String activetype,String activecontent,Date startActiveDate,Date endActiveDate, int rows, int page)throws Exception;
	//取得指定的活动
	public CommunityActiveModel getByNo(int activeno)throws Exception;
	//取得活动类型列表
	 public List<CommunityActiveModel> gettype()throws Exception;
	 //取得活动地方列表
	 public List<CommunityActiveModel>getplace()throws Exception;
	

}
