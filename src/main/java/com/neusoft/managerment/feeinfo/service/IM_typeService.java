package com.neusoft.managerment.feeinfo.service;

import com.neusoft.managerment.feeinfo.model.M_typeModel;

/**
 *   模块：物业日常业务管理   表：维修类型  M_Type
 *  维修类型业务层接口
 * @Author:张梓琪
 */
public interface IM_typeService {
	    //增加
		public void add(M_typeModel mtype) throws Exception;
		//修改
		public void modify(M_typeModel mtype) throws Exception;
		//删除
		public void delete(M_typeModel mtype) throws Exception;
		
}
