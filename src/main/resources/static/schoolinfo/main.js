/**
 * 学院管理前端主管理JS
 * 模块：学校信息
 * 业务对象：学院管理
 * 
 */
$(function(){
	var academyID = null;
	var academyname = null;
	var academycount = null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("学院管理");
	//设置日期的格式和选择
	
	//显示学院列表
	$("table#AcademyTable").jqGrid({
		url: 'academy/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '学院编号', name: 'academyid', width: 20 },
			{ label: '学院名称', name: 'academyname', width: 50 },
			{ label: '学院人数', name: 'academycount', width: 50 },
		],
		caption:"学院列表",
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
		      id: "academyid"},
		pager: "#Academypager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(academyid){
			academyID = academyid;
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("academy/list/name",function(AcademyList){
		if(AcademyList){
			$.each(AcademyList,function(index,nm){
				$("select#AcademySelection").append("<option value='"+nm.academyname+"'>"+nm.academyname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadAcademyList()
	{
		
		$("table#AcademyTable").jqGrid('setGridParam',{postData:{academyname:academyname,
																page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义学院名下拉框的更新事件的处理
	$("select#AcademySelection").off().on("change",function(){
		academyname=$("select#AcademySelection").val();
		
		reloadAcademyList();
	});
	
	

	
	
	//===========================增加学院处理================================================
	
	$("a#AcademyAddLink").off().on("click",function(){
		$("div#AcademyDailogArea").load("schoolinfo/add.html",function(){
			//验证提交数据
			$("form#AcademyAddForm").validate({
				rules: {
					academyid: {
						required: true
					},
					academyname: {
						required: true
					},
					academycount: {
						required: true
					},
					
				},
				message:{
					academyid: {
						required: "学院编号为空"
					},
					academyname: {
						required: "学院名称为空"
					},
					academycount: {
						required: "学院人数为空"
					},
					
				}
			});
			
			//增加学院的弹窗
			$("div#AcademyDailogArea").dialog({
				title:"增加学院",
				width:600
			});
			console.log("89");
			
			//拦截增加提交表单
			$("form#AcademyAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadAcademyList(); //更新学院列表
				}
				console.log(result.status);
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
				$("div#AcademyDailogArea").dialog( "close" );
				$("div#AcademyDailogArea").dialog( "destroy" );
				$("div#AcademyDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#AcademyDailogArea").dialog( "close" );
				$("div#AcademyDailogArea").dialog( "destroy" );
				$("div#AcademyDailogArea").html("");
			});
		});
	});
	
	//===============================修改学院信息处理=============================

	$("a#AcademyModifyLink").off().on("click",function(){
		//若无选中新闻
		if(academyID==0){
			BootstrapDialog.show({
	            title: '学院信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#AcademyDailogArea").load("schoolinfo/modify.html",function(){
				//验证提交数据
				$("form#AcademyModifyForm").validate({
					rules: {
						academyid: {
							required: true
						},
						academyname: {
							required: true
						},
						academycount: {
							required: true
						},
						
					},
					message:{
						academyid: {
							required: "学院编号为空"
						},
						academyname: {
							required: "学院名称为空"
						},
						academycount: {
							required: "学院人数为空"
						},
						
					}
				});
				
				
				
				

				//取得指定的新闻信息
				$.getJSON("academy/get",{academyid:academyID},function(academy){
					
					if(academy){
						$("input[name='academyid']").val(academyID);
						$("input[name='academyname']").val(academy.model.academyname);
						$("input[name='academycount']").val(academy.model.academycount);
						/*console.log(academy.model.academycount);*/
					}
				});
				
				//修改新闻的弹窗
				$("div#AcademyDailogArea").dialog({
					title:"修改学院",
					width:600
				});
				
				//拦截修改提交表单
				$("form#AcademyModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadAcademyList(); //更新学院列表
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
					$("div#AcademyDailogArea").dialog( "close" );
					$("div#AcademyDailogArea").dialog( "destroy" );
					$("div#AcademyDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#AcademyDailogArea").dialog( "close" );
					$("div#AcademyDailogArea").dialog( "destroy" );
					$("div#AcademyDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除新闻处理=====================================

	$("a#AcademyDeleteLink").off().on("click",function(){
		
		if(academyID==0){
			BootstrapDialog.show({
	            title: '学院操作信息',
	            message:"请选择要删除的学院信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此信息吗?', function(result){
	            if(result) {
		            $.post("academy/delete",{academyid:academyID},function(result){
		            	if(result.status=="OK"){
		            		reloadAcademyList(); //更新学院列表
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