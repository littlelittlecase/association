package com.neusoft.managerment.feeinfo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.Data;
/**
 *    模块：物业日常业务管理   表：维修工人表
 *    维修工人的Model类
 * @Author: 张梓琪
 */
@Alias("wemp")
@Data
public class WempModel implements Serializable {

	private String wempid = null;
	private String wempname = null;
	private String sex = null;
}
