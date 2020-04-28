package com.neusoft.managerment.feeinfo.controller;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.feeinfo.model.FeeItemModel;
import com.neusoft.managerment.feeinfo.service.IFeeItemService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *  模块：feeinfo 收费 
 *  收费项目  FeeItem控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/feeItem")
public class FeeItemController {

	@Autowired
	private IFeeItemService feeItemService=null;
	
	//增加收费项目
	@PostMapping(value="/add")
	public ResultMessage<FeeItemModel> add(FeeItemModel feeItemModel) throws Exception{
		feeItemService.add(feeItemModel);
		return new ResultMessage<FeeItemModel>("OK", "增加收费项目成功");
	}
	//修改收费项目
	@PostMapping(value="/modify")
	public ResultMessage<FeeItemModel> modify(FeeItemModel feeItemModel) throws Exception{
		feeItemService.modify(feeItemModel);
		return new ResultMessage<FeeItemModel>("OK", "修改收费项目成功");
	}
	//删除收费项目
	@PostMapping(value="/delete")
	public ResultMessage<FeeItemModel> delete(FeeItemModel feeItemModel) throws Exception{
		feeItemService.delete(feeItemModel);
		return new ResultMessage<FeeItemModel>("OK", "删除收费项目成功");
	}
	
	//取得指定收费项目的信息
	@GetMapping("/get")
	public FeeItemModel getByNoWithFeeType(int no) throws Exception{
		return feeItemService.getByNoWithFeeType(no);
	}
	//取得所有收费项目列表
	@GetMapping("/list/unit")
	public List<FeeItemModel> getListByUnit() throws Exception{
		return feeItemService.getListByUnit();
	}
	//取得收费项目列表
	@GetMapping("/list/all")
	public List<FeeItemModel> getListByAll() throws Exception{
		return feeItemService.getListByAll();
	}
	//根据综合检索条件取得收费项目列表
	@GetMapping(value="/list/condition/page")
	public ResultMessage<FeeItemModel> getListByConditionWithPage(
			@RequestParam(required = false,defaultValue = "") String unit,
			@RequestParam(required = false,defaultValue = "0") int feetypeNo,
			@RequestParam(required = false,defaultValue = "") String cycle,
			@RequestParam(required = false,defaultValue = "") String status,
			@RequestParam(required = false,defaultValue = "10") int rows,
			@RequestParam(required = false,defaultValue = "1") int page)throws Exception{
		
		ResultMessage<FeeItemModel> result=new ResultMessage<FeeItemModel>("OK","取得收费项目列表分页成功");
		result.setCount(feeItemService.getCountByCondition(unit, feetypeNo, cycle, status));
		result.setPageCount(feeItemService.selectPageByConditionWithPage(unit, feetypeNo, cycle, status, rows));
		result.setList(feeItemService.getListByConditionWithPage(unit, feetypeNo, cycle, status, rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
}
