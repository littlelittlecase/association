package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    物业日常业务管理模块的维修报表Model 
 */
@Alias("MaintainRecord")
@Data
public class MaintainRecordModel implements Serializable {
	private int recordno = 0;
	//外键
	private M_typeModel mtype = null;
	//外键
	private RoomModel room = null;
	private String contactname = null;
	private String mobile = null;
	private String tel = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mdate = null;
	private String mdesc = null;
	//外键
	private WempModel wemp = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date wdate = null;
	private String wtask = null;
	private String wresult = null;
	private String wstatus = null;
	private int wfee = 0;
	//外键
	private MaintainProviderModel provider = null;
	private String clientfeeback = null;

}
