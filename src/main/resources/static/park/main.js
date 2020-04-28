/**
 *  车位的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var parkTypeNo=0;
	var buildingNo=0;
	var parkstatus=null;
	var rentunit=null;
	var feestatus=null;
	var mixarea=null;
	var maxarea=null;
	var minrentprice=null;
	var maxrentprice=null;
	var parkno=0;
	
	//设置系统页面标题
	$("span#mainpagetille").html("车位管理");
	//显示车位列表
	$("table#ParkGrid").jqGrid({
		url: host+'park/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '类型编号', name: 'parkType.no', width: 50 },
			{ label: '车位编码', name: 'code', width: 50 },
			{ label: '楼号', name: 'building.no', width: 50 },
			{ label: '位置', name: 'location', width: 50 },
			{ label: '面积', name: 'area', width: 50},
			{ label: '关联客户', name: 'parkstatus', width: 50 },
			{ label: '出租价格', name: 'rentprice', width: 50 },
			{ label: '租赁单位', name: 'rentunit', width: 50 },
			{ label: '收费状态', name: 'feestatus', width: 50 }
		],
		caption:"车位列表",
		viewrecords: true, //显示总记录数
		autowidth: true,
		height: 300,
		rowNum: 10,
		rowList:[5,6,7,8,9,10],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "no"},
		pager: "#ParkGridPager",
		multiselect:false,
		onSelectRow:function(pno){
			parkno=pno;
		}
		
	});
	//取得车位类型列表，填充类型编号下拉框
	$.getJSON(host+"parkType/list/all",function(typeList){
		if(typeList){
			$.each(typeList,function(index,um){
				$("select#ParkTypeSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
			});
		}
	});
	//取得车位列表，填充楼号下拉框
	$.getJSON(host+"building/list/all",function(List){
		if(List){
			$.each(List,function(index,dm){
				$("select#BuildingNoSelection").append("<option value='"+dm.no+"'>"+dm.code+"</option>");
			});
		}
	});
	//设置检索参数，更新jQGrid的列表显示
	function reloadParkList()
	{
		$("table#ParkGrid").jqGrid('setGridParam',{postData:{parkTypeNo:parkTypeNo,
			buildingNo:buildingNo,parkstatus:parkstatus,
			rentunit:rentunit,feestatus:feestatus,mixarea:mixarea,
			maxarea:maxarea,minrentprice:minrentprice,maxrentprice:maxrentprice}}).trigger("reloadGrid");
		
	}
	
	//定义类型编号下拉框的更新事件的处理
	$("select#ParkTypeSelection").off().on("change",function(){
		parkTypeNo=$("select#ParkTypeSelection").val();
		reloadParkList();
	});
	//定义楼号下拉框的更新事件的处理
	$("select#BuildingNoSelection").off().on("change",function(){
		buildingNo=$("select#BuildingNoSelection").val();
		reloadParkList();
	});
	//定义租赁单位下拉框的更新事件的处理
	$("select#UnitSelection").off().on("change",function(){
		rentunit=$("select#UnitSelection").val();
		reloadParkList();
	});
	//定义关联客户的更新事件的处理
	$("input[name='parkstatus']").off().on("change",function(){
		parkstatus=$("input[name='parkstatus']:checked").val();
		reloadParkList();
	});
	//定义收费状态的更新事件的处理
	$("input[name='feestatus']").off().on("change",function(){
		feestatus=$("input[name='feestatus']:checked").val();
		reloadParkList();
	});
	//定义面积的更新事件的处理
	$("input[name='mixarea']").off().on("change",function(){
		mixarea=$("input[name='mixarea']").val();
		reloadParkList();
	});
	$("input[name='maxarea']").off().on("change",function(){
		maxarea=$("input[name='maxarea']").val();
		reloadParkList();
	});
	//定义出租价格的更新事件的处理
	$("input[name='minrentprice']").off().on("change",function(){
		minrentprice=$("input[name='minrentprice']").val();
		reloadParkList();
	});
	$("input[name='maxrentprice']").off().on("change",function(){
		maxrentprice=$("input[name='maxrentprice']").val();
		reloadParkList();
	});
/*	//点击检索事件处理
	$("a#ParkSearchButton").on("click",function(){
		util=$("select#UtilSelection").val();
		cycle=$("input[name='cycle']").val();
		cycle=$("input[name='cycle']").val();
		status=$("input[name='status']").val();
		reloadParkList();
	});*/
	
	
	//===========================增加车位处理================================================
	$("a#ParkAddLink").off().on("click",function(){
		//取得车位类型列表，填充类型编号下拉框
		$.getJSON(host+"parkType/list/all",function(typeList){
			if(typeList){
				$.each(typeList,function(index,um){
					$("select#ParkTypeSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
				});
			}
		});
		//取得车位列表，填充楼号下拉框
		$.getJSON(host+"building/list/all",function(List){
			if(List){
				$.each(List,function(index,dm){
					$("select#BuildingNoSelection").append("<option value='"+dm.no+"'>"+dm.code+"</option>");
				});
			}
		});
		$("div#ParkDialogArea").load("park/add.html",function(){
			//验证提交数据
			$("form#ParkAddForm").validate({
				rules: {
					parkType: {
						required: true
					},
					code: {
						required: true
					},
					building: {
						required: true
					},
					location: {
						required: true
					},
					area: {
						required: true,
						min:0
					},
					parkstatus: {
						required: true
					},
					rentprice: {
						required: true,
						min:0
					},
					rentunit: {
						required: true
					},
					feestatus: {
						required: true
					}
				},
				message:{
					parkType: {
						required: "类型编号为空"
					},
					code: {
						required: "车位编码为空"
					},
					building: {
						required: "楼号为空"
					},
					location: {
						required: "位置为空"
					},
					area: {
						number: "面积必须是数值",
				    	range: "大于等于0"
					},
					parkstatus: {
						required: "关联客户状态为空"
					},
					rentprice: {
						number: "总居民数必须是数值",
				    	range: "大于等于0"
					},
					rentunit: {
						required: "租赁单位为空"
					},
					feestatus: {
						required: "收费状态为空"
					}
				}
			});
			//增加车位的弹窗
			$("div#ParkDialogArea").dialog({
				title:"增加车位",
				width:600
			});
			
			//拦截增加提交表单
			$("form#ParkAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadParkList(); //更新车位列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '车位操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#ParkDialogArea").dialog( "close" );
				$("div#ParkDialogArea").dialog( "destroy" );
				$("div#ParkDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#ParkDialogArea").dialog( "close" );
				$("div#ParkDialogArea").dialog( "destroy" );
				$("div#ParkDialogArea").html("");
			});
		});
	});
	
	
	//===============================修改车位处理=============================
	$("a#ParkModifyLink").off().on("click",function(){

		if(parkno==0){
			BootstrapDialog.show({
	            title: '车位操作信息',
	            message:"请选择要修改的车位",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			//取得车位类型列表，填充类型编号下拉框
			$.getJSON(host+"parkType/list/all",function(typeList){
				if(typeList){
					$.each(typeList,function(index,um){
						$("select#ParkTypeSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
					});
				}
			});
			//取得车位列表，填充楼号下拉框
			$.getJSON(host+"building/list/all",function(List){
				if(List){
					$.each(List,function(index,dm){
						$("select#BuildingNoSelection").append("<option value='"+dm.no+"'>"+dm.code+"</option>");
					});
				}
			});
			$("div#ParkDialogArea").load("park/modify.html",function(){
				//取得选择的车位
				$.getJSON(host+"park/get",{no:parkno},function(data){
					//alert(parkno);
					if(data){
						$("input[name='no']").val(parkno);
						$("select[name='parkType.no']").val(data.parkType.no);
						$("input[name='code']").val(data.code);
						$("select[name='building.no']").val(data.building.no);
						$("input[name='location']").val(data.location);
						$("input[name='area']").val(data.area);
						$("input[name='rentprice']").val(data.rentprice);
						$("select[name='rentunit']").val(data.rentunit);
						$("input[name='parkstatus'][value='"+data.parkstatus+"']").attr("checked","true");
						$("input[name='feestatus'][value='"+data.feestatus+"']").attr("checked","true");
					}
				});
				
				$("div#ParkDialogArea" ).dialog({
					title:"车位修改",
					width:600
				});
				//拦截表单提交
				$("form#ParkModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadParkList(); //更新车位列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '车位操作信息',
			            message:result.message,
						buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#ParkDialogArea").dialog( "close" );
					$("div#ParkDialogArea").dialog( "destroy" );
					$("div#ParkDialogArea").html("");
					
				});
					
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#ParkDialogArea").dialog( "close" );
					$( "div#ParkDialogArea").dialog( "destroy" );
					$("div#ParkDialogArea").html("");
				});
			});			
		}			
	});

	//===============================删除车位处理=====================================
	$("a#ParkDeleteLink").off().on("click",function(){	
		if(parkno==0){
			BootstrapDialog.show({
	            title: '车位操作信息',
	            message:"请选择要删除的车位",
				buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此车位么?', function(result){
	            if(result) {
		            $.post(host+"park/delete",{no:parkno},function(result){
		            	if(result.status=="OK"){
							reloadParkList(); //更新车位列表 
						}
						BootstrapDialog.show({
				            title: '车位操作信息',
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


	//================================查看车位处理====================================

	$("a#ParkViewLink").off().on("click",function(){
		
		if(parkno==0){
			BootstrapDialog.show({
	            title: '车位操作信息',
	            message:"请选择要查看的车位",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#ParkDialogArea").load("park/view.html",function(){
				//取得选择的车位
				$.getJSON(host+"park/get",{no:parkno},function(data){
					if(data){
						$("span#parkType").html(data.parkType.no+"("+data.parkType.name+")");
						$("span#code").html(data.code);
						$("span#building").html(data.building.no+"("+data.building.code+")");
						$("span#location").html(data.location);
						$("span#area").html(data.area);
						$("span#rentprice").html(data.rentprice);
						$("span#rentunit").html(data.rentunit);
						$("span#parkstatus").html(data.parkstatus);
						$("span#feestatus").html(data.feestatus);
						
					}
				});
				//弹出Dialog
				$("div#ParkDialogArea" ).dialog({
					title:"车位详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#ParkDialogArea").dialog( "close" );
					$("div#ParkDialogArea").dialog( "destroy" );
					$("div#ParkDialogArea").html("");
				});

			});
			
		}
	});
	

});