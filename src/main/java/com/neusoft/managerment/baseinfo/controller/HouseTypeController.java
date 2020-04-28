package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.HouseTypeModel;
import com.neusoft.managerment.baseinfo.service.IHouseTypeService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息   表：户型  HouseType
 *    author : 陈思颖
 *    version: 1.0
 */
//户型的控制类
@RestController
@RequestMapping("/housetype")
public class HouseTypeController {
	@Autowired
	private IHouseTypeService houseTypeService=null;
	
	//增加户型
	@PostMapping("/add")
	public ResultMessage<HouseTypeModel> add(HouseTypeModel housetype) throws Exception {
		houseTypeService.add(housetype);
		return new ResultMessage<HouseTypeModel>("OK","增加客户成功");
	}
	//修改户型
	@PostMapping("/modify")
	public ResultMessage<HouseTypeModel> modify(HouseTypeModel housetype) throws Exception {
		houseTypeService.modify(housetype);
		return new ResultMessage<HouseTypeModel>("OK","修改客户成功");
	}
	//删除户型
	@PostMapping("/delete")
	public ResultMessage<HouseTypeModel> delete(HouseTypeModel housetype) throws Exception {
		houseTypeService.delete(housetype);
		return new ResultMessage<HouseTypeModel>("OK","删除客户成功");
	}
	//取得指定的户型
	@GetMapping("/get")
	public HouseTypeModel getByTypeNo(int typeno) throws Exception{
		return houseTypeService.getByTypeNo(typeno);
	}
	//取得所有户型列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<HouseTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="10") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<HouseTypeModel> result=new ResultMessage<HouseTypeModel>("OK","取得客户列表分页模式成功");
		result.setList(houseTypeService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得所有户型列表，无分页
	@GetMapping(value="/list/all")
	public List<HouseTypeModel> getListByAll() throws Exception{
		return houseTypeService.getListByAll();
	}
	

}
