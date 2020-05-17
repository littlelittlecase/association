/**
 * 专业管理前端主管理JS
 * 模块：学校信息
 * 业务对象：学院管理
 * 
 */
$(function(){
	var majoyID = null;
	var majoyname = null;
	var academyid = null;
	var academyname = null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("专业管理");
	
	//设置日期的格式和选择
	
	//显示专业列表
	$("table#MajoyTable").jqGrid({
		url: 'majoy/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '专业编号', name: 'majoyid', width: 20 },
			{ label: '专业名称', name: 'majoyname', width: 50 },
			{ label: '学院编号', name: 'academy.academyid', width: 50 },
			{ label: '学院名称', name: 'academy.academyname', width: 50 }
			
			
		],
		caption:"专业列表",
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
		      id: "majoyid"},
		pager: "#Majoypager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(majoyid){
			majoyID = majoyid;
		}
		     
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("majoy/list/name",function(MajoyList){
		if(MajoyList){
			$.each(MajoyList,function(index,nm){
				$("select#MajoySelection").append("<option value='"+nm.majoyname+"'>"+nm.majoyname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadMajoyList()
	{
		
		$("table#MajoyTable").jqGrid('setGridParam',{postData:{majoyname:majoyname,
																page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义学院名下拉框的更新事件的处理
	$("select#MajoySelection").off().on("change",function(){
		majoyname=$("select#MajoySelection").val();
		
		reloadMajoyList();
		 
	});
	
	

	
	
	//===========================增加专业处理================================================
	
	$("a#MajoyAddLink").off().on("click",function(){
		$("div#MajoyDailogArea").load("majoyinfo/add.html",function(){
			//验证提交数据
			/*$("form#MajoyAddForm").validate({
				rules: {
					academy.academyid: {
						required: true
					},
					academy.academyname: {
						required: true
					},
					majoyname: {
						required: true
					},
					majoyid: {
						required: true
					}
					
				},
				message:{
					academy.academyid: {
						required: "学院编号为空"
					},
					academy.academyname: {
						required: "学院名称为空"
					},
					majoyname: {
						required: "专业名称为空"
					},
					majoyid: {
						required: "专业编号为空"
					}
					
				}
			});*/
			
			//增加专业的弹窗
			$("div#MajoyDailogArea").dialog({
				title:"增加专业",
				width:600
			});
			console.log("89");
			
			//拦截增加提交表单
			$("form#MajoyAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadMajoyList(); //更新学院列表
				}
				console.log(result.status);
				BootstrapDialog.show({
		            title: '专业操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#MajoyDailogArea").dialog( "close" );
				$("div#MajoyDailogArea").dialog( "destroy" );
				$("div#MajoyDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#MajoyDailogArea").dialog( "close" );
				$("div#MajoyDailogArea").dialog( "destroy" );
				$("div#MajoyDailogArea").html("");
			});
		});
	});
	
	//===============================修改专业信息处理=============================

	$("a#MajoyModifyLink").off().on("click",function(){
		//若无选中专业
		if(majoyID==null){
			BootstrapDialog.show({
	            title: '专业信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#MajoyDailogArea").load("majoyinfo/modify.html",function(){
				//验证提交数据
				$("form#MajoyModifyForm").validate({
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
				
				
				
				

				//取得指定的专业信息
				$.getJSON("majoy/get",{majoyid:majoyID},function(majoy){
					
					if(majoy){
						$("input[name='academy.academyid']").val(majoy.model.academy.academyid);
						$("input[name='academy.academyname']").val(majoy.model.academy.academyname);
						$("input[name='majoyname']").val(majoy.model.majoyname);
						$("input[name='majoyid']").val(majoy.model.majoyid);
					
					}
				
				});
				
				//修改专业的弹窗
				$("div#MajoyDailogArea").dialog({
					title:"修改专业",
					width:600
				});
				
				//拦截修改提交表单
				$("form#MajoyModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadMajoyList(); //更新专业列表
					}
					
					BootstrapDialog.show({
			            title: '专业操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#MajoyDailogArea").dialog( "close" );
					$("div#MajoyDailogArea").dialog( "destroy" );
					$("div#MajoyDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#MajoyDailogArea").dialog( "close" );
					$("div#MajoyDailogArea").dialog( "destroy" );
					$("div#MajoyDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除新闻处理=====================================

	$("a#MajoyDeleteLink").off().on("click",function(){
		
		if(majoyID==null){
			BootstrapDialog.show({
	            title: '专业操作信息',
	            message:"请选择要删除的专业信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除该信息吗?', function(result){
	            if(result) {
		            $.post("majoy/delete",{majoyid:majoyID},function(result){
		            	if(result.status=="OK"){
		            		reloadMajoyList(); //更新专业列表
						}
						BootstrapDialog.show({
				            title: '专业操作信息',
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