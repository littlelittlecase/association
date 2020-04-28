package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;
import com.neusoft.managerment.baseinfo.model.RoomModel;

import lombok.Data;
/**
 *    author : 张梓琪
 *    version: 1.0
 *    物业日常业务管理模块的维修Model 
 */
@Alias("mtype")
@Data
public class M_typeModel implements Serializable{

	//维修类型
	private int mtypeno = 0;
	private String mtypename = null;
	
}
