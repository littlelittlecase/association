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

import com.neusoft.managerment.baseinfo.model.BuildingTypeModel;
import com.neusoft.managerment.baseinfo.service.IBuildingTypeService;
import com.neusoft.managerment.message.ResultMessage;
/**
 *  模块：baseinfo 基础信息 
 *  建筑类型控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/buildType")
public class BuildingTypeController {
	@Autowired
	private IBuildingTypeService bts=null;
	
	//增加建筑类型
	@PostMapping(value="/add")
	public ResultMessage<BuildingTypeModel> add(BuildingTypeModel buildTypeModel) throws Exception {
		bts.add(buildTypeModel);
		return new ResultMessage<BuildingTypeModel>("OK","增加建筑类型成功");
	}
	//修改建筑类型
	@PostMapping(value="/modify")
	public ResultMessage<BuildingTypeModel> update(BuildingTypeModel buildTypeModel) throws Exception {
		bts.modify(buildTypeModel);
		return new ResultMessage<BuildingTypeModel>("OK","修改建筑类型成功");
	}
	//删除建筑类型
	@PostMapping(value="/delete")
	public  ResultMessage<BuildingTypeModel> delete(BuildingTypeModel buildTypeModel) throws Exception {
		bts.delete(buildTypeModel);
		return new ResultMessage<BuildingTypeModel>("OK","删除建筑类型成功");
	}
	//取得所有建筑类型列表
	@GetMapping(value="/list/all")
	public List<BuildingTypeModel> getBuildTypeListByAll() throws Exception{
		return bts.getBuildTypeListByAll();
	}
	//取得所有建筑类型列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<BuildingTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="10") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<BuildingTypeModel> result=new ResultMessage<BuildingTypeModel>("OK","取得建筑类型列表分页模式成功");
		result.setCount(bts.getCountByAll());
		result.setPageCount(bts.getPageCountByAll(rows));
		result.setList(bts.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	//取得指定建筑类型的信息
	@GetMapping("/get")
	public BuildingTypeModel getBuildTypeByNo(String no) throws Exception{
		return bts.getBuildTypeByNo(no);
	}
	
}
