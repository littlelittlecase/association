package com.neusoft.managerment.baseinfo.model;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import lombok.Data;

/**
 *    模块：基础信息   表：户型HouseType
 *    author : 陈思颖
 *    version: 1.0
 */

@Alias("housetype")
@Data
public class HouseTypeModel implements Serializable {
	private int typeno = 0;
	private String typename = null;
	
}
