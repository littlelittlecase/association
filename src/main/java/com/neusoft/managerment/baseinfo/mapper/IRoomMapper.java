package com.neusoft.managerment.baseinfo.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.baseinfo.model.CustomerHomeModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;


/**
 *    模块：基础信息   表：房间 Room
 *    author : 陈思颖
 *    version: 1.0
 */
//房间的Mapper接口
@Mapper
public interface IRoomMapper {
	//增加
	public void create(RoomModel roomModel) throws Exception;
	//修改
	public void update(RoomModel roomModel) throws Exception;
	//删除
	public void delete(RoomModel roomModel) throws Exception;
	//1 取得房间的信息,无关联
	public List<RoomModel> selectListByAll() throws Exception;
	//2 取得所有房间列表,有关联,分页模式
	public List<RoomModel> selectListByAllWithFKWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;	
	//3 取得指定房间的信息
	public RoomModel selectByRoomNo(int roomno) throws Exception;
	//4 取得房间的个数
	public int selectCountByAll() throws Exception;
	//5 根据综合检索条件取得房间列表
	public List<RoomModel> selectListByConditionWithPage(
			@Param("areano") int areano, 
			@Param("buildingtypeno") String buildingtypeno, 
			@Param("housetypeno") int housetypeno, 
			@Param("buildingno") int buildingno, 
			@Param("start") int start,
			@Param("rows") int rows) throws Exception;
	//6 根据综合检索条件取得房间个数 
	public int selectCountByCondition(
			@Param("areano") int areano, 
			@Param("buildingtypeno") String buildingtypeno, 
			@Param("housetypeno") int housetypeno, 
			@Param("buildingno") int buildingno
			) throws Exception;
	//7  取得房间的页数
	public int selectPageCountByAll(int rows);
	//8  根据综合检索条件取得房间页数 
	public int selectPageCountByConditionWithPage(int areano, String buildingtypeno, int housetypeno, int buildingno, int rows) throws Exception;

	
}
