package com.neusoft.managerment.baseinfo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.AreaModel;
import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.service.IBuildingService;
import com.neusoft.managerment.baseinfo.service.IBuildingTypeService;
import com.neusoft.managerment.message.ResultMessage;
/**
 *   模块：baseinfo 基础信息
 *   楼宇控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/building")
public class BuildingController {
	@Autowired
	private IBuildingService bs=null;
	
	
	//增加楼宇
	@PostMapping(value="/add")
	public ResultMessage<BuildingModel> add(BuildingModel buildModel) throws Exception {
		bs.add(buildModel);
		return new ResultMessage<BuildingModel>("OK","增加楼宇成功");
	}
	//修改楼宇
	@PostMapping(value="/modify")
	public ResultMessage<BuildingModel> update(BuildingModel buildModel) throws Exception {
		bs.modify(buildModel);
		return new ResultMessage<BuildingModel>("OK","修改楼宇成功");
	}
	//删除楼宇
	@PostMapping(value="/delete")
	public ResultMessage<BuildingModel> delete(BuildingModel buildModel) throws Exception {
		bs.delete(buildModel);
		return new ResultMessage<BuildingModel>("OK","删除楼宇成功");
	}
	
	//取得所有楼宇列表
	@GetMapping(value="/list/all")
	public List<BuildingModel> getListByAll() throws Exception{
		return bs.getListByAll();
	}
	//取得所有楼宇列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<BuildingModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="2") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<BuildingModel> result=new ResultMessage<BuildingModel>("OK","取得楼宇列表分页模式成功");
		result.setCount(bs.getCountByAll());
		result.setPageCount(bs.getPageCountByAll(rows));
		result.setList(bs.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	//查询楼宇的信息,关联Area
	@GetMapping(value="/list/all/witharea")
	public List<BuildingModel> getListByAllWithArea() throws Exception {
		return bs.getListByAllWithArea();
	}
	
	//查询楼宇的信息,关联BuildingType
	@GetMapping(value="/list/all/withbuildtype")
	public List<BuildingModel> getListByAllWithBuildType() throws Exception{
		return bs.getListByAllWithBuildType();
	}
	
	//查询楼宇的信息,关联Area和BuildingType
	@GetMapping(value="/list/all/withareaandbuildtype")
	public List<BuildingModel> getListByAllWithAreaAndBuildType() throws Exception {
		return bs.getListByAllWithAreaAndBuildType();
	}
	
	//取得所有楼宇列表,关联Area和BuildingType.分页模式
	@GetMapping(value="/list/all/withareaandbuildtypewithpage")
	public ResultMessage<BuildingModel> getListByAllWithAreaAndBuildTypeWithPage(@RequestParam(required = false,defaultValue ="5") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<BuildingModel> result=new ResultMessage<BuildingModel>("OK","取得楼宇列表分页模式成功");
		result.setCount(bs.getCountByAll());
		result.setPageCount(bs.getPageCountByAll(rows));
		result.setList(bs.getListByAllWithAreaAndBuildTypeWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得指定小区的楼宇列表,参数:areaNo 
	@GetMapping(value="/list/area")
	public List<BuildingModel> getListByArea(int areaNo) throws Exception {
		return bs.getListByArea(areaNo);
	}
		
	//取得指定楼宇的信息
	@GetMapping("/get")
	public BuildingModel getByNo(String no) throws Exception{
		return bs.getByNo(no);
	}

	//按检索条件取得楼宇列表
	@GetMapping(value="/list/condition/page")
	public ResultMessage<BuildingModel> getListByConditionWithAreaAndBuildTypeWithPage(
			@RequestParam(required = false,defaultValue ="0") int areaNo,
			@RequestParam(required = false,defaultValue ="") String buildingtypeNo,
			@RequestParam(required = false,defaultValue ="") String code,
			@RequestParam(required = false,defaultValue ="") String direction,
			@RequestParam(required = false,defaultValue ="0") int minhome,
			@RequestParam(required = false,defaultValue ="0") int maxhome,
			@RequestParam(required = false,defaultValue ="0") int minhouse,
			@RequestParam(required = false,defaultValue ="0") int maxhouse, 
			@RequestParam(required = false,defaultValue ="10") int rows, 
			@RequestParam(required = false,defaultValue ="1") int page) throws Exception {
	
		ResultMessage<BuildingModel> result=new ResultMessage<BuildingModel>("OK","取得员工列表分页成功");
		result.setCount(bs.getCountByCondition(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse));
		result.setPageCount(bs.getPageByConditionWithPage(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse, rows));
		result.setList(bs.getListByConditionWithAreaAndBuildTypeWithPage(areaNo, buildingtypeNo, code, direction, minhome, maxhome, minhouse, maxhouse, rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
}

