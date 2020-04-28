package com.neusoft.managerment.feeinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.feeinfo.mapper.IFeeItemMapper;
import com.neusoft.managerment.feeinfo.model.FeeItemModel;
import com.neusoft.managerment.feeinfo.service.IFeeItemService;
/**
 *    模块：收费   表：收费项目  FeeItem
 * 收费项目业务层接口的实现类
 * @Author: 吕淑兰
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class FeeItemServiceImpl implements IFeeItemService {

	@Autowired
	private IFeeItemMapper feeItemMapper=null;
	
	@Override
	public void add(FeeItemModel feeItemModel) throws Exception {
		feeItemMapper.create(feeItemModel);

	}

	@Override
	public void modify(FeeItemModel feeItemModel) throws Exception {
		feeItemMapper.update(feeItemModel);

	}

	@Override
	public void delete(FeeItemModel feeItemModel) throws Exception {
		feeItemMapper.delete(feeItemModel);

	}

	@Override
	public FeeItemModel getByNoWithFeeType(int no) throws Exception {
		
		return feeItemMapper.selectByNoWithFeeType(no);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return feeItemMapper.selectCountByAll();
	}

	@Override
	public List<FeeItemModel> getListByConditionWithPage(String unit, int feetypeNo, String cycle, String status,
			int rows, int page) throws Exception {
		
		return feeItemMapper.selectListByConditionWithPage(unit, feetypeNo, cycle, status, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String unit, int feetypeNo, String cycle, String status) throws Exception {
		
		return feeItemMapper.selectCountByCondition(unit, feetypeNo, cycle, status);
	}

	@Override
	public int selectPageByConditionWithPage(String unit, int feetypeNo, String cycle, String status, int rows)
			throws Exception {
		
		int pageCount=0;
		int count=this.getCountByCondition(unit, feetypeNo, cycle, status);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public List<FeeItemModel> getListByUnit() throws Exception {
		
		return feeItemMapper.selectListByUnit();
	}

	@Override
	public List<FeeItemModel> getListByAll() throws Exception {
		
		return feeItemMapper.selectListByAll();
	}

}
