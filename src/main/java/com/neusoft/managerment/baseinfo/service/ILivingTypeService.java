package com.neusoft.managerment.baseinfo.service;
import java.util.List;

import com.neusoft.managerment.baseinfo.model.CustomerTypeModel;
import com.neusoft.managerment.baseinfo.model.LivingTypeModel;

/**
 *    模块：基础信息   表：居住 类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
public interface ILivingTypeService {
	//增加
	public void add(LivingTypeModel livingtype) throws Exception;
	//修改
	public void modify(LivingTypeModel livingtype) throws Exception;
	//删除
	public void delete(LivingTypeModel livingtype) throws Exception;
	//取得所有居住类型列表
	public LivingTypeModel getListByTypeNo(int typeno) throws Exception;
	//取得指定居住类型列表
	public List<LivingTypeModel> getListByAll() throws Exception;

}
