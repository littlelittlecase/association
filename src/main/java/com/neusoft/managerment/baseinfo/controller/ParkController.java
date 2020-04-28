package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.BuildingModel;
import com.neusoft.managerment.baseinfo.model.ParkModel;
import com.neusoft.managerment.baseinfo.service.IParkService;
import com.neusoft.managerment.message.ResultMessage;
/**
 *   模块：baseinfo 基础信息
 *   车位控制层Controller
 * @Author: 吕淑兰
 */

@RestController
@RequestMapping(value="/park")
public class ParkController {
	@Autowired
	private IParkService ps=null;
	
	//增加车位
	@PostMapping(value="/add")
	public ResultMessage<ParkModel> add(ParkModel parkModel) throws Exception {
		ps.add(parkModel);
		return new ResultMessage<ParkModel>("OK","增加车位成功");
	}
	//修改车位
	@PostMapping(value="/modify")
	public ResultMessage<ParkModel> update(ParkModel parkModel) throws Exception {
		ps.modify(parkModel);
		return new ResultMessage<ParkModel>("OK","修改车位成功");
	}
	//删除车位
	@PostMapping(value="/delete")
	public ResultMessage<ParkModel> delete(ParkModel parkModel) throws Exception {
		ps.delete(parkModel);
		return new ResultMessage<ParkModel>("OK","删除车位成功");
	}
	
	//取得所有车位列表
	@GetMapping(value="/list/all")
	public List<ParkModel> getListByAll() throws Exception{
		return ps.getListByAll();
	}
	//取得所有车位列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<ParkModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="2") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<ParkModel> result=new ResultMessage<ParkModel>("OK","取得车位列表分页模式成功");
		result.setCount(ps.getCountByAll());
		result.setPageCount(ps.getPagaCountByAll(rows));
		result.setList(ps.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	//取得所有车位列表,关联ParkType
	@GetMapping(value="/list/all/withParkType")
	public List<ParkModel> getListByAllWithParkType() throws Exception {
		return ps.getListByAllWithParkType();
	}
	
	//取得所有车位列表,关联Building
	@GetMapping(value="/list/all/withBuilding")
	public List<ParkModel> getListByAllWithBuilding() throws Exception{
		return ps.getListByAllWithBuilding();
	}
	
	//取得所有车位列表,关联ParkType和Building
	@GetMapping(value="/list/all/withParkTypeAndBuilding")
	public List<ParkModel> getListByAllWithParkTypeAndBuilding() throws Exception {
		return ps.getListByAllWithParkTypeAndBuilding();
	}
	
	//取得指定车位类型的车位列表,参数:parkTypeNo 
	@GetMapping(value="/list/parkType")
	public List<ParkModel> getBuildListByParkType(int parkTypeNo) throws Exception {
		return ps.getListByParkType(parkTypeNo);
	}
	//取得指定楼宇的车位列表,参数:buildingNo 
	@GetMapping(value="/list/building")
	public List<ParkModel> getListByBuilding(int buildingNo) throws Exception {
		return ps.getListByBuilding(buildingNo);
	}
		
	//取得指定车位的信息
	@GetMapping("/get")
	public ParkModel getBuildByNo1(int no) throws Exception{
		return ps.getByNo(no);
	}

	//根据综合检索条件取得车位列表,关联ParkType和Building,分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<ParkModel> selectListByConditionWithParkTypeAndBuildingWithPage(
			@RequestParam(required = false,defaultValue = "0") int parkTypeNo,
			@RequestParam(required = false,defaultValue = "0") int buildingNo,
			@RequestParam(required = false,defaultValue = "") String parkstatus,
			@RequestParam(required = false,defaultValue = "") String rentunit,
			@RequestParam(required = false,defaultValue = "") String feestatus,
			@RequestParam(required = false,defaultValue = "") String mixarea,
			@RequestParam(required = false,defaultValue = "") String maxarea,
			@RequestParam(required = false,defaultValue = "") String minrentprice,
			@RequestParam(required = false,defaultValue = "") String maxrentprice,
			@RequestParam(required = false,defaultValue = "10") int rows,
			@RequestParam(required = false,defaultValue = "1") int page) throws Exception {
		
		ResultMessage<ParkModel> result=new ResultMessage<ParkModel>("OK","取得员工列表分页成功");
		result.setCount(ps.getCountByCondition(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice));
		result.setPageCount(ps.getPageCountByCondition(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice, rows));
		result.setList(ps.getListByConditionWithParkTypeAndBuildingWithPage(parkTypeNo, buildingNo, parkstatus, rentunit, feestatus, mixarea, maxarea, minrentprice, maxrentprice, rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
}

