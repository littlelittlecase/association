package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 *    模块：收费   表：收费项目  FeeItem
 *    收费项目的Model类
 * @Author: 吕淑兰
 */
@Alias("FeeItem")
@Data
public class FeeItemModel implements Serializable {
	private int no = 0;// 收费项目序号
	private String code = null; // 收费项目编码
	private String name = null; // 收费项目名称
	private String unit = null; // 收费单位(米,户，人，车)
	private FeeTypeModel feetype = null; // 收费类型
	//private String FeePayType = null; // 计费方式(年，月，日,次)
	private String cycle = null; // 是否周期性(Y/N)
	private String status = null; // 是否收费(Y/N)
	private String desc = null; // 收费项目说明

}
