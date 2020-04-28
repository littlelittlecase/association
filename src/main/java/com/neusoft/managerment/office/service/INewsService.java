package com.neusoft.managerment.office.service;

import java.util.Date;
import java.util.List;


import com.neusoft.managerment.office.model.NewsModel;

/*
 * 2019.8.9
   *      张梓琪
 * 新闻的业务接口
 * */
public interface INewsService {
	//发布新闻
	public void relesenews(NewsModel newsmodel) throws Exception;
	//删除新闻
	public void deletenews(NewsModel newsmodel) throws Exception;
	//修改新闻
	public void modifynews(NewsModel newsmodel) throws Exception;
	//查询新闻
	public List<NewsModel> getnewsbyall() throws Exception;
	public List<NewsModel> getnewsbytype(String newstype)throws Exception;
	//查询新闻类型
	public List<NewsModel>gettype()throws Exception;
	
	//取得所有新闻列表,分页模式
     public List<NewsModel> getListByAllWithPage(int rows,int page) throws Exception;
     //取得新闻个数
     public int getcountbyall() throws Exception;
    //取得新闻页数
 	public int getPageCountByAll(int rows) throws Exception;
 	//取得指定新闻
 	public NewsModel getByNo(int newsno)throws Exception;
 	//根据综合检索条件取得新闻列表,分页
    public List<NewsModel> getListByConditionWithPage(
 				String newstype,
 				Date startDate,
 				Date endDate,
 				int rows, int page) throws Exception;
 		
 	//根据综合检索条件取得新闻个数
 	public int getCountByCondition(
 				String newstype,
 				Date startDate,
 				Date endDate) throws Exception;
 		
 	//根据综合检索条件取得新闻显示的页数
 	public int getPageByConditionWithPage(
 				String newstype,
 				Date startDate,
 				Date endDate,
 				int rows) throws Exception;
	 

}
