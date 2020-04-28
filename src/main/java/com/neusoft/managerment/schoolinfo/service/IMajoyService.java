package com.neusoft.managerment.schoolinfo.service;

import java.util.List;

import com.neusoft.managerment.schoolinfo.model.AcademyModel;
import com.neusoft.managerment.schoolinfo.model.MajoyModel;

/**
 *    模模块：学校基础信息   表：专业表 majoy
 */
public interface IMajoyService {
	    //增加
		public void add(MajoyModel majoymodel)throws Exception;
		//删除
		public void delete(MajoyModel majoymodel)throws Exception;
		//修改
		public void update(MajoyModel majoymodel)throws Exception;
		//查询
		public List<MajoyModel>getall()throws Exception;
		//根据专业名查询
		public List<MajoyModel>getbyname(String majoyname)throws Exception;
		//获取专业名
		public List<MajoyModel>getname()throws Exception;
		//获取指定专业
		public MajoyModel getByName(String majoyid)throws Exception;
	    
		//根据综合检索条件取得专业列表
		public List<MajoyModel> getListByConditionWithPage(
					String majoyname,
					int rows, int page
					)throws Exception;
		//根据综合检索条件取得专业个数
		public int getCountByCondition (
					String majoyname
					)throws Exception;
		//取得页数
		public int getPageByConditionWithPage(
					String majoyname,
					int rows) throws Exception;
}
