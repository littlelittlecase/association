package com.neusoft.managerment.feeinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.feeinfo.model.FeeItemModel;
import com.neusoft.managerment.feeinfo.model.YearPriceModel;
import com.neusoft.managerment.feeinfo.service.IYearPriceService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *  模块：feeinfo 收费 
 *  年度价格 YearPrice控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/yearPrice")
public class YearPriceController {

	@Autowired
	private IYearPriceService yearPriceService=null;
	
	//增加年度价格
	@PostMapping(value="/add")
	public ResultMessage<YearPriceModel> add(YearPriceModel yearPriceModel,
			@RequestParam(required = false) int[] feeItems) throws Exception{
		if(feeItems!=null) {
			yearPriceService.add(yearPriceModel);
			yearPriceService.addFeeItem(yearPriceModel.getFeeyear(), feeItems);
		}
		
		return new ResultMessage<YearPriceModel>("OK","增加收费项目年度价格成功");
	}
	
	//修改年度价格
	@PostMapping(value="/modify")
	public ResultMessage<YearPriceModel> modify(YearPriceModel yearPriceModel,
			@RequestParam(required = false) int[] feeItems) throws Exception{
		if(feeItems!=null) {
			//先删除原有的收费项目
			yearPriceService.deleteFeeItem(yearPriceModel.getFeeyear());
			//再增加的收费项目
			yearPriceService.addFeeItem(yearPriceModel.getFeeyear(), feeItems);
		
			yearPriceService.modify(yearPriceModel);
		}
		return new ResultMessage<YearPriceModel>("OK","修改收费项目年度价格成功");
	}
	//删除年度价格
	@PostMapping(value="/delete")
	public ResultMessage<YearPriceModel> delete(YearPriceModel yearPriceModel,@RequestParam(required = false) int[] feeItems) throws Exception{
		
		//先删除原有的收费项目
		yearPriceService.deleteFeeItem(yearPriceModel.getFeeyear());
		//再删除年度价格
		yearPriceService.delete(yearPriceModel);
		return new ResultMessage<YearPriceModel>("OK","删除收费项目年度价格成功");
	}
	
	//取得年度价格表，关联收费项目
	@GetMapping("/list/all")
	public List<YearPriceModel> getListByAllWithFeeItem() throws Exception{
		return yearPriceService.getListByAllWithFeeItem();
	}
	//取得指定年度价格的信息，关联收费项
	@GetMapping("/get")
	public YearPriceModel getByFeeYearWithFeeItem(String feeyear) throws Exception{
		return yearPriceService.getByFeeYearWithFeeItem(feeyear);
	};
	//根据综合检索条件取得收费项目列表
	@GetMapping(value="/list/condition/page")
	public ResultMessage<YearPriceModel> getListByConditionWithPage(
			@RequestParam(required = false,defaultValue = "") String feeyear,
			@RequestParam(required = false,defaultValue = "0") int intemNo,
			@RequestParam(required = false,defaultValue = "") String minunitprice,
			@RequestParam(required = false,defaultValue = "") String maxunitprice,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate,
			@RequestParam(required = false,defaultValue = "10") int rows,
			@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		
		ResultMessage<YearPriceModel> result=new ResultMessage<YearPriceModel>("OK","取得收费项目列表分页成功");
		result.setCount(yearPriceService.getCountByCondition(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate));
		result.setPageCount(yearPriceService.getPageByCondition(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate, rows));
		result.setList(yearPriceService.getListByConditionWithFeeItemWithPage(feeyear, intemNo, minunitprice, maxunitprice, startDate, endDate, rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
}
