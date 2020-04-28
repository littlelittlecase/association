/**
 *  建筑类型的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var typeNo=null;
	var name=null;

	//设置系统页面标题
	$("span#mainpagetille").html("建筑类型管理");
	
	//显示建筑类型列表
	$("table#BuildingTypeGrid").jqGrid({	
		url: host+'buildType/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '类型编号', name: 'no', width: 50},
			{ label: '类型名称', name: 'name', width: 50}
		],
		caption:"建筑类型列表",
		viewrecords: true,
		autowidth: true,
		height:300,
		rowNum: 10,
		rowList:[5,6,7,8,9,10],
		jsonReader : {			
			root: "list", 
			page: "page", 
			total: "pageCount", 
			records: "count", 
		    repeatitems: true, 
			id: "no" 
		},
		pager: "#BuildingTypeGridPager",//jqGrid分页样式
		multiselect:false,
		onSelectRow:function(ptno){
			typeNo=ptno;

		}		
		
	});
	function reloadBuildingTypeList()
	{
		$("table#BuildingTypeGrid").jqGrid().trigger("reloadGrid");
	}

	//===========================增加建筑类型处理=================================
	//点击建筑类型增加链接处理，嵌入add.html
	$("a#BuildingTypeAddLink").off().on("click",function(){
		$("div#BuildingTypeDialogArea").load("buildingType/add.html",function(){
				//验证提交数据
			$("form#BuildingTypeDialogArea").validate({
				rules: {
					no: {
						required: true
					},
					name: {
						required: true
					}
				},
				message:{
					no: {
						required: "类型编号为空"
					},
					name: {
						required: "类型名称空"
					}
				}
			});
			//增加建筑类型的弹窗
			$("div#BuildingTypeDialogArea").dialog({
				title:"增加建筑类型",
				width:400
			});
			//拦截增加提交表单
			$("form#BuildingTypeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadBuildingTypeList(); //更新建筑类型列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '建筑类型操作信息',
		            message:result.message,
					buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#BuildingTypeDialogArea").dialog( "close" );
				$("div#BuildingTypeDialogArea").dialog( "destroy" );
				$("div#BuildingTypeDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#BuildingTypeDialogArea").dialog( "close" );
				$("div#BuildingTypeDialogArea").dialog( "destroy" );
				$("div#BuildingTypeDialogArea").html("");
			});						
		});		
	});	
	
	//===========================修改建筑类型处理=================================
	$("a#BuildingTypeModifyLink").off().on("click",function(){
		if(typeNo==null){
			BootstrapDialog.show({
	            title: '建筑类型操作信息',
	            message:"请选择要修改的建筑类型",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			$("div#BuildingTypeDialogArea").load("buildingType/modify.html",function(){
				//取得选择的建筑类型
				$.getJSON(host+"buildType/get",{no:typeNo},function(data){
					//alert(typeNo);
					if(data){
						$("input[name='no']").val(typeNo);
						$("input[name='name']").val(data.name);
					}
				});
				
				$("div#BuildingTypeDialogArea" ).dialog({
					title:"建筑类型修改",
					width:600
				});
				//拦截表单提交
				$("form#BuildingTypeModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadBuildingTypeList(); //更新建筑类型列表 
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '建筑类型操作信息',
			            message:result.message,
						buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#BuildingTypeDialogArea").dialog( "close" );
					$("div#BuildingTypeDialogArea").dialog( "destroy" );
					$("div#BuildingTypeDialogArea").html("");
					
				});
					
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#BuildingTypeDialogArea").dialog( "close" );
					$( "div#BuildingTypeDialogArea").dialog( "destroy" );
					$("div#BuildingTypeDialogArea").html("");
				});
			});			
		}			
	});
	
	//===========================删除建筑类型处理=================================
	$("a#BuildingTypeDeleteLink").off().on("click",function(){	
		if(typeNo==null){
			BootstrapDialog.show({
	            title: '建筑类型操作信息',
	            message:"请选择要删除的建筑类型",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此建筑类型么?', function(result){
	            if(result) {
		            $.post(host+"buildType/delete",{no:typeNo},function(result){
		            	if(result.status=="OK"){
							reloadBuildingTypeList(); //更新建筑类型列表 
						}
						BootstrapDialog.show({
				            title: '建筑类型操作信息',
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
	
	//===========================查看建筑类型处理=================================
	$("a#BuildingTypeViewLink").off().on("click",function(){		
		if(typeNo==null){
			BootstrapDialog.show({
	            title: '建筑类型操作信息',
	            message:"请选择要查看的建筑类型",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#BuildingTypeDialogArea").load("buildingType/view.html",function(){
				//取得选择的建筑类型
				$.getJSON(host+"buildType/get",{no:typeNo},function(data){
					if(data){
						$("span#no").html(typeNo);
						$("span#name").html(data.name);	
					}
				});
				//弹出Dialog
				$("div#BuildingTypeDialogArea" ).dialog({
					title:"建筑类型详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$( "div#BuildingTypeDialogArea").dialog( "close" );
					$( "div#BuildingTypeDialogArea").dialog( "destroy" );
					$("div#BuildingTypeDialogArea").html("");
				});
			});
			
		}
	});
	
});