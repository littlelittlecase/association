/**
 * 学生管理前端主管理JS
 * 模块：学生信息
 * 业务对象：学生管理
 * 
 */
$(function(){
	var studentID = null;
	var studentname = null;
	var sex = null;
	var Studentname = null;
	var majoyname = null;
	var studentphone = null;
	var associationname = null;
	
	
	//设置系统页面标题
	$("span#mainpagetille").html("学院管理");
	//设置日期的格式和选择
	
	//显示学生列表
	$("table#StudentTable").jqGrid({
		url: 'student/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '学号', name: 'studentid', width: 20 },
			{ label: '学生姓名', name: 'studentname', width: 50 },
			{ label: '性别', name: 'sex', width: 50 },
			{ label: '学院名称', name: 'academy.academyname', width: 50 },
			{ label: '专业', name: 'majoy.majoyname', width: 50 },
			{ label: '学生手机号码', name: 'studentphone', width: 50 },
			{ label: '社团名称', name: 'association.associationname', width: 50 },
		],
		caption:"学生列表",
		viewrecords: true, 
		autowidth: true,
		height: 400,
		rowNum: 5,
		rowList:[10,20,30],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "studentid"},
		pager: "#Studentpager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(studentid){
			studentID = studentid;
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("student/list/name",function(StudentList){
		if(StudentList){
			$.each(StudentList,function(index,nm){
				$("select#StudentSelection").append("<option value='"+nm.studentname+"'>"+nm.studentname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadStudentList()
	{
		
		$("table#StudentTable").jqGrid('setGridParam',{postData:{Studentname:Studentname,
																page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义学院名下拉框的更新事件的处理
	$("select#StudentSelection").off().on("change",function(){
		Studentname=$("select#StudentSelection").val();
		
		reloadStudentList();
	});
	
	

	
	
	//===========================增加学院处理================================================
	
	$("a#StudentAddLink").off().on("click",function(){
		$("div#StudentDailogArea").load("studentinfo/add.html",function(){
			//验证提交数据
			$("form#StudentAddForm").validate({
				rules: {
					studentid: {
						required: true
					},
					studentname: {
						required: true
					},
					sex: {
						required: true
					},
					academyname: {
						required: true
					},
					majoyname: {
						required: true
					},
					studentphone: {
						required: true
					},
					associationname: {
						required: true
					}
					
					
				},
				message:{
					studentid: {
						required: "学号不能为空"
					},
					studentname: {
						required: "学生姓名不能为空"
					},
					sex: {
						required: "性别不能为空"
					},
					academyname: {
						required: "学院不能为空"
					},
					majoyname: {
						required: "专业不能为空"
					},
					studentphone: {
						required: "手机号码不能为空"
					},
					associationname: {
						required: "社团不能为空"
					}
					
				}
			});
			
			//增加学院的弹窗
			$("div#StudentDailogArea").dialog({
				title:"增加学院",
				width:600
			});
			console.log("89");
			
			//拦截增加提交表单
			$("form#StudentAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadStudentList(); //更新学院列表
				}
				console.log(result.status);
				BootstrapDialog.show({
		            title: '学生操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#StudentDailogArea").dialog( "close" );
				$("div#StudentDailogArea").dialog( "destroy" );
				$("div#StudentDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#StudentDailogArea").dialog( "close" );
				$("div#StudentDailogArea").dialog( "destroy" );
				$("div#StudentDailogArea").html("");
			});
		});
	});
	
	//===============================修改学院信息处理=============================

	$("a#StudentModifyLink").off().on("click",function(){
		//若无选中新闻
		if(studentID==0){
			BootstrapDialog.show({
	            title: '学生信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#StudentDailogArea").load("studentinfo/modify.html",function(){
				//验证提交数据
				$("form#StudentModifyForm").validate({
					rules: {
						studentid: {
							required: true
						},
						studentname: {
							required: true
						},
						sex: {
							required: true
						},
						academyname: {
							required: true
						},
						majoyname: {
							required: true
						},
						studentphone: {
							required: true
						},
						associationname: {
							required: true
						}
						
						
					},
					message:{
						studentid: {
							required: "学号不能为空"
						},
						studentname: {
							required: "学生姓名不能为空"
						},
						sex: {
							required: "性别不能为空"
						},
						academyname: {
							required: "学院不能为空"
						},
						majoyname: {
							required: "专业不能为空"
						},
						studentphone: {
							required: "手机号码不能为空"
						},
						associationname: {
							required: "社团不能为空"
						}
						
					}
				});
				
				
				
				

				//取得指定的学生信息
				$.getJSON("student/get",{studentid:studentID},function(student){
					
					if(student){
						$("input[name='studentid']").val(student.model.studentid);
						$("input[name='studentname']").val(student.model.studentname);
						$("input[name='sex']").val(student.model.sex);
						$("input[name='academy.academyname']").val(student.model.academy.academyname);
						$("input[name='majoy.majoyname']").val(student.model.majoy.majoyname);
						$("input[name='studentphone']").val(student.model.studentphone);
						$("input[name='association.associationname']").val(student.model.association.associationname);
						
					}
				});
				
				//修改新闻的弹窗
				$("div#StudentDailogArea").dialog({
					title:"修改学生信息",
					width:600
				});
				
				//拦截修改提交表单
				$("form#StudentModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadStudentList(); //更新学生列表
					}
					
					BootstrapDialog.show({
			            title: '学院操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#StudentDailogArea").dialog( "close" );
					$("div#StudentDailogArea").dialog( "destroy" );
					$("div#StudentDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#StudentDailogArea").dialog( "close" );
					$("div#StudentDailogArea").dialog( "destroy" );
					$("div#StudentDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除学生处理=====================================

	$("a#StudentDeleteLink").off().on("click",function(){
		
		if(studentID==0){
			BootstrapDialog.show({
	            title: '学生操作信息',
	            message:"请选择要删除的学生学院信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确定需要删除该名学生吗?', function(result){
	            if(result) {
		            $.post("student/delete",{studentid:studentID},function(result){
		            	if(result.status=="OK"){
		            		reloadStudentList(); //更新学院列表
						}
						BootstrapDialog.show({
				            title: '学院操作信息',
				            message:result.message,
				            buttons: [{
				                label: '确定',
				                action: function(dialog) {
				                    dialog.close();
				                }
				            }]
				        });
		            });
	            }
			});
				
		}
	
	});

	
	
	
});