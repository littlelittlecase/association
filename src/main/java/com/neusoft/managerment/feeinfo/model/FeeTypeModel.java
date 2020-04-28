package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    模块：收费   表：收费类型  FeeType
 *    收费类型的Model类
 * @Author: 吕淑兰
 */
@Alias("FeeType")
@Data
public class FeeTypeModel implements Serializable {

	private int no = 0; // 类型编号
	private String name = null; // 类型名称
}
