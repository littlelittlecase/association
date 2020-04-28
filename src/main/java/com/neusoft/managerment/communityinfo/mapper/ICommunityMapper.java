package com.neusoft.managerment.communityinfo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.communityinfo.model.CommunityActiveModel;


/*
 * 社区活动的DAO层Mapper接口
 * @Author: 张梓琪
 */
@Mapper
public interface ICommunityMapper {
	// 增加
	public void create(CommunityActiveModel con) throws Exception;

	// 修改
	public void update(CommunityActiveModel con) throws Exception;

	// 删除
	public void delete(CommunityActiveModel con) throws Exception;

	// 查 取得所有活动
	public List<CommunityActiveModel> selectListByAll() throws Exception;

	// 取得指定地方的活动
	public List<CommunityActiveModel> selectListByPlace(String activeplace) throws Exception;

	/*
	 * //取得指定时间的活动 public List<CommunityActiveModel> selectListByTime(Date
	 * activetime)throws Exception;
	 */
	// 根据索引取得社区活动个数
	public int selectCountByConditionWithpage(@Param("activeno") int activeno,@Param("activeplace") String activeplace,@Param("activetype") String activetype,@Param("activecontent") String activecontent,@Param("startActiveDate") Date startActiveDate,@Param("endActiveDate") Date endActiveDate) throws Exception;

	// 根据索引取得列表
	public List<CommunityActiveModel> selectListByConditionWithPage(@Param("activeno") int activeno,@Param("activeplace") String activeplace,@Param("activetype") String activetype,@Param("activecontent") String activecontent,@Param("startActiveDate") Date startActiveDate,@Param("endActiveDate") Date endActiveDate, @Param("start") int start,@Param("rows") int rows) throws Exception;

	//根据编号取得指定的活动
	 public CommunityActiveModel selectbyno(int activeno) throws Exception;
	 //取得活动类型列表
	 public List<CommunityActiveModel> selecttype()throws Exception;
	 //取得活动地方列表
	 public List<CommunityActiveModel>selectplace()throws Exception;
	 
}
