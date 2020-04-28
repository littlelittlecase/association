package com.neusoft.managerment.personnel.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.personnel.model.EvaluationModel;

@Mapper
public interface IEvaluationMapper {
	//增加
    public void create(EvaluationModel evaModel)throws Exception;
    //删除
    public void delete(EvaluationModel evaModel)throws Exception;
    //修改
    public void update(EvaluationModel evaModel) throws Exception;
    public EvaluationModel selectEvaByNO(int no) throws Exception;
	//返回所有员工信息
	public List<EvaluationModel> selectListByAll() throws Exception;
	//检索
	public List<EvaluationModel> selectListByConditionWithEmp(
			@Param("idd") int idd,
			@Param("sex") String sex,
			@Param("grade") String grade,
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//检索的个数
	public int selectCountByCondition(
			@Param("idd") int idd,
			@Param("sex") String sex,
			@Param("grade") String grade) throws Exception;
}
