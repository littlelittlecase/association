package com.neusoft.managerment.office.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.office.model.NewsModel;

//新闻的Mapper层接口
@Mapper
public interface INewsMapper {
	//c  增添方法
	public void create(NewsModel newsmodel) throws Exception;
	//u  修改方法
	public void update(NewsModel newsmodel) throws Exception;
	//d  删除方法
	public void delete(NewsModel newsmodel) throws Exception;
	
	//R  查询 取得列表 只取的新闻
	public List<NewsModel> selectListByAll() throws Exception;
	public List<NewsModel> selectListType(String newstype) throws Exception;
	//查询新闻类型
	public List<NewsModel>selecttype()throws Exception;
	
	//取得所有新聞列表,分页模式
   public List<NewsModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
   
   //取得新闻个数
   public int selectcountbyall() throws Exception;
   //取得指定新闻
   public NewsModel selectbyno(int newsno) throws Exception;
   
 //根据综合检索条件取得新闻列表,分页
 	public List<NewsModel> selectListByConditionWithPage(
 			@Param("newstype") String newstype,
 			@Param("startDate") Date startDate,
 			@Param("endDate") Date endDate,
 			@Param("start") int start,
 			@Param("rows") int rows) throws Exception;
 	
 	//根据综合检索条件取得新闻个数
 	public int selectCountByCondition(
 			@Param("newstype") String newstype,
 			@Param("startDate") Date startDate,
 			@Param("endDate") Date endDate) throws Exception;

   
	

}
