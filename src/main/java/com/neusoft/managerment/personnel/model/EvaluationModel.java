package com.neusoft.managerment.personnel.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Alias("Evaluation")
@Data
public class EvaluationModel implements Serializable {
	private int no=0;
	private EmployeesModel employees=null;
	private String grade = null;
	private String award = null;
}
