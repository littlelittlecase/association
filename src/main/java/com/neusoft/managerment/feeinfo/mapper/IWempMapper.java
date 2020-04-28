package com.neusoft.managerment.feeinfo.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.neusoft.managerment.feeinfo.model.WempModel;

/*
 * 维修工人的DAO层MyBatis Mapper接口
 * @Author: 张梓琪
 */
@Mapper
public interface IWempMapper {
	        //增添
			public void create(WempModel wemp) throws Exception;
			//修改v
			public void update(WempModel wemp) throws Exception;
			//删除
			public void delete(WempModel wemp) throws Exception;
			
}
