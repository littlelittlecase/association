package com.neusoft.managerment.feeinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.feeinfo.model.FeeTypeModel;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.service.IM_typeService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *  模块：feeinfo  
 *  维修类型控制层Controller
 * @Author:张梓琪
 */

@RestController
@RequestMapping(value="/mtype")
public class M_typeController {
	@Autowired
	IM_typeService typeservice = null;
	//增加
	@RequestMapping(value="add")
	public ResultMessage<M_typeModel> add(M_typeModel typemodel) throws Exception{
		typeservice.add(typemodel);
		return new ResultMessage<M_typeModel>("OK","增加维修类型成功");
		
	}
	

}
