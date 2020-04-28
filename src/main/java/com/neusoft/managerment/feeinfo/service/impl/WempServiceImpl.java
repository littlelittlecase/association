package com.neusoft.managerment.feeinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.managerment.feeinfo.mapper.IWempMapper;
import com.neusoft.managerment.feeinfo.model.WempModel;
import com.neusoft.managerment.feeinfo.service.IWempSerivce;
/**
 *    模块：物业日常业务管理   表：维修工人 Wemp
 * 维修工人业务层接口的实现类
 * @Author: 张梓琪
 */
@Service
public class WempServiceImpl implements IWempSerivce {
    @Autowired
	public IWempMapper wempmapper = null;
    //增添
	@Override
	public void add(WempModel wemp) throws Exception {
		wempmapper.create(wemp);
		
	}

	//修改
	@Override
	public void modify(WempModel wemp) throws Exception {
		wempmapper.update(wemp);
		
	}

	//删除
	@Override
	public void delete(WempModel wemp) throws Exception {
		wempmapper.delete(wemp);
		
	}

	
}
