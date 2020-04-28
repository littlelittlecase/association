package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.managerment.baseinfo.model.LivingTypeModel;
import com.neusoft.managerment.baseinfo.service.ILivingTypeService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息  表：居住类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
//居住类型的控制类
@RestController
@RequestMapping("/livingType")
public class LivingTypeController {
	@Autowired
	private ILivingTypeService livingTypeService=null;
	
	//增加居住类型
	@PostMapping("/add")
	public ResultMessage<LivingTypeModel> add(LivingTypeModel livingtype) throws Exception {
		livingTypeService.add(livingtype);
		return new ResultMessage<LivingTypeModel>("OK","增加居住类型成功");
	}
	//修改居住类型
	@PostMapping("/modify")
	public ResultMessage<LivingTypeModel> modify(LivingTypeModel livingtype) throws Exception {
		livingTypeService.modify(livingtype);
		return new ResultMessage<LivingTypeModel>("OK","修改居住类型成功");
	}
	//删除居住类型
	@PostMapping("/delete")
	public ResultMessage<LivingTypeModel> delete(LivingTypeModel livingtype) throws Exception {
		livingTypeService.delete(livingtype);
		return new ResultMessage<LivingTypeModel>("OK","删除居住类型成功");
	}
	//取得指定的居住类型
	@GetMapping("/get")
	public LivingTypeModel getListByTypeNo(int typeno) throws Exception{
		return livingTypeService.getListByTypeNo(typeno);
	}
	
	//取得所有居住类型列表，无分页
	@GetMapping(value="/list/all")
	public List<LivingTypeModel> getListByAll() throws Exception{
		return livingTypeService.getListByAll();
	}
	
}
