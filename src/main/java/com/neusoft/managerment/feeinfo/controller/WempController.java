package com.neusoft.managerment.feeinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.feeinfo.service.IWempSerivce;
import com.neusoft.managerment.message.ResultMessage;

/**
 *  模块：物业日常业务管理  
 *  维修工人控制层Controller
 * @Author:张梓琪
 */

@RestController
@RequestMapping(value="/wemp")
public class WempController {
	@Autowired
	public IWempSerivce iwemp = null;
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<WempModel> add(WempModel wempmodel)throws Exception{
		iwemp.add(wempmodel);
		return new ResultMessage<WempModel>("OK","增加工人成功");
		
		
	}
	//删除
		@RequestMapping(value="/delete")
		public ResultMessage<WempModel> delete(WempModel wempmodel)throws Exception{
			iwemp.delete(wempmodel);
			return new ResultMessage<WempModel>("OK","删除工人成功");
		}
		
		//删除
		@RequestMapping(value="/modify")
		public ResultMessage<WempModel> modify(WempModel wempmodel)throws Exception{
			iwemp.modify(wempmodel);
			return new ResultMessage<WempModel>("OK","修改工人成功");
	
		}


}
