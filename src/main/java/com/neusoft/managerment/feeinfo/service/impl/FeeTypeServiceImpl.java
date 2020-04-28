package com.neusoft.managerment.feeinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.feeinfo.mapper.IFeeTypeMapper;
import com.neusoft.managerment.feeinfo.model.FeeTypeModel;
import com.neusoft.managerment.feeinfo.service.IFeeTypeService;
/**
 *    模块：收费   表：收费类型  FeeType
 * 收费类型业务层接口的实现类
 * @Author: 吕淑兰
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class FeeTypeServiceImpl implements IFeeTypeService {
	@Autowired
	private IFeeTypeMapper feeTypeMapper=null;
	
	@Override
	public void add(FeeTypeModel feeTypeModel) throws Exception {
		feeTypeMapper.create(feeTypeModel);

	}

	@Override
	public void modify(FeeTypeModel feeTypeModel) throws Exception {
		feeTypeMapper.update(feeTypeModel);

	}

	@Override
	public void delete(FeeTypeModel feeTypeModel) throws Exception {
		feeTypeMapper.delete(feeTypeModel);

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeeTypeModel> getListByAll() throws Exception {
		return feeTypeMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FeeTypeModel getByNo(int no) throws Exception {
		return feeTypeMapper.selectByNo(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FeeTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		return feeTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return feeTypeMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPagaCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
