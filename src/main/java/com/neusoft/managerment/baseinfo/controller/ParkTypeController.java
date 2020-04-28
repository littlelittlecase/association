package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.ParkTypeModel;
import com.neusoft.managerment.baseinfo.service.IParkTypeService;
import com.neusoft.managerment.message.ResultMessage;
/**
 *  模块：baseinfo 基础信息 
 *  车位类型控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/parkType")
public class ParkTypeController {
	@Autowired
	private IParkTypeService pts=null;
	
	//增加车位类型
	@PostMapping(value="/add")
	public ResultMessage<ParkTypeModel> add(ParkTypeModel parkTypeModel) throws Exception {
		pts.add(parkTypeModel);
		return new ResultMessage<ParkTypeModel>("OK","增加车位类型成功");
	}
	//修改车位类型
	@PostMapping(value="/modify")
	public ResultMessage<ParkTypeModel> update(ParkTypeModel parkTypeModel) throws Exception {
		pts.modify(parkTypeModel);
		return new ResultMessage<ParkTypeModel>("OK","修改车位类型成功");
	}
	//删除车位类型
	@PostMapping(value="/delete")
	public  ResultMessage<ParkTypeModel> delete(ParkTypeModel parkTypeModel) throws Exception {
		pts.delete(parkTypeModel);
		return new ResultMessage<ParkTypeModel>("OK","删除车位类型成功");
	}
	//取得所有车位类型列表
	@GetMapping(value="/list/all")
	public List<ParkTypeModel> getListByAll() throws Exception{
		return pts.getListByAll();
	}
	//取得所有车位类型列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<ParkTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="2") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<ParkTypeModel> result=new ResultMessage<ParkTypeModel>("OK","取得车位类型列表分页模式成功");
		result.setCount(pts.getCountByAll());
		result.setPageCount(pts.getPageCountByAll(rows));
		result.setList(pts.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	//取得指定车位类型的信息
	@GetMapping("/get")
	public ParkTypeModel getByNo(int no) throws Exception{
		return pts.getByNo(no);
	}
	
}
