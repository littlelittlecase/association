package com.neusoft.managerment.associationinfo.service;

import java.util.List;

import com.neusoft.managerment.associationinfo.model.AssociationModel;


/**
 *    模模块：学校基础信息   表：社团表 Association
 */
public interface IAssociationService {
	    //增加
		public void add(AssociationModel asociationmodel)throws Exception;
		//删除
		public void delete(AssociationModel associationmodel)throws Exception;
		//修改
		public void update(AssociationModel associationmodel)throws Exception;
		//查询
		public List<AssociationModel>getall()throws Exception;
		//根据学院名查询
		public List<AssociationModel>getbyname(String associationname)throws Exception;
		//获取学院名
		public List<AssociationModel>getname()throws Exception;
		//获取指定学院
		public AssociationModel getByName(String associationid)throws Exception;
	    
		//根据综合检索条件取得学院列表
		public List<AssociationModel> getListByConditionWithPage(
					String associationname,
					int rows, int page
					)throws Exception;
		//根据综合检索条件取得学院个数
		public int getCountByCondition (
					String associationname
					)throws Exception;
		//取得页数
		public int getPageByConditionWithPage(
					String associationname,
					int rows) throws Exception;
}
