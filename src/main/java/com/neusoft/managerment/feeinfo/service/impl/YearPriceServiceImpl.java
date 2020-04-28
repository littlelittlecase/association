package com.neusoft.managerment.feeinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.feeinfo.mapper.IYearPriceMapper;
import com.neusoft.managerment.feeinfo.model.YearPriceModel;
import com.neusoft.managerment.feeinfo.service.IYearPriceService;


/**
 *    模块：收费   表：年度价格 YearPrice
 * 年度价格业务层接口的实现类
 * @Author: 吕淑兰
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class YearPriceServiceImpl implements IYearPriceService {

	@Autowired
	private IYearPriceMapper yearPriceMapper=null;

	@Override
	public void add(YearPriceModel yearPriceModel) throws Exception {
		yearPriceMapper.create(yearPriceModel);
		
	}

	@Override
	public void modify(YearPriceModel yearPriceModel) throws Exception {
		yearPriceMapper.update(yearPriceModel);
		
	}

	@Override
	public void delete(YearPriceModel yearPriceModel) throws Exception {
		yearPriceMapper.delete(yearPriceModel);
		
	}

	@Override
	public List<YearPriceModel> getListByAllWithFeeItem() throws Exception {
		
		return yearPriceMapper.selectListByAllWithFeeItem();
	}

	@Override
	public YearPriceModel getByFeeYearWithFeeItem(String feeyear) throws Exception {
		
		return yearPriceMapper.selectByFeeYearWithFeeItem(feeyear);
	}

	@Override
	public void addFeeItem(String feeyear, int[] itemno) throws Exception {
		yearPriceMapper.addFeeItem(feeyear, itemno);
		
	}

	@Override
	public void deleteFeeItem(String feeyear) throws Exception {
		yearPriceMapper.deleteFeeItem(feeyear);
		
	}

	@Override
	public List<YearPriceModel> getListByConditionWithFeeItemWithPage(String feeyear, int intemNo,
			String minunitprice, String maxunitprice, Date startDate, Date endDate, int rows, int page)
			throws Exception {
		return yearPriceMapper.selectListByConditionWithFeeItemWithPage(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(String feeyear, int intemNo, String minunitprice, String maxunitprice,
			Date startDate, Date endDate) throws Exception {
		return yearPriceMapper.selectCountByCondition(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate);
	}

	@Override
	public int getPageByCondition(String feeyear, int intemNo, String minunitprice, String maxunitprice,
			Date startDate, Date endDate, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
}
