package com.neusoft.managerment.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.neusoft.managerment.baseinfo.model.LivingTypeModel;

/**
 *    模块：基础信息   表：居住类型 CustomerType
 *    author : 陈思颖
 *    version: 1.0
 */
//居住类型的Mapper接口
@Mapper
public interface ILivingTypeMapper {
	//增加
	public void create(LivingTypeModel livingTypeModel) throws Exception;
	//修改
	public void update(LivingTypeModel livingTypeModel) throws Exception;
	//删除
	public void delete(LivingTypeModel livingTypeModel) throws Exception;
	//取得所有居住类型
	public List<LivingTypeModel> selectListByAll() throws Exception;
	//取得指定居住类型
	public LivingTypeModel selectListByTypeNo(int typeno) throws Exception;

}
