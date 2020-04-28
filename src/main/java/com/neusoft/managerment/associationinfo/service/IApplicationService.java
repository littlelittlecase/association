package com.neusoft.managerment.associationinfo.service;

import java.util.List;

import com.neusoft.managerment.associationinfo.model.ApplicationModel;

/**
 *    模模块：社团基础信息   表：社团审核表 Application
 */
public interface IApplicationService {
	   
		//修改
		public void update(ApplicationModel applicationmodel)throws Exception;
		//查询
		public List<ApplicationModel>getall()throws Exception;
		//根据状态查询
		public List<ApplicationModel>getbyname(String appname)throws Exception;
		//获取社团名
		public List<ApplicationModel>getname()throws Exception;
		//获取指定社团学院
		public ApplicationModel getByName(String appid)throws Exception;
	    
		//根据综合检索条件取得社团审核列表
		public List<ApplicationModel> getListByConditionWithPage(
					String appname,
					int rows, int page
					)throws Exception;
		//根据综合检索条件取得审核个数
		public int getCountByCondition (
					String appname
					)throws Exception;
		//取得页数
		public int getPageByConditionWithPage(
					String appname,
					int rows) throws Exception;
}
