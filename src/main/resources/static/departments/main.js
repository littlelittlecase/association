/*
 * 部门管理js
 * 
 */

$(function(){
	var departmentNo=0; //选择的部门编号
	var departmentName="";
	//设置系统页面标题
	$("span#mainpagetille").html("部门管理");
	//取得部门的列表，分页模式
	$("table#DepartmentsGrid").jqGrid({
		url:host+'departments/list/condition/page',
		datatype:"json",
		colModel:[
			{label:'部门编号',name:'deptno',width:10},
			{label:'部门名称',name:'deptname',width:20},
			{label:'部门职责',name:'ddesc',width:100}
		],
		caption:"部门列表",
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
			id:"deptno"},//主属性名，model
		pager:"#DepartmentsGridPager",
		multiselect:false,
		onSelectRow:function(deptno){
			departmentNo=deptno;
		}

	});
			
	
	//取得部门列表，填充部门下拉框
	$.getJSON(host+"departments/list/all",function(departmentsList){
		if(departmentsList){
			$.each(departmentsList,function(index,dm){
				$("select#DeptnoSelection").append("<option value='"+dm.deptno+"'>"+dm.deptno+"</option>");
			});
		}
	});
	
	//取得下拉框
	$.getJSON(host+"departments/list/all",function(departmentsList){
		if(departmentsList){
			$.each(departmentsList,function(index,rm){
				$("select#DeptnameSelection").append("<option value='"+rm.deptname+"'>"+rm.deptname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadDepList()
	{
		$("table#DepartmentsGrid").jqGrid('setGridParam',{
			postData:{departmentNo:departmentNo,
			departmentName:departmentName}}).trigger("reloadGrid");
	}
	
	//定义部门下拉框的更新事件的处理
	$("select#DeptnoSelection").off().on("change",function(){
		departmentNo=$("select#DeptnoSelection").val();
		reloadDepList();
	});
	
	//定义部门下拉框的更新事件的处理
	$("select#DeptnameSelection").off().on("change",function(){
		departmentName=$("select#DeptnameSelection").val();
		reloadDepList();
	});
	
	//点击检索事件处理
	$("a#DeptSearchButton").on("click",function(){
		departmentNo=$("select#DeptnoSelection").val();
		departmentName=$("select#DeptnameSelection").val();
		reloadDepList();
	});
	

	
//===========================增加处理================================================
	$("a#DepartmentsAddLink").off().on("click",function(){
		$("div#DepartmentDialogArea").load(host+"departments/add.html",function(){
			//$("div#DepartmentDialogArea").css({zIndex:9999});
			//取得部门列表，并填充部门下拉框
			
			//取得角色列表，生成角色选择下拉框
			
			//验证提交数据
			$("form#DepartmentsAddForm").validate({
				  rules: {
				    deptname:{
				    	required: true
				    },
				    ddesc:{
				    	required: true
				    }
				    	
				  },
				  messages:{
					deptname:{
					    	required:"部门名称为空"
					},
					ddesc:{
				    	required:"部门职责为空"
				}
				 }
			});
			//拦截增加提交表单
			$("form#DepartmentsAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadDepList();//更新列表
				}
				alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '部门操作信息',
		            message:result.message
		        });
				$("div#DepartmentDialogArea" ).dialog( "close" );
				$("div#DepartmentDialogArea" ).dialog( "destroy" );
				$("div#DepartmentDialogArea").html("");
				
			});
			
			
			$("div#DepartmentDialogArea").dialog({
				title:"部门增加",
				width:950
			});
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#DepartmentDialogArea").dialog("close");
				$("div#DepartmentDialogArea").dialog("destroy")
				$("div#DepartmentDialogArea").html("");
			});
			
		});
	});	
	
	//===============================修改处理===============================================================
	$("a#DepartmentsModifyLink").off().on("click",function(){
		if(departmentNo==0){
			BootstrapDialog.show({
	            title: '部门操作信息',
	            message:"请选择要修改的部门"
	        });
		}
		else{
			$("div#DepartmentDialogArea").load(host+"departments/modify.html",function(){
				//取得选择的部门
				$.getJSON("departments/get",{deptno:departmentNo},function(data){
					if(data.status=="OK"){
						$("input[name='deptno']").val(departmentNo);
						$("input[name='deptname']").val(data.model.deptname);
						$("input[name='ddesc']").val(data.model.ddesc);
						}
				});	
			//验证提交数据
			$("form#DepartmentsModifyForm").validate({
				  rules: {
				    deptname:{
				    	required: true
				    },
				    ddesc:{
				    	required: true
				    }
				    	
				  },
				  messages:{
					deptname:{
					    	required:"部门名称为空"
					},
					ddesc:{
				    	required:"部门职责为空"
				}
				 }
			});
			//拦截修改提交表单
			$("form#DepartmentsModifyForm").ajaxForm(function(result){
				if(result.status=="OK"){
					departmentNo=0;
					reloadDepList();//更新列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '部门操作信息',
		            message:result.message
		        });
				$("div#DepartmentDialogArea" ).dialog( "close" );
				$("div#DepartmentDialogArea" ).dialog( "destroy" );
				$("div#DepartmentDialogArea").html("");
				
			});
			
			
			$("div#DepartmentDialogArea").dialog({
				title:"部门信息修改",
				width:950
			});
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#DepartmentDialogArea").dialog("close");
				$("div#DepartmentDialogArea").dialog("destroy")
				$("div#DepartmentDialogArea").html("");
			});
			
		});
	}
	});
	
	//===============================删除处理==============================================================
	
	$("a#DepartmentsDelteLink").off().on("click",function(){
		if(departmentNo==0){
			BootstrapDialog.show({
	            title: '部门操作信息',
	            message:"请选择要删除的部门",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]      	
	        });
		}
		else{
			BootstrapDialog.confirm('确认删除此小区么?', function(result){
				if(result){
					$.post("departments/delete",{deptno:departmentNo},function(result){
						if(result.status=="OK"){
							departmentNo=0;
							reloadDepList();//更新列表
						}
						
						BootstrapDialog.show({
				            title: '部门操作信息',
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
	
	
	//================================查看处理=============================================================
	
	$("a#DepartmentsViewLink").off().on("click",function(){
		if(departmentNo==0){
			BootstrapDialog.show({
	            title: '部门操作信息',
	            message:"请选择要查看的部门",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#DepartmentDialogArea").load("departments/view.html",function(){
				//取得指定的部门信息
				$.getJSON("/departments/get",{deptno:departmentNo},function(em){
					if(em){
						$("span#deptno").html(departmentNo);
						$("span#deptname").html(em.model.deptname);
						$("span#ddesc").html(em.model.ddesc);
					}
				});
				
				
				$("div#DepartmentDialogArea").dialog({
					title:"部门详细信息",
					width:800
				});
				//点击取消按钮，管理弹出窗口
				$("input[value='关闭']").off().on("click",function(){
					$("div#DepartmentDialogArea").dialog("close");
					$("div#DepartmentDialogArea").dialog("destroy")
					$("div#DepartmentDialogArea").html("");
				});
				
				
			});
		}	
	});
	
});	

