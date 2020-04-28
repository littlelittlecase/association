package com.neusoft.managerment.feeinfo.service;

import com.neusoft.managerment.feeinfo.model.M_typeModel;
import com.neusoft.managerment.feeinfo.model.WempModel;

/**
 *   模块：物业日常业务管理   表：维修工人类型  Wemp
 *  维修工人业务层接口
 * @Author:张梓琪
 */
public interface IWempSerivce {

	 //增加
	public void add(WempModel wemp) throws Exception;
	//修改
	public void modify(WempModel wemp) throws Exception;
	//删除
	public void delete(WempModel wemp) throws Exception;
}
