package com.neusoft.managerment.personnel.service;

import java.util.List;

import com.neusoft.managerment.personnel.model.EvaluationModel;

public interface IEvaluationService {
	//增加
	public void add(EvaluationModel evaModel)throws Exception;
	//删除
	public void delete(EvaluationModel evaModel)throws Exception;
	//修改
	public void modify(EvaluationModel evaModel)throws Exception;
	//返回所有考评信息
	public List<EvaluationModel> getListByAll()throws Exception;
	//返回指定的考评信息
	public EvaluationModel getEvaByNO(int no)throws Exception;
	//检索
	public List<EvaluationModel> getEvaByCondition(int idd,String sex,String grade,int rows,int page)throws Exception;
	//检索的个数
	public int getCountByCondition(int idd,String sex,String grade)throws Exception;
	//检索的页数
	public int getPageByCondition(int idd,String sex,String grade,int rows)throws Exception;
}
