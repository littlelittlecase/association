package com.neusoft.managerment.feeinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.managerment.feeinfo.mapper.IM_typeMapper;
import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.service.IM_typeService;
/**
 *    模块：物业日常业务管理   表：维修类型  M_Type
 * 维修类型业务层接口的实现类
 * @Author: 张梓琪
 */
@Service
public class M_typeServiceImpl implements IM_typeService{

	@Autowired IM_typeMapper typemapper = null;
	@Override
	public void add(M_typeModel mtype) throws Exception {
		typemapper.create(mtype);
		
	}

	@Override
	public void modify(M_typeModel mtype) throws Exception {
		
		
	}

	@Override
	public void delete(M_typeModel mtype) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
