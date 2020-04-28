package com.neusoft.managerment.feeinfo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.managerment.feeinfo.model.M_typeModel;

/*
 * 维修报修的DAO层MyBatis Mapper接口
 * @Author: 张梓琪
 */
@Mapper
public interface IM_typeMapper {
	    //增添
		public void create(M_typeModel mtype) throws Exception;
		//修改v
		public void update(M_typeModel mtype) throws Exception;
		//删除
		public void delete(M_typeModel mtype) throws Exception;
		
}
