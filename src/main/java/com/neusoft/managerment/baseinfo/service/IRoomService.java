package com.neusoft.managerment.baseinfo.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.CustomerHomeModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;
/**
 *    模块：基础信息   表：房间 Room
 *    author : 陈思颖
 *    version: 1.0
 */
public interface IRoomService {
	//增加
	public void add(RoomModel room) throws Exception;
	//修改
	public void modify(RoomModel room) throws Exception;
	//删除
	public void delete(RoomModel room) throws Exception;
	//1 取得所有房间列表,无关联
	public List<RoomModel> getListByAll() throws Exception;
	//2 取得所有房间列表,有关联,分页模式
	public List<RoomModel> getListByAllWithFKWithPage(int rows,int page) throws Exception;
	//3 取得指定房间对象
	public RoomModel getByRoomNo(int roomno) throws Exception;
	//4 取得房间的个数
	public int getCountByAll() throws Exception;
	//5 根据综合检索条件取得房间列表
	public List<RoomModel> getListByConditionWithPage(int areano, String buildingtypeno, int housetypeno, int buildingno, int rows,int page) throws Exception;
	//6 根据综合检索条件取得房间个数 
	public int getCountByCondition(int areano, String buildingtypeno, int housetypeno, int buildingno) throws Exception;
	//7 取得房间的页数
	public int getPageCountByAll(int rows) throws Exception;
	//8  根据综合检索条件取得房间页数 
	public int getPageCountByConditionWithPage(int areano, String buildingtypeno, int housetypeno, int buildingno, int rows) throws Exception;


}
