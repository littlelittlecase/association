/**
 *  楼宇的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var areaNo=0;
	var buildingtypeNo=null;
	var code=null;
	var direction=null;
	var minhome=0;
	var maxhome=0;
	var minhouse=0;
	var maxhouse=0;
	var buildingNo=null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("楼宇管理");
	
	//显示楼宇列表
	$("table#BuildingGrid").jqGrid({	
		url: host+'building/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '小区号', name: 'area.no', width: 50},
			{ label: '楼号', name: 'code', width: 50},
			{ label: '楼宇地址', name: 'address', width: 50},
			{ label: '楼宇结构编号', name: 'buildingtype.no', width: 50},
			{ label: '楼宇朝向', name: 'direction', width: 50},
			{ label: '居民数', name: 'home', width: 50},
			{ label: '公建数', name: 'house', width: 50}
		],
		caption:"楼宇列表",
		viewrecords: true,
		autowidth: true,
		height:400,
		rowNum: 15,
		rowList:[10,15,20,25],
		jsonReader : {			
			root: "list", 
			page: "page", 
			total: "pageCount", 
			records: "count", 
		    repeatitems: true, 
			id: "no" 
		},
		pager: "#BuildingGridPager",//jqGrid分页样式
		multiselect:false,
		onSelectRow:function(bno){
			buildingNo=bno;

		}		
		
	});
	
	//取得小区列表，填充小区列表下拉框
	$.getJSON(host+"area/list/all",function(areaList){
		if(areaList){
			$.each(areaList,function(index,um){
				$("select#AreaNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
			});
		}
	});
		
	//取得楼宇结构编号列表，填充楼宇结构编号列表下拉框
	$.getJSON(host+"buildType/list/all",function(typeList){
		if(typeList){
			$.each(typeList,function(index,um){
				$("select#BuildingTypeNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
			});
		}
	});
	
	//取得楼号列表，填充楼号列表下拉框
	$.getJSON(host+"building/list/all",function(codeList){
		if(codeList){
			$.each(codeList,function(index,um){
				$("select#CodeSelection").append("<option value='"+um.code+"'>"+um.code+"</option>");
			});
		}
	});
	//设置检索参数，更新jQGrid的列表显示
	function reloadBuildingList()
	{
		$("table#BuildingGrid").jqGrid('setGridParam',{postData:{areaNo:areaNo,
			buildingtypeNo:buildingtypeNo,code:code,
			direction:direction,minhome:minhome,
			maxhome:maxhome,minhouse:minhouse,maxhouse:maxhouse}}).trigger("reloadGrid");
	}	
	
	//定义小区下拉框的更新事件的处理
	$("select#AreaNoSelection").off().on("change",function(){
		areaNo=$("select#AreaNoSelection").val();
		reloadBuildingList();
	});
	//定义楼宇结构编号下拉框的更新事件的处理
	$("select#BuildingTypeNoSelection").off().on("change",function(){
		buildingtypeNo=$("select#BuildingTypeNoSelection").val();
		reloadBuildingList();
	});
	//定义楼号下拉框的更新事件的处理
	$("select#CodeSelection").off().on("change",function(){
		code=$("select#CodeSelection").val();
		reloadBuildingList();
	});
	//定义朝向下拉框的更新事件的处理
	$("select#DirectionSelection").off().on("change",function(){
		direction=$("select#DirectionSelection").val();
		reloadBuildingList();
	});
	//定义总居民数的更新事件的处理
	$("input#minhome").off().on("change",function(){
		minhome=$("input#minhome").val();
		reloadBuildingList();
	});
	$("input#maxhome").off().on("change",function(){
		maxhome=$("input#maxhome").val();
		reloadBuildingList();
	});
	//定义总公建数的更新事件的处理
	$("input#minhouse").off().on("change",function(){
		minhouse=$("input#minhouse").val();
		reloadBuildingList();
	});
	$("input#maxhouse").off().on("change",function(){
		maxhouse=$("input#maxhouse").val();
		reloadBuildingList();
	});	
	
	
	//===========================增加楼宇处理============================================================
	//点击楼宇增加链接处理，嵌入add.html
	$("a#BuildingAddLink").off().on("click",function(){
		//取得小区列表，填充小区列表下拉框
		$.getJSON(host+"area/list/all",function(areaList){
			if(areaList){
				$.each(areaList,function(index,um){
					$("select#AreaNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
				});
			}
		});
			
		//取得楼宇结构编号列表，填充楼宇结构编号列表下拉框
		$.getJSON(host+"buildType/list/all",function(typeList){
			if(typeList){
				$.each(typeList,function(index,um){
					$("select#BuildingTypeNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
				});
			}
		});
		$("div#BuildingDialogArea").load("building/add.html",function(){
				//验证提交数据
			$("form#BuildingAddForm").validate({
				rules: {
					area: {
						required: true
					},
					code: {
						required: true
					},
					address: {
						required: true
					},
					buildingtype: {
						required: true
					},
					direction: {
						required: true
					},
					home: {
						required: true
					},
					house: {
						required: true
					}
				},
				message:{
					area: {
						number: "小区号必须是正整数",
						required: "小区号为空",
						range: "大于等于0"
					},
					code: {
						required: "楼号为空"
					},
					address: {
						required: "楼宇地址为空"
					},
					buildingtype: {
						number: "楼宇结构编号必须是正整数",
						required: "楼宇结构编号为空",
				    	range: "大于等于0"
					},
					direction: {
						required: "楼宇朝向为空"
					},
					home: {
						number: "居民数必须是正整数",
				    	range: "大于等于0"
					},
					house: {
						number: "公建数必须是正整数",
				    	range: "大于等于0"
					}
				}
			});
			//增加楼宇的弹窗
			$("div#BuildingDialogArea" ).dialog({
				title:"增加楼宇",
				width:600
			});
			//拦截增加提交表单
			$("form#BuildingAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadBuildingList(); //更新楼宇列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '楼宇操作信息',
		            message:result.message,
					buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#BuildingDialogArea").dialog( "close" );
				$("div#BuildingDialogArea").dialog( "destroy" );
				$("div#BuildingDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#BuildingDialogArea").dialog( "close" );
				$("div#BuildingDialogArea").dialog( "destroy" );
				$("div#BuildingDialogArea").html("");
			});						
		});		
	});	
	
	//===========================修改楼宇处理======================================================
	$("a#BuildingModifyLink").off().on("click",function(){
		//取得小区列表，填充小区列表下拉框
		$.getJSON(host+"area/list/all",function(areaList){
			if(areaList){
				$.each(areaList,function(index,um){
					$("select#AreaNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
				});
			}
		});
			
		//取得楼宇结构编号列表，填充楼宇结构编号列表下拉框
		$.getJSON(host+"buildType/list/all",function(typeList){
			if(typeList){
				$.each(typeList,function(index,um){
					$("select#BuildingTypeNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
				});
			}
		});
		
		if(buildingNo==null){
			BootstrapDialog.show({
	            title: '楼宇操作信息',
	            message:"请选择要修改的楼宇",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			$("div#BuildingDialogArea").load("building/modify.html",function(){
				//取得选择的楼宇
				$.getJSON(host+"building/get",{no:buildingNo},function(data){
					//alert(buildingNo);
					if(data){
						$("input[name='no']").val(buildingNo);
						$("select[name='area.no']").val(data.area.no);
						$("input[name='code']").val(data.code);
						$("input[name='address']").val(data.address);
						$("select[name='buildingtype.no']").val(data.buildingtype.no);
						$("select[name='direction']").val(data.direction);
						$("input[name='home']").val(data.home);
						$("input[name='house']").val(data.house);
					}
				});
				
				$("div#BuildingDialogArea" ).dialog({
					title:"楼宇修改",
					width:600
				});
				//拦截表单提交
				$("form#BuildingModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadBuildingList(); //更新楼宇列表 
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '楼宇操作信息',
			            message:result.message,
						buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#BuildingDialogArea").dialog( "close" );
					$("div#BuildingDialogArea").dialog( "destroy" );
					$("div#BuildingDialogArea").html("");
					
				});
					
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#BuildingDialogArea").dialog( "close" );
					$( "div#BuildingDialogArea").dialog( "destroy" );
					$("div#BuildingDialogArea").html("");
				});
			});			
		}			
	});
	
	//===========================删除楼宇处理=================================
	$("a#BuildingDeleteLink").off().on("click",function(){	
		if(buildingNo==null){
			BootstrapDialog.show({
	            title: '楼宇操作信息',
	            message:"请选择要删除的楼宇",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此楼宇么?', function(result){
	            if(result) {
		            $.post(host+"building/delete",{no:buildingNo},function(result){
		            	if(result.status=="OK"){
							reloadBuildingList(); //更新楼宇列表 
						}
						BootstrapDialog.show({
				            title: '楼宇操作信息',
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
	
	//===========================查看楼宇处理=================================
	$("a#BuildingViewLink").off().on("click",function(){		
		if(buildingNo==null){
			BootstrapDialog.show({
	            title: '楼宇操作信息',
	            message:"请选择要查看的楼宇",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#BuildingDialogArea").load("building/view.html",function(){
				//取得选择的楼宇
				$.getJSON(host+"building/get",{no:buildingNo},function(data){
					if(data){
						$("span#no").html(data.no);
						$("span#area").html(data.area.no+"("+data.area.name+")");
						$("span#code").html(data.code);
						$("span#address").html(data.address);
						$("span#buildingtype").html(data.buildingtype.no+"("+data.buildingtype.name+")");
						$("span#direction").html(data.direction);
						$("span#home").html(data.home);
						$("span#house").html(data.house);	
					}
				});
				//弹出Dialog
				$("div#BuildingDialogArea" ).dialog({
					title:"楼宇详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$( "div#BuildingDialogArea").dialog( "close" );
					$( "div#BuildingDialogArea").dialog( "destroy" );
					$("div#BuildingDialogArea").html("");
				});
			});
			
		}
	});
	
});