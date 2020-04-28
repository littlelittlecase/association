package com.neusoft.managerment.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.managerment.baseinfo.mapper.IRoomMapper;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.baseinfo.service.IRoomService;
/**
 *    模块：基础信息   表：房间 Room
 *    author : 陈思颖
 *    version: 1.0
 */

//房间的业务实现类
@Service("roomService")
@Transactional(rollbackFor=Exception.class)
public class RoomServiceImpl implements IRoomService {
	@Autowired
	private IRoomMapper roomMapper=null;
	
	@Override
	public void add(RoomModel room) throws Exception {
		roomMapper.create(room);

	}

	@Override
	public void modify(RoomModel room) throws Exception {
		roomMapper.update(room);

	}

	@Override
	public void delete(RoomModel room) throws Exception {
		roomMapper.delete(room);

	}

	@Override
	public List<RoomModel> getListByAll() throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectListByAll();
	}

	@Override
	public List<RoomModel> getListByAllWithFKWithPage(int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectListByAllWithFKWithPage(rows*(page-1), rows);
	}

	@Override
	public RoomModel getByRoomNo(int roomno) throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectByRoomNo(roomno);
	}

	@Override
	public int getCountByAll() throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectCountByAll();
	}

	@Override
	public List<RoomModel> getListByConditionWithPage(int areano, String buildingtypeno, int housetypeno, int buildingno,
			int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectListByConditionWithPage(areano, buildingtypeno, housetypeno, buildingno, rows*(page-1), rows);
	}

	@Override
	public int getCountByCondition(int areano, String buildingtypeno, int housetypeno, int buildingno) throws Exception {
		// TODO Auto-generated method stub
		return roomMapper.selectCountByCondition(areano, buildingtypeno, housetypeno, buildingno);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public int getPageCountByConditionWithPage(int areano, String buildingtypeno, int housetypeno, int buildingno,
			int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(areano, buildingtypeno, housetypeno, buildingno);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}





}
