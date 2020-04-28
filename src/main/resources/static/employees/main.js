//员工管理js
$(function(){
	var departmentNo=0;
	var idd=0;
	var sex="";
	var mobile="";
	var startJoinDate=null;
	var endJoinDate=null;
	var minSal=0;
	var maxSal=0;
	$("span#mainpagetille").html("员工信息管理");
	
	//取得员工列表，分页模式
	$("table#EmployeesGrid").jqGrid({
		url:host+'employees/list/condition/page',
		datatype:"json",
		colModel:[
			{label:'员工编号',name:'id',width:20},
			{label:'部门名称',name:'departments.deptname',width:30},
			{label:'员工姓名',name:'empname',width:40},
			{label:'入职日期',name:'joindate',width:40},
			{label:'性别',name:'sex',width:20},
			{label:'年龄',name:'age',width:20},
			{label:'手机号码',name:'mobile',width:40},
			{label:'微信',name:'wx',width:40},
			{label:'邮箱',name:'mail',width:40},
			{label:'薪资',name:'salary',width:20}
		],
		caption:"员工信息列表",
		viewrecords:true,
		autowidth:true,
		height:200,
		rowNum:5,
		rowList:[5,10,15],
		jsonReader:{
			root:"list",//返回格式
			page:"page",//页号
			total:"pageCount",//页数
			records:"count",//总个数
			id:"id"},//主属性名，model
		pager:"#EmployeesGridPager",
		multiselect:false,
		onSelectRow:function(empid){
			idd=empid;
		}
	});
	
	//取得部门列表下拉框
	$.getJSON(host+"departments/list/all",function(departmentsList){
		if(departmentsList){
			$.each(departmentsList,function(index,dm){
				$("select#DeptnoSelection").append("<option value='"+dm.deptno+"'>"+dm.deptname+"</option>");
			});
		}
	});
	//取得员工名称下拉框
	$.getJSON(host+"employees/list/all",function(empList){
		if(empList){
			$.each(empList,function(index,em){
				$("select#EmpSelection").append("<option value='"+em.id+"'>"+em.empname+"</option>");
			});
		}
	});
	
	//更新JQGrid的列表显示
	function reloadEmpList(){
		
		$("table#EmployeesGrid").jqGrid('setGridParam',{postData:{departmentNo:departmentNo,
			idd:idd,sex:sex,mobile:mobile,startJoinDate:startJoinDate,endJoinDate:endJoinDate,
			minSal:minSal,maxSal:maxSal}}).trigger("reloadGrid");
	}
	//部门下拉框的更新事件
	$("select#DeptnoSelection").off().on("change",function(){
		departmentNo=$("select#DeptnoSelection").val();
		reloadEmpList();
	});
	//员工下拉框的更新事件
	$("select#EmpSelection").off().on("change",function(){
		idd=$("select#EmpSelection").val();
		reloadEmpList();
	});
	//定义性别单选按钮更改事件
	$("input[name='sex']").off().on("change",function(){
		sex=$("input[name='sex']:checked").val();
		reloadEmpList();
	});
	//点击检索事件处理
	$("a#EmpSearchButton").on("click",function(){
		departmentNo=$("select#DeptnoSelection").val();
		idd=$("select#EmpSelection").val();
		sex=$("input[name='sex']:checked").val();
		mobile=$("input#mobile").val();
		startJoinDate=$("input#startJoinDate").val();
		endJoinDate=$("input#endJoinDate").val();
		minSal=$("input#minSal").val();
		maxSal=$("input#maxSal").val();
		if(startJoinDate==""){
			startJoinDate=null;
		}
		if(endJoinDate==""){
			endJoinDate=null;
		}
		reloadEmpList();
	});
	//===========================增加员工处理================================================
	$("a#EmployeesAddLink").off().on("click",function(){
		$("div#EmployeesDialog").load("employees/add.html",function(){
			//取得部门列表下拉框
			$.getJSON(host+"departments/list/all",function(departmentsList){
				if(departmentsList){
					$.each(departmentsList,function(index,dm){
						$("select#DeptnoSelection").append("<option value='"+dm.deptno+"'>"+dm.deptname+"</option>");
					});
				}
			});
			//验证员工提交数据
			$("form#EmployeesAddForm").validate({
				  rules: {
				    id:{
				    	required: true
				    },
				    empname:{
				    	required: true
				    },
				    joindate:{
				    	required: true
				    },
				    sex:{
				    	required: true
				    },
				    age:{
				    	required: true,
				    	number: true,
				    	range: [18, 60]
				    },
				    mobile:{
				    	required: true,
				    	mobile:true
				    },
				    wx:{
				    	required: true
				    },
				    mail:{
				    	required: true,
				    	email:true
				    },
				    salary:{
				    	required: true,
				    	number: true
				    },
				    	
				  },
				  messages:{
					  id:{
					    required:"员工编号为空"
					},
					empname:{
				    required:"员工姓名为空"
					},
					joindate:{
				    	required: "入职日期为空"
				    },
				    sex:{
				    	required: "未选择性别"
				    },
				    age:{
				    	required: "年龄为空",
				    	number: "年龄必须是数值",
				    	range:"年龄需要在18-60之间"
				    },
				    mobile:{
				    	required: "手机号码为空",
				    	mobile:"这不是一个手机号码"
				    },
				    wx:{
				    	required: "微信号为空"
				    },
				    mail:{
				    	required: "邮箱号为空",
				    	email:"这不是一个邮箱"
				    },
				    salary:{
				    	required: "薪资为空",
				    	number:"薪资必须是数字"
				    },
				 }
			});
			//拦截增加表单
			$("form#EmployeesAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					idd=0;
					reloadEmpList();//更新列表
				}
				alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '员工操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]    
		        });
				$("div#EmployeesDialog").dialog("close");
				$("div#EmployeesDialog").dialog("destroy")
				$("div#EmployeesDialog").html("");
				
			});
			$("div#EmployeesDialog").dialog({
				title:"员工增加",
				width:950
			});
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#EmployeesDialog").dialog("close");
				$("div#EmployeesDialog").dialog("destroy")
				$("div#EmployeesDialog").html("");
			});
			
		});	
	});
	//===============================修改处理===============================================================
	$("a#EmployeesModifyLink").off().on("click",function(){
		if(idd==0){
			BootstrapDialog.show({
	            title: '员工操作信息',
	            message:"请选择要修改的员工信息",
	            buttons: [{
	                label: '关闭',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]    
	        });
		}
		else{
			$("div#EmployeesDialog").load(host+"employees/modify.html",function(){
				//取得部门列表下拉框
				$.getJSON(host+"departments/list/all",function(departmentsList){
					if(departmentsList){
						$.each(departmentsList,function(index,dm){
							$("select#DeptnoSelection").append("<option value='"+dm.deptno+"'>"+dm.deptname+"</option>");
						});
					}
				});
				//取得选择的部门
				$.getJSON("employees/get",{id:idd},function(data){
					//alert(id);
					if(data.status=="OK"){
						$("input[name='id']").val(idd);
						//$('#testSelect option:selected') .val();//选中的值
						//$("input[name='departments.deptno']").val(data.model.departments.deptname);
						$('#DeptnoSelection option:selected').val(data.model.departments.deptname);
						$("input[name='empname']").val(data.model.empname);
						$("input[name='joindate']").val(data.model.joindate);
						$("input[name=sex][value="+data.model.sex+"]").attr("checked",true);
						$("input[name='age']").val(data.model.age);
						$("input[name='mobile']").val(data.model.mobile);
						$("input[name='wx']").val(data.model.wx);
						$("input[name='mail']").val(data.model.mail);
						$("input[name='salary']").val(data.model.salary);
						}
				});	
			//验证提交数据
				$("form#EmployeesAddForm").validate({
					  rules: {
					    id:{
					    	required: true
					    },
					    empname:{
					    	required: true
					    },
					    joindate:{
					    	required: true
					    },
					    sex:{
					    	required: true
					    },
					    age:{
					    	required: true,
					    	number: true,
					    	range: [18, 60]
					    },
					    mobile:{
					    	required: true,
					    	mobile:true
					    },
					    wx:{
					    	required: true
					    },
					    mail:{
					    	required: true,
					    	email:true
					    },
					    salary:{
					    	required: true,
					    	number: true
					    },
					    	
					  },
					  messages:{
						  id:{
						    required:"员工编号为空"
						},
						empname:{
					    required:"员工姓名为空"
						},
						joindate:{
					    	required: "入职日期为空"
					    },
					    sex:{
					    	required: "未选择性别"
					    },
					    age:{
					    	required: "年龄为空",
					    	number: "年龄必须是数值",
					    	range:"年龄需要在18-60之间"
					    },
					    mobile:{
					    	required: "手机号码为空",
					    	mobile:"这不是一个手机号码"
					    },
					    wx:{
					    	required: "微信号为空"
					    },
					    mail:{
					    	required: "邮箱号为空",
					    	email:"这不是一个邮箱"
					    },
					    salary:{
					    	required: "薪资为空",
					    	number:"薪资必须是数字"
					    },
					 }
				});
			//拦截修改提交表单
			$("form#EmployeesModifyForm").ajaxForm(function(result){
				if(result.status=="OK"){
					idd=0;
					reloadEmpList();//更新列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '员工操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]    
		        });
				$("div#EmployeesDialog" ).dialog( "close" );
				$("div#EmployeesDialog" ).dialog( "destroy" );
				$("div#EmployeesDialog").html("");
				
			});
			
			
			$("div#EmployeesDialog").dialog({
				title:"员工信息修改",
				width:950
			});
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#EmployeesDialog").dialog("close");
				$("div#EmployeesDialog").dialog("destroy")
				$("div#EmployeesDialog").html("");
			});
			
		});
	}
	});
	
	//===============================删除处理===============================================================
	$("a#EmployeesDelteLink").off().on("click",function(){
		if(idd==0){
			BootstrapDialog.show({
	            title: '员工操作信息',
	            message:"请选择要删除的员工信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]    
	        });
		}
		else{
			BootstrapDialog.confirm('确认删除此员工吗?',function(result){
				if(result){
					$.post(host+"employees/delete",{id:idd},function(result){
						if(result.status=="OK"){
							idd=0;
							reloadEmpList();//更新列表
						}
						
						BootstrapDialog.show({
				            title: '员工操作信息',
				            message:result.message,
				            buttons: [{
				                label: '关闭',
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
	//===============================查看处理===============================================================
	$("a#EmployeesViewLink").off().on("click",function(){
		if(idd==0){
			BootstrapDialog.show({
	            title: '员工操作信息',
	            message:"请选择要查看的员工信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]    
	        });
		}
		else{
			$("div#EmployeesDialog").load(host+"employees/view.html",function(){
				//取得指定的员工信息
				$.getJSON(host+"employees/get",{id:idd},function(em){
					if(em){
						$("span#id").html(idd);
						$("span#deptname").html(em.model.departments.deptname);
						$("span#empname").html(em.model.empname);
						$("span#joindate").html(em.model.joindate);
						$("span#sex").html(em.model.sex);
						$("span#age").html(em.model.age);
						$("span#mobile").html(em.model.mobile);
						$("span#wx").html(em.model.wx);
						$("span#mail").html(em.model.mail);
						$("span#salary").html(em.model.salary);
					}
				});
				$("div#EmployeesDialog").dialog({
					title:"员工信息显示",
					width:950
				});
				//点击取消按钮，管理弹出窗口
				$("input[value='取消']").off().on("click",function(){
					$("div#EmployeesDialog").dialog("close");
					$("div#EmployeesDialog").dialog("destroy")
					$("div#EmployeesDialog").html("");
				});
			});
			
		}
			
		});
});