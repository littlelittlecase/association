package com.neusoft.managerment.feeinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.FeeTypeModel;

/**
 *   模块：收费   表：收费类型  FeeType
 *  收费类型业务层接口
 * @Author:吕淑兰
 */
public interface IFeeTypeService {
	//增加收费类型
	public void add(FeeTypeModel feeTypeModel) throws Exception;
	//修改收费类型
	public void modify(FeeTypeModel feeTypeModel) throws Exception;
	//删除收费类型
	public void delete(FeeTypeModel feeTypeModel) throws Exception;
	//取得所有收费类型列表
	public List<FeeTypeModel> getListByAll() throws Exception;
	//取得指定收费类型的信息
	public FeeTypeModel getByNo(int no) throws Exception;
	//取得所有收费类型列表,分页模式
	public List<FeeTypeModel> getListByAllWithPage(int rows, int page) throws Exception;
	//取得收费类型的个数
	public int getCountByAll() throws Exception;
	//取得收费类型页数
	public int getPagaCountByAll(int rows) throws Exception;
}