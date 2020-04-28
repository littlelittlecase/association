/**
 * 客户前端主管理JS
 * 模块：baseinfo
 * 业务对象：客户
 * 作者陈思颖
 */

$(function(){
	var studentname = null;
	var academy = null;
	var classname = null;
	var sex = null;
	var ID = 0;
	var majoy = null;
	var phone = null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("学生信息管理");
	
	//显示列表
	$("table#StudentGrid").jqGrid({
		url: 'student/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '学院', name: 'academy', width: 50 },
			{ label: '专业', name: 'majoy', width: 70 },
			{ label: '班级', name: 'classname', width: 70 },
			{ label: '学生姓名', name: 'studentname', width: 70 },
			{ label: '性别', name: 'sex', width: 70},
			{ label: '手机号', name: 'phone', width: 70 },
			{ label: '身份证', name: 'ID', width: 70 }
		],
		caption:"学生信息列表",
		viewrecords: true, 
		autowidth: true,
		height: 400,
		rowNum: 10,
		rowList:[10,20,30],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "studentname"},
		pager: "#StudentGrid",
		multiselect:false,
		onSelectRow:function(studentName){
			studentname=studentName;
			
		}
	});
	

	
	//取得列表，填充列表下拉框
	$.getJSON("area/list/all",function(areaList){
		if(areaList){
			$.each(areaList,function(index,um){
				$("select#AreaNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
			});
		}
	});
	
	//取得建筑类型编号列表，填充建筑类型编号列表下拉框
	$.getJSON(host+"buildType/list/all",function(typeList){
		if(typeList){
			$.each(typeList,function(index,um){
				$("select#BuildingTypeNoSelection").append("<option value='"+um.no+"'>"+um.name+"</option>");
			});
		}
	});
	
	//取得户型编号列表，填充户型编号列表下拉框
	$.getJSON(host+"housetype/list/all",function(typeList){
		if(typeList){
			$.each(typeList,function(index,um){
				$("select#HouseTypeNoSelection").append("<option value='"+um.typeno+"'>"+um.typename+"</option>");
			});
		}
	});
	
	//取得楼宇编号列表，填充楼宇编号列表下拉框
	$.getJSON(host+"building/list/all",function(typeList){ 
		if(typeList){
			$.each(typeList,function(index,um){
				$("select#BuildingNoSelection").append("<option value='"+um.no+"'>"+um.no+"</option>");
			});
		}
	});
	
	//更新jQGrid的列表显示
	function reloadRoomList()
	{
		$("table#RoomGrid").jqGrid('setGridParam',{postData:{areano:areano,buildingtypeno:buildingtypeno,
		housetypeno:housetypeno,buildingno:buildingno}}).trigger("reloadGrid");
		
	};
	
	//定义小区下拉框的更新事件的处理
	$("select#AreaNoSelection").off().on("change",function(){
		areano=$("select#AreaNoSelection").val();
		reloadRoomList();
	});
	
	//定义建筑类型编号下拉框的更新事件的处理
	$("select#BuildingTypeNoSelection").off().on("change",function(){
		buildingtypeno=$("select#BuildingTypeNoSelection").val();
		reloadRoomList();
	});
	
	//定义户型编号下拉框的更新事件的处理
	$("select#HouseTypeNoSelection").off().on("change",function(){
		housetypeno=$("select#HouseTypeNoSelection").val();
		reloadRoomList();
	});
	
	//定义建筑编号下拉框的更新事件的处理
	$("select#BuildingNoSelection").off().on("change",function(){
		buildingno=$("select#BuildingNoSelection").val();
		reloadRoomList();
	});	
	
	//点击检索事件处理
	$("a#RoomSearchButton").on("click",function(){
		areano=$("select#AreaNoSelection").val();
		buildingtypeno=$("select#BuildingTypeNoSelection").val();
		housetypeno=$("select#HouseTypeNoSelection").val();
		buildingno=$("select#BuildingNoSelection").val();

     	reloadRoomList();
		
	});
	
	
	
	//===========================增加房间处理================================================
	
	
	
	$("a#StudentAddLink").off().on("click",function(){
		$("div#StudentDialog").load("student/add.html",function(){
			var  = null;
			var academy = null;
			var classname = null;
			var sex = null;
			var ID = 0;
			var majoy = null;
			var phone = null;
			
			
			//验证提交的数据
			$("form#StudentAddForm").validate({
				  rules: {
					  studentname:{
				  		  required: true
				  	  },
				  	  academy:{
					      required: true
					  },
					  classname:{
					      required: true,
					  },
					  sex:{
					      required: true,
					  },
					  ID:{
					      required: true
					  },
					  majoy:{
					      required: true
					  },
					  phone:{
					      required: true
					  },
					  
					  
				  },
				  messages:{
					  studentname:{
				  		  required: "学生姓名为空"
				  	  },
				  	  academy:{
					      required: "学院为空"
					  },
					  classname:{
					      required: "班级为空"
					  },
					  sex:{
					      required: "性别为空"
					  },
					  ID:{
					      required: "身份证为空"
					  },
					  majoy:{
					      required: "专业为空"
					  },
					  phone:{
					      required: "手机号码为空"
					  },
					  

				 }
			});
			
			
			//添加学生弹窗
			$("div#StudentDialog").dialog({
				title:"学生增加",
				width:950
			});
			
			//拦截增加提交表单
			$("form#StudentAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadRoomList();  //更新学生列表
				}
				
				BootstrapDialog.show({
		            title: '学生信息操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#RoomDialog").dialog( "close" );
				$("div#RoomDialog").dialog( "destroy" );
				$("div#RoomDialog").html("");
				
			});

			
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#RoomDialog").dialog( "close" );
				$("div#RoomDialog").dialog( "destroy" );
				$("div#RoomDialog").html("");
			});
			
		});
	});

	//===============================修改房间处理===============================================================
	
	$("a#RoomModifyLink").off().on("click",function(){
		//alert(customerno);
		if(roomno==0){
			BootstrapDialog.show({
	            title: '房间信息',
	            message:"请选择要修改的房间",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#RoomDialog").load("room/modify.html",function(){
				
				//验证修改的数据
				$("form#RoomModifyForm").validate({
					  rules: {
					  	  areano:{
					  		  required: true
					  	  },
						  buildingtypeno:{
						      required: true
						  },
						  housetypeno:{
						      required: true,
						  },
						  buildingno:{
						      required: true,
						  },
						  departmentcode:{
						      required: true
						  },
						  floor:{
						      required: true
						  },
						  roomcode:{
						      required: true
						  },
						  buildingarea:{
						      required: true
						  },
						  feearea:{
						      required: true
						  },
						  roomstatus:{
						      required: true
						  },
						  roomtype:{
						      required: true
						  },
						  
					  },
					  messages:{
					  	  areano:{
					  		  required: "小区编号为空"
					  	  },
						  buildingtypeno:{
						      required: "建筑类型编号为空"
						  },
						  housetypeno:{
						      required: "户型编号为空"
						  },
						  buildingno:{
						      required: "楼宇编号为空"
						  },
						  departmentcode:{
						      required: "单元为空"
						  },
						  floor:{
						      required: "楼层为空"
						  },
						  roomcode:{
						      required: "房间号为空"
						  },
						  buildingarea:{
						      required: "使用面积为空"
						  },
						  feearea:{
						      required: "缴费面积为空"
						  },
						  roomstatus:{
						      required: "房间状态为空"
						  },
						  roomtype:{
						      required: "房间类型为空"
						  },

					 }
				});
				
				//取得指定的房间信息
				$.getJSON("room/get",{roomno:roomno},function(em){
					if(em){
						$("input[name='roomno']").val(roomno);
						$("input[name='area.no']").val(em.area.no);
						$("input[name='buildingtype.no']").val(em.buildingtype.no);
						$("input[name='housetype.typeno']").val(em.housetype.typeno);
						$("input[name='building.no']").val(em.building.no);
						$("input[name='departmentcode']").val(em.departmentcode);
						$("input[name='floor']").val(em.floor);
						$("input[name='roomcode']").val(em.roomcode);
						$("input[name='buildingarea']").val(em.buildingarea);
						$("input[name='feearea']").val(em.feearea);
						$("input[name='roomstatus']").val(em.roomstatus);
						$("input[name='roomtype']").val(em.roomtype);
	
					}
				});
				
				
				
				
				//弹出Dialog
				$("div#RoomDialog" ).dialog({
					title:"房间信息修改",
					width:800
				});
				
				//拦截修改提交表单
				$("form#RoomModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadRoomList();  //更新房间列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '房间操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#RoomDialog").dialog( "close" );
					$("div#RoomDialog").dialog( "destroy" );
					$("div#RoomDialog").html("");
					
				});
	
				
				//点击取消按钮，管理弹出窗口
				$("input[value='取消']").off().on("click",function(){
					$("div#RoomDialog").dialog("close");
					$("div#RoomDialog").dialog("destroy")
					$("div#RoomDialog").html("");
				});
				
				
			});
		}
	});	
	
	//===============================删除房间处理=====================================

	$("a#RoomDeleteLink").off().on("click",function(){
		
		if(roomno==0){
			BootstrapDialog.show({
	            title: '房间操作信息',
	            message:"请选择要删除的房间",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此房间?', function(result){
	            if(result) {
		            $.post("room/delete",{roomno:roomno},function(result){
		            	if(result.status=="OK"){
		            		reloadRoomList(); 
						}
						BootstrapDialog.show({
				            title: '房间操作信息',
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

	//================================查看房间详细信息===================================

	$("a#RoomViewLink").off().on("click",function(){
		
		if(roomno==0){
			BootstrapDialog.show({
	            title: '房间操作信息',
	            message:"请选择要查看的房间",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#RoomDialog").load("room/view.html",function(){
				//alert(customerno);
				//取得选择的房间
				$.getJSON("room/get",{roomno:roomno},function(data){
					if(data){
						$("span#roomno").html(roomno);
						$("span#areano").html(data.area.no);
						$("span#areaname").html(data.area.name);
						$("span#buildingtypeno").html(data.buildingtype.no);
						$("span#buildingtypename").html(data.buildingtype.name);
						$("span#housetypeno").html(data.housetype.typeno);
						$("span#housetypename").html(data.housetype.typename);
						$("span#buildingno").html(data.building.no);
						$("span#buildingaddress").html(data.building.address);
						$("span#departmentcode").html(data.departmentcode);
						$("span#floor").html(data.floor);
						$("span#roomcode").html(data.roomcode);
						$("span#buildingarea").html(data.buildingarea);
						$("span#feearea").html(data.feearea);
						$("span#roomstatus").html(data.roomstatus);
						$("span#roomtype").html(data.roomtype);
					}
				});
				//弹出Dialog
				$("div#RoomDialog" ).dialog({
					title:"房间详细信息",
					width:800
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#RoomDialog" ).dialog( "close" );
					$("div#RoomDialog" ).dialog( "destroy" );
					$("div#RoomDialog").html("");
				});

			});
			
		}
	});
	
	
	
});