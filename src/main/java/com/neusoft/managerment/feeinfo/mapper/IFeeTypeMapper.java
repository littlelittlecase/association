package com.neusoft.managerment.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.managerment.feeinfo.model.FeeTypeModel;


/**
 *  模块：基础信息   表：收费类型  FeeType
 * 收费类型的DAO层MyBatis Mapper接口
 * @Author: 吕淑兰
 */

@Mapper
public interface IFeeTypeMapper {
	//增加收费类型
	public void create(FeeTypeModel feeTypeModel) throws Exception;
	//修改收费类型
	public void update(FeeTypeModel feeTypeModel) throws Exception;
	//删除收费类型
	public void delete(FeeTypeModel feeTypeModel) throws Exception;
	//取得所有收费类型列表
	public List<FeeTypeModel> selectListByAll() throws Exception;
	//取得指定收费类型的信息
	public FeeTypeModel selectByNo(int no) throws Exception;
	//取得所有收费类型列表,分页模式
	public List<FeeTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//取得收费类型的个数
	public int selectCountByAll() throws Exception;
}
