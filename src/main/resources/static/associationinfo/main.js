/**
 * 学院管理前端主管理JS
 * 模块：学校信息
 * 业务对象：学院管理
 * 
 */
$(function(){
	var associationID = null;
	var associationname = null;
	var associationleader = null;
	var startDate = null;
	var endDate = null; 
	var associationstatus = null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("社团管理");
	//设置日期的格式和选择
	
	//显示社团列表
	$("table#AssociationTable").jqGrid({
		url: 'association/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '社团编号', name: 'associationid', width: 20 },
			{ label: '社团名称', name: 'associationname', width: 50 },
			{ label: '社团负责人', name: 'associationleader', width: 50 },
			{ label: '社团创建时间', name: 'associationtime', width: 50 },
			{ label: '社团创建状态', name: 'associationstatus', width: 50 },
		],
		caption:"社团列表",
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
		      id: "associationid"},
		pager: "#Associationpager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(associationid){
			associationID = associationid;
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("association/list/name",function(AssociationList){
		if(AssociationList){
			$.each(AssociationList,function(index,nm){
				$("select#AssociationSelection").append("<option value='"+nm.associationname+"'>"+nm.associationname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadAssociationList()
	{
		
		$("table#AssociationTable").jqGrid('setGridParam',{postData:{associationname:associationname,
																page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义社团名下拉框的更新事件的处理
	$("select#AssociationSelection").off().on("change",function(){
		associationname=$("select#AssociationSelection").val();
		
		reloadAssociationList();
	});
	
	

	
	
	//===========================增加学院处理================================================
	
	$("a#AssociationAddLink").off().on("click",function(){
		$("div#AssociationDailogArea").load("associationinfo/add.html",function(){
			//验证提交数据
			$("form#AssociationAddForm").validate({
				rules: {
					associationid: {
						required: true
					},
					associationname: {
						required: true
					},
					associationleader: {
						required: true
					},
					associationtime: {
						required: true
					},
					associationstatus: {
						required: true
					},
					
				},
				message:{
					associationid: {
						required: "社团编号为空"
					},
					associationname: {
						required: "社团名称为空"
					},
					associationleader: {
						required: "社团负责人为空"
					},
					associationtime: {
						required: "社团创建时间为空"
					},
					associationstatus: {
						required: "社团状态为空"
					},
					
				}
			});
			
			//增加社团的弹窗
			$("div#AssociationDailogArea").dialog({
				title:"增加社团",
				width:600
			});
			console.log("89");
			
			//拦截增加提交表单
			$("form#AssociationAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadAssociationList(); //更新社团列表
				}
				console.log(result.status);
				BootstrapDialog.show({
		            title: '社团操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#AssociationDailogArea").dialog( "close" );
				$("div#AssociationDailogArea").dialog( "destroy" );
				$("div#AssociationDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#AssociationDailogArea").dialog( "close" );
				$("div#AssociationDailogArea").dialog( "destroy" );
				$("div#AssociationDailogArea").html("");
			});
		});
	});
	
	//===============================修改学院信息处理=============================

		$("a#AssociationModifyLink").off().on("click",function(){
		//若无选中新闻
		if(associationID==0){
			BootstrapDialog.show({
	            title: '社团信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#AssociationDailogArea").load("associationinfo/modify.html",function(){
				//验证提交数据
				$("form#AssociationModifyForm").validate({
					rules: {
						associationid: {
							required: true
						},
						associationname: {
							required: true
						},
						associationleader: {
							required: true
						},
						associationtime: {
							required: true
						},
						associationstatus: {
							required: true
						},
						
					},
					message:{
						associationid: {
							required: "社团编号为空"
						},
						associationname: {
							required: "社团名称为空"
						},
						associationleader: {
							required: "社团负责人为空"
						},
						associationtime: {
							required: "社团创建时间为空"
						},
						associationstatus: {
							required: "社团状态为空"
						},
						
					}
				});
				
				
				
				

				//取得指定的社团信息
				$.getJSON("association/get",{associationid:associationID},function(association){
					
					if(association){
						$("input[name='associationid']").val(associationID);
						$("input[name='associationname']").val(association.model.associationname);
						$("input[name='associationleader']").val(association.model.associationleader);
						$("input[name='associationtime']").val(association.model.associationtime);
						
						$("input[name='associationstatus'][value='"+association.associationstatus+"']").attr("checked","true");
						
					}
				});
				
				//修改社团的弹窗
				$("div#AssociationDailogArea").dialog({
					title:"修改社团",
					width:600
				});
				
				//拦截修改提交表单
				$("form#AssociationModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadAssociationList(); //更新社团列表
					}
					
					BootstrapDialog.show({
			            title: '社团操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#AssociationDailogArea").dialog( "close" );
					$("div#AssociationDailogArea").dialog( "destroy" );
					$("div#AssociationDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#AssociationDailogArea").dialog( "close" );
					$("div#AssociationDailogArea").dialog( "destroy" );
					$("div#AssociationDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除社团处理=====================================

			$("a#AssociationDeleteLink").off().on("click",function(){
		
		if(associationID==0){
			BootstrapDialog.show({
	            title: '社团操作信息',
	            message:"请选择要删除的社团信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('真的确认删除此社团吗?', function(result){
	            if(result) {
		            $.post("association/delete",{associationid:associationID},function(result){
		            	if(result.status=="OK"){
		            		reloadAssociationList(); //更新社团列表
						}
						BootstrapDialog.show({
				            title: '社团操作信息',
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