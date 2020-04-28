package com.neusoft.managerment.office.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *    author : 张梓琪
 *    version: 1.0
 *    系统基础信息的新闻的Model
 */
@Alias("NewsModel")
@Data
public class NewsModel implements Serializable {
	private int newsno = 0;
	private String newstype = null;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date newstime = null;
	private String newscontent = null;
	

}
