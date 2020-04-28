package com.neusoft.managerment.baseinfo.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.baseinfo.service.IRoomService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息  表：房间 Room
 *    author : 陈思颖
 *    version: 1.0
 */
//房间的控制类
@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private IRoomService roomService=null;
	
	//增加房间
	@PostMapping("/add")
	public ResultMessage<RoomModel> add(RoomModel room) throws Exception {
		roomService.add(room);
		return new ResultMessage<RoomModel>("OK","增加房间成功");
	}
	
	//修改房间
	@PostMapping("/modify")
	public ResultMessage<RoomModel> modify(RoomModel room) throws Exception {
		roomService.modify(room);
		return new ResultMessage<RoomModel>("OK","修改房间成功");
	}
	
	//删除房间
	@PostMapping("/delete")
	public ResultMessage<RoomModel> delete(RoomModel room) throws Exception {
		roomService.delete(room);
		return new ResultMessage<RoomModel>("OK","删除房间成功");
	}

	//1 取得所有客户列表,无关联客户类型
	@GetMapping(value="/list/all")
	public List<RoomModel> getListByAll() throws Exception{
		return roomService.getListByAll();
	}
	
	//2 取得所有房间列表，有关联,分页模式
	@GetMapping(value="/list/all/page")
	public ResultMessage<RoomModel> getListByAllWithFKWithPage(@RequestParam(required = false,defaultValue ="10") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<RoomModel> result=new ResultMessage<RoomModel>("OK","取得房间列表分页模式成功");
		result.setCount(roomService.getCountByAll());
		result.setPageCount(roomService.getPageCountByAll(rows));
		result.setList(roomService.getListByAllWithFKWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//3 取得指定房间对象
	@GetMapping("/get")
	public RoomModel getByCustomerNo(int roomno) throws Exception{
		return roomService.getByRoomNo(roomno);
	}
	
	//4 取得房间的个数
	

	//5 根据综合检索条件取得房间列表
	@GetMapping(value="/list/condition/page")
	public ResultMessage<RoomModel> getListByConditionWithPage(
		@RequestParam(required = false,defaultValue ="0") int areano,
		@RequestParam(required = false,defaultValue ="") String buildingtypeno,
		@RequestParam(required = false,defaultValue ="0") int housetypeno,
		@RequestParam(required = false,defaultValue ="0") int buildingno,
		@RequestParam(required = false,defaultValue ="20") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) 
				throws Exception{
	ResultMessage<RoomModel> result=new ResultMessage<RoomModel>("OK","检索取得房间列表分页成功");
	result.setCount(roomService.getCountByCondition(areano, buildingtypeno, housetypeno, buildingno));
	result.setPageCount(roomService.getPageCountByConditionWithPage(areano, buildingtypeno, housetypeno, buildingno, rows));
	result.setList(roomService.getListByConditionWithPage(areano, buildingtypeno, housetypeno, buildingno, rows, page));
	result.setPage(page);
	result.setRows(rows);
	
	return result;
	}
	
	//6 根据综合检索条件取得客户个数 
	
	
	
}

