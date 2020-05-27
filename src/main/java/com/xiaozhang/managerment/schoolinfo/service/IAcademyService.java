package com.xiaozhang.managerment.schoolinfo.service;

import java.util.List;

import com.xiaozhang.managerment.schoolinfo.model.AcademyModel;

/**
 *    模模块：学校基础信息   表：学院表 Academy
 */
public interface IAcademyService {
	    //增加
		public void add(AcademyModel academymodel)throws Exception;
		//删除
		public void delete(AcademyModel academymodel)throws Exception;
		//修改
		public void update(AcademyModel academymodel)throws Exception;
		//查询
		public List<AcademyModel>getall()throws Exception;
		//根据学院名查询
		public List<AcademyModel>getbyname(String academyname)throws Exception;
		//获取学院名
		public List<AcademyModel>getname()throws Exception;
		//获取指定学院
		public AcademyModel getByName(String academyid)throws Exception;
	    
		//根据综合检索条件取得学院列表
		public List<AcademyModel> getListByConditionWithPage(
					String academyname,
					int rows, int page
					)throws Exception;
		//根据综合检索条件取得学院个数
		public int getCountByCondition (
					String academyname
					)throws Exception;
		//取得页数
		public int getPageByConditionWithPage(
					String academyname,
					int rows) throws Exception;
}
