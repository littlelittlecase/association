package com.neusoft.managerment.feeinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.feeinfo.model.FeeTypeModel;
import com.neusoft.managerment.feeinfo.service.IFeeTypeService;
import com.neusoft.managerment.message.ResultMessage;
/**
 *  模块：feeinfo 收费 
 *  收费类型控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/feeType")
public class FeeTypeController {
	@Autowired
	private IFeeTypeService fts=null;
	
	//增加收费类型
	@PostMapping(value="/add")
	public ResultMessage<FeeTypeModel> add(FeeTypeModel feeTypeModel) throws Exception {
		fts.add(feeTypeModel);
		return new ResultMessage<FeeTypeModel>("OK","增加收费类型成功");
	}
	//修改收费类型
	@PostMapping(value="/modify")
	public ResultMessage<FeeTypeModel> update(FeeTypeModel feeTypeModel) throws Exception {
		fts.modify(feeTypeModel);
		return new ResultMessage<FeeTypeModel>("OK","修改收费类型成功");
	}
	//删除收费类型
	@PostMapping(value="/delete")
	public ResultMessage<FeeTypeModel> delete(FeeTypeModel feeTypeModel) throws Exception {
		fts.delete(feeTypeModel);
		return new ResultMessage<FeeTypeModel>("OK","删除收费类型成功");
	}
	//取得所有收费类型列表
	@GetMapping(value="/list/all")
	public List<FeeTypeModel> getListByAll() throws Exception{
		return fts.getListByAll();
	}
	//取得所有收费类型列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<FeeTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<FeeTypeModel> result=new ResultMessage<FeeTypeModel>("OK","取得收费类型列表分页模式成功");
		result.setCount(fts.getCountByAll());
		result.setPageCount(fts.getPagaCountByAll(rows));
		result.setList(fts.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	//取得指定收费类型的信息
	@GetMapping("/get")
	public FeeTypeModel getByNo(int no) throws Exception{
		return fts.getByNo(no);
	}
	
}
