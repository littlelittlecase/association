/**
 * 学院管理前端主管理JS
 * 模块：学校信息
 * 业务对象：学院管理
 * 
 */
$(function(){
	var applicationID = null;
	var appname = null;
	var studentname = null;
	var reason = null;
	var appstatus = null;
	
	
	//设置系统页面标题
	/*$("span#mainpagetille").html("社团审核管理");*/
	$("span#mainpagetille").html("学生实践安排管理");
	//设置日期的格式和选择
	
	//显示社团审核列表
	$("table#ApplicationTable").jqGrid({
		url: 'application/list/condition/page',
		
		datatype: "json",
		colModel: [
			{ label: '车牌号', name: 'appid', width: 20 },
			{ label: '班级', name: 'appname', width: 50 },
			{ label: '负责人', name: 'appstatus', width: 50 },
			{ label: '负责人手机号码', name: 'reason', width: 50 },
			{ label: '司机手机号码', name: 'student.studentname', width: 50 },
			
			
			/*{ label: '申请编号', name: 'appid', width: 20 },
			{ label: '社团名称', name: 'appname', width: 50 },
			{ label: '申请状态', name: 'appstatus', width: 50 },
			{ label: '申请理由', name: 'reason', width: 50 },
			{ label: '申请人', name: 'student.studentname', width: 50 },*/
		],
/*		caption:"社团审核列表",
 * 
*/		
		caption:"学生实践列表",
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
		      id: "appid"},
		pager: "#Applicationpager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(appid){
			applicationID = appid;
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("application/list/name",function(ApplicationList){
		if(ApplicationList){
			$.each(ApplicationList,function(index,nm){
				$("select#ApplicationSelection").append("<option value='"+nm.appname+"'>"+nm.appname+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadApplicationList()
	{
		
		$("table#ApplicationTable").jqGrid('setGridParam',{postData:{appname:appname,
																page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义学院名下拉框的更新事件的处理
	$("select#ApplicationSelection").off().on("change",function(){
		appname=$("select#ApplicationSelection").val();
		
		reloadApplicationList();
	});
	
	

	
	
	//===========================增加学院处理================================================
	
	/*$("a#AssociationAddLink").off().on("click",function(){
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
	*/
	//===============================审核处理=============================

		$("a#ApplicationModifyLink").off().on("click",function(){
		//若无选中新闻
		if(applicationID==0){
			BootstrapDialog.show({
	            title: '社团审核信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#ApplicationDailogArea").load("applicationinfo/modify.html",function(){
				

				//取得指定的社团信息
				$.getJSON("application/get",{appid:applicationID},function(application){
					
					if(application){
						$("input[name='appid']").val(applicationID);
						$("input[name='appname']").val(application.model.appname);
						$("input[name='appstatus'][value='"+application.appstatus+"']").attr("checked","true");
						$("input[name='reason']").val(application.model.reason);
						$("input[name='student.studentname']").val(application.model.student.studentname);
						
					}
				});
				
				//修改新闻的弹窗
				$("div#ApplicationDailogArea").dialog({
					title:"审核社团",
					width:600
				});
				
				//拦截修改提交表单
				$("form#ApplicationModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadApplicationList(); //更新学院列表
					}
					
					BootstrapDialog.show({
			            title: '更新操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#ApplicationDailogArea").dialog( "close" );
					$("div#ApplicationDailogArea").dialog( "destroy" );
					$("div#ApplicationDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#ApplicationDailogArea").dialog( "close" );
					$("div#ApplicationDailogArea").dialog( "destroy" );
					$("div#ApplicationDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除新闻处理=====================================

			/*$("a#AssociationDeleteLink").off().on("click",function(){
		
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
			BootstrapDialog.confirm('大哥真的确认删除此社团吗?', function(result){
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

	*/
	
	
});