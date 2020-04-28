package com.neusoft.managerment.feeinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;
import com.neusoft.managerment.baseinfo.service.IMaintainproviderService;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.model.MaintainRecordModel;
import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.feeinfo.service.IMaintainRecordService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *  模块：物业日常业务管理  
 *  维修保单控制层Controller
 * @Author:张梓琪
 */

@RestController
@RequestMapping(value="/maintainrecord")
public class MaintainRecordController {

	@Autowired
	public IMaintainRecordService imrr = null;
	//查询所有维修单
	@RequestMapping(value="/tolist")
	public List<MaintainRecordModel> getlist(MaintainRecordModel maintainmodel)throws Exception{
		return imrr.getListByALL();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<MaintainRecordModel> add(MaintainRecordModel maintainmodel)throws Exception{
		imrr.add(maintainmodel);
		return new ResultMessage<MaintainRecordModel>("ok","增加成功");
		
		
	}
	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<MaintainRecordModel> modify(MaintainRecordModel maintainmodel)throws Exception{
		imrr.modify(maintainmodel);
		return new ResultMessage<MaintainRecordModel>("ok","修改成功");
		}
	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<MaintainRecordModel> delete(MaintainRecordModel maintainmodel)throws Exception{
		imrr.delete(maintainmodel);
		return new ResultMessage<MaintainRecordModel>("ok","删除成功");

		}
	//查询指定维修保单
	@RequestMapping(value="/get")
	public ResultMessage<MaintainRecordModel> get(int recordno)throws Exception{
		imrr.getByNo(recordno);
		ResultMessage<MaintainRecordModel> result = new ResultMessage<MaintainRecordModel>("ok","查询成功");
		result.setModel(imrr.getByNo(recordno));
		return result;

		}
	//查询维修类型
	@RequestMapping(value="/list/type")
	public List<M_typeModel> gettype()throws Exception{
		return imrr.gettype();
	}
	//查询维修工人
	@RequestMapping(value="/list/wemp")
	public List<WempModel> getwemp()throws Exception{
		return imrr.getwemp();
		
	}
	//查询房间号
	@RequestMapping(value="/list/room")
	public List<RoomModel> getroom()throws Exception{
		return imrr.getroom();
		
	}
	//查询维修公司
	@RequestMapping(value="/list/provider")
	public List<MaintainProviderModel>getprovider()throws Exception{
		return imrr.getprovider();
	}
	//查询所有维修保单 分页
	@RequestMapping(value="/list/all/page")
	public ResultMessage<MaintainRecordModel> list(
			@RequestParam(required = false,defaultValue ="4") int rows,
			@RequestParam(required = false,defaultValue = "1") int page)throws Exception{
		ResultMessage<MaintainRecordModel> result = new ResultMessage<MaintainRecordModel>("ok","分页查询成功");
		result.setList(imrr.getListByAllWithPage(rows, page));
		result.setCount(imrr.getcountbyall());
		result.setPageCount(imrr.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		return result;		

		}
	
	//按条件查询维修保单 分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<MaintainRecordModel> getListByConditionWithPage(
			@RequestParam(required = false,defaultValue ="0") int mtypeno,//外键维修类型
			@RequestParam(required = false,defaultValue ="0") int roomno,//外键房间序号
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date startDate,//报修时间
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate, 
            @RequestParam(required = false,defaultValue="") String wstatus, //维修状态
            @RequestParam(required = false,defaultValue ="0") int providerno,//外键维修公司序号
			@RequestParam(required = false,defaultValue ="4") int rows,
			@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		ResultMessage<MaintainRecordModel> result = new ResultMessage<MaintainRecordModel>("ok","分页查询成功");
		result.setCount(imrr.getCountByCondition(mtypeno, roomno, startDate, endDate, wstatus, providerno));
		result.setList(imrr.getListByConditionWithPage(mtypeno, roomno, startDate, endDate, wstatus, providerno, rows, page));
		result.setPageCount(imrr.getPageByConditionWithPage(mtypeno, roomno, startDate, endDate, wstatus, providerno, rows));
		result.setPage(page);
		result.setRows(rows);
		return result;
		
		
	}
	
		
}
