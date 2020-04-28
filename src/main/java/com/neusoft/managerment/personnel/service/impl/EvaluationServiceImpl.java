package com.neusoft.managerment.personnel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.personnel.mapper.IEvaluationMapper;
import com.neusoft.managerment.personnel.model.EvaluationModel;
import com.neusoft.managerment.personnel.service.IEvaluationService;
@Service
@Transactional(rollbackFor=Exception.class)
public class EvaluationServiceImpl implements IEvaluationService {

	private IEvaluationMapper evaMapper=null;
	@Override
	public void add(EvaluationModel evaModel) throws Exception {
		evaMapper.create(evaModel);
	}

	@Override
	public void delete(EvaluationModel evaModel) throws Exception {
		evaMapper.delete(evaModel);

	}

	@Override
	public void modify(EvaluationModel evaModel) throws Exception {
		evaMapper.update(evaModel);

	}

	@Override
	public List<EvaluationModel> getListByAll() throws Exception {
		
		return evaMapper.selectListByAll();
	}

	@Override
	public EvaluationModel getEvaByNO(int no) throws Exception {
		
		return evaMapper.selectEvaByNO(no);
	}

	@Override
	public List<EvaluationModel> getEvaByCondition(int idd, String sex, String grade, int rows, int page)
			throws Exception {
		
		return evaMapper.selectListByConditionWithEmp(idd, sex, grade, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(int idd, String sex, String grade) throws Exception {
		
		return evaMapper.selectCountByCondition(idd, sex, grade);
	}

	@Override
	public int getPageByCondition(int idd, String sex, String grade, int rows) throws Exception {
		int pageCount=0;
		int count=evaMapper.selectCountByCondition(idd, sex, grade);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
