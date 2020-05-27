
$(function(){
	var applicationID = 0;
	var appname = null;
	var studentname = null;
	var reason = null;
	var appstatus = null;
	
	
	//设置系统页面标题
	$("span#mainpagetille").html("社团审核管理");
	//设置日期的格式和选择
	//显示社团审核列表
	$("table#ApplicationTable").jqGrid({
		url: 'application/list/condition/page',
		
		datatype: "json",
		colModel: [
			
			{ label: '申请编号', name: 'appid', width: 20 ,cellattr: addCellAttra},
			{ label: '社团名称', name: 'appname', width: 50,cellattr: addCellAttra },
			{ label: '申请理由', name: 'reason', width: 50 ,cellattr: addCellAttra},
			{ label: '申请人', name: 'student.studentname', width: 50,cellattr: addCellAttra },
			{ label: '申请状态', name: 'appstatus', width: 50 ,cellattr: addCellAttr},
		],
		caption:"社团审核列表",
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
		      id: "appid"},
		pager: "#Applicationpager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(appid){
			applicationID = appid;
		}
		
	});
	//改变申请状态颜色
	function addCellAttr(rowId, val, rawObject, cm, rdata) {
		            if(rawObject.planId == null ){
		                return "style='color:red'";
		            }
		        }
	//改变申请状态颜色
	function addCellAttra(rowId, val, rawObject, cm, rdata) {
		            if(rawObject.planId == null ){
		                return "style='color:#5a93a0'";
		            }
		        }
	
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
	
	//定义社团名下拉框的更新事件的处理
	$("select#ApplicationSelection").off().on("change",function(){
		appname=$("select#ApplicationSelection").val();
		
		reloadApplicationList();
	});
	

	//===========================增加处理================================================
	$("a#ApplicationAddLink").off().on("click",function(){
		$("div#ApplicationDailogArea").load("applicationinfo/add.html",function(){
			//验证提交数据
			/*$("form#StudentAddForm").validate({
				rules: {
					studentid: {
						required: true
					},
					studentname: {
						required: true
					},
					sex: {
						required: true
					},
					academyname: {
						required: true
					},
					majoyname: {
						required: true
					},
					studentphone: {
						required: true
					},
					associationname: {
						required: true
					}
					
					
				},
				message:{
					studentid: {
						required: "学号不能为空"
					},
					studentname: {
						required: "学生姓名不能为空"
					},
					sex: {
						required: "性别不能为空"
					},
					academyname: {
						required: "学院不能为空"
					},
					majoyname: {
						required: "专业不能为空"
					},
					studentphone: {
						required: "手机号码不能为空"
					},
					associationname: {
						required: "社团不能为空"
					}
					
				}
			});*/
			
			//增加学院的弹窗
			$("div#ApplicationDailogArea").dialog({
				title:"提交申请",
				width:600
			});
			
			
			//拦截增加提交表单
			$("form#ApplicationAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadApplicationList(); //更新学院列表
					console.log("89");
				}
				
				BootstrapDialog.show({
		            title: '学生操作信息',
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
	});

	//===============================审核处理=============================

		$("a#ApplicationModifyLink").off().on("click",function(){
		//若无选中审核信息
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
				
				//审核的弹窗
				$("div#ApplicationDailogArea").dialog({
					title:"审核社团",
					width:600
				});
				
				//拦截修改提交表单
				$("form#ApplicationModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadApplicationList(); //更新审核列表
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
	
	
	
	
	
	
});