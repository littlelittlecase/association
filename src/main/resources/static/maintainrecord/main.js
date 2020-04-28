/**
 * 维修表单管理前端主管理JS
 * 模块：收费
 * 业务对象：收费管理
 * 作者:张梓琪
 * 
 */
$(function(){
	var recordId = 0;
	var mtypeno = 0;
	var roomno = 0;
	var wstatus = null;
	var providerno = 0;
	var startDate = null;
	var endDate = null;
	var wdate = null;
	var mdate = null;
	
	
	
	//设置系统页面标题
	$("span#mainpagetille").html("维修表单管理");
	//设置日期的格式和选择
	
	//显示员工列表
	$("table#MaintainrecordTable").jqGrid({
		url: 'maintainrecord/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '报修序号', name: 'recordno', width: 20 },
			{ label: '维修类型', name: 'mtype.mtypeno', width: 50 },
			{ label: '报修日期', name: 'mdate', width: 50 },
			{ label: '维修状态', name: 'wstatus', width: 100 },
			{ label: 'Room序号', name: 'room.roomno', width: 100 },
		],
		caption:"维修表单列表",
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
		      id: "recordno"},
		pager: "#Maintainrecordpager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(m){
			recordId = m;
			
			
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("maintainrecord/list/type",function(MaintainrecordList){
		if(MaintainrecordList){
			$.each(MaintainrecordList,function(index,nm){
				$("select#TypeSelection").append("<option value='"+nm.mtypeno+"'>"+nm.mtypename+"</option>");
			});
		}
	});
	//取得room序号列表，填充类型下拉框
	$.getJSON("maintainrecord/list/room",function(MaintainrecordList){
		if(MaintainrecordList){
			$.each(MaintainrecordList,function(index,nm){
				$("select#RoomNoSelection").append("<option value='"+nm.roomno+"'>"+nm.roomno+"</option>");
			});
		}
	});
	
	//取得维修公司列表，填充维修下拉框
	$.getJSON("maintainrecord/list/provider",function(MaintainrecordList){
		if(MaintainrecordList){
			$.each(MaintainrecordList,function(index,pm){
				$("select#ProviderNoSelection").append("<option value='"+pm.providerno+"'>"+pm.pname+"</option>");
				
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	/*function reloadMaintainrecordList()
	{
		
		$("table#MaintainrecordTable").jqGrid('setGridParam',{postData:{mtypeno:mtypeno,roomno:roomno,
																		wstatus:wstatus,providerno：providerno,
																		startDate:startDate,endDate:endDate,}}).trigger("reloadGrid");
	}*/
	function reloadMaintainrecordList()
	{
		$("table#MaintainrecordTable").jqGrid('setGridParam',{postData:{mtypeno:mtypeno,roomno:roomno,
																		wstatus:wstatus,providerno:providerno,
																		startDate:startDate,endDate:endDate,page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义维修类型下拉框的更新事件的处理
	$("select#TypeSelection").off().on("change",function(){
		mtypeno=$("select#TypeSelection").val();
		
		reloadMaintainrecordList();
	});
	//定义房间序号下拉框的更新事件的处理
	$("select#RoomNoSelection").off().on("change",function(){
		roomno=$("select#RoomNoSelection").val();
		
		reloadMaintainrecordList();
	});
	//定义维修状态下拉框的更新事件的处理
	$("select#WStatusSelection").off().on("change",function(){
		wstatus=$("select#WStatusSelection").val();
		
		reloadMaintainrecordList();
	});
	//定义维修服务公司下拉框的更新事件的处理
	$("select#ProviderNoSelection").off().on("change",function(){
		providerno=$("select#ProviderNoSelection").val();
		
		reloadMaintainrecordList();
	});
	//定义报修时间的更新事件的处理
	
	$("input#startDate").off().on("change",function(){
		startDate=$("input#startDate").val();
		reloadMaintainrecordList();
	});
	$("input#endDate").off().on("change",function(){
		endDate=$("input#endDate").val();
		reloadMaintainrecordList();
	});
	
	//===========================增加新闻处理================================================
	
	$("a#MaintainrecordAddLink").off().on("click",function(){
		$("div#MaintainrecordDailogArea").load("maintainrecord/add.html",function(){
			$("div#MaintainrecordDailogArea").dialog({
				title:"增加新闻",
				width:800
			});
			
			//验证提交数据
			/*$("form#MaintainrecordAddForm").validate({
				rules: {
					newstype: {
						required: true
					},
					newstime: {
						required: true
					},
					newscontent: {
						required: true
					},
					
				},
				message:{
					newstype: {
						required: "新闻类型为空"
					},
					newstime: {
						required: "新闻时间为空"
					},
					newscontent: {
						required: "新闻内容为空"
					},
					
				}
			});
			//增加新闻的弹窗
			*/
			//取得类型列表，填充类型下拉框
			$.getJSON("maintainrecord/list/type",function(MaintainrecordList){
				if(MaintainrecordList){
					$.each(MaintainrecordList,function(index,nm){
						$("select#AddTypeSelection").append("<option value='"+nm.mtypeno+"'>"+nm.mtypename+"</option>");
					});
				}
			});
			//取得维修工人列表，填充维修工人下拉框
			$.getJSON("maintainrecord/list/wemp",function(MaintainrecordList){
				if(MaintainrecordList){
					$.each(MaintainrecordList,function(index,pm){
						$("select#AddWempidSelection").append("<option value='"+pm.wempid+"'>"+pm.wempname+"</option>");
						
					});
				}
			});
			//取得维修公司列表，填充维修公司下拉框
			$.getJSON("maintainrecord/list/provider",function(MaintainrecordList){
				if(MaintainrecordList){
					$.each(MaintainrecordList,function(index,pm){
						$("select#AddProviderNoSelection").append("<option value='"+pm.providerno+"'>"+pm.pname+"</option>");
						
					});
				}
			});
			//取得room序号列表，填充room下拉框
			$.getJSON("maintainrecord/list/room",function(MaintainrecordList){
				if(MaintainrecordList){
					$.each(MaintainrecordList,function(index,nm){
						$("select#AddRoomNoSelection").append("<option value='"+nm.roomno+"'>"+nm.roomno+"</option>");
					});
				}
			});
			//拦截增加提交表单
			$("form#MaintainrecordAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadMaintainrecordList(); //更新维修列表
				}
				
				BootstrapDialog.show({
		            title: '维修列表操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#MaintainrecordDailogArea").dialog( "close" );
				$("div#MaintainrecordDailogArea").dialog( "destroy" );
				$("div#MaintainrecordDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#MaintainrecordDailogArea").dialog( "close" );
				$("div#MaintainrecordDailogArea").dialog( "destroy" );
				$("div#MaintainrecordDailogArea").html("");
			});
		});
	});
	
	//===============================修改维修表单处理=============================

	$("a#MaintainrecordModifyLink").off().on("click",function(){
		//若无选中新闻
		if(recordId==0){
			BootstrapDialog.show({
	            title: '维修表单信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#MaintainrecordDailogArea").load("maintainrecord/modify.html",function(){
				
				//取得指定的新闻信息
				$.getJSON("maintainrecord/get",{recordno:recordId},function(maintainrecord){
					
					if(maintainrecord){
						$("input[name='recordno']").val(recordId);
						$("select[name='mtype.mtypeno']").val(maintainrecord.model.mtype.mtypeno);
						$("select[name='room.roomno']").val(maintainrecord.model.room.roomno);
						$("input[name='contactname']").val(maintainrecord.model.contactname);
						$("input[name='mobile']").val(maintainrecord.model.mobile);
						$("input[name='tel']").val(maintainrecord.model.tel);
						$("input[name='mdate']").val(maintainrecord.model.mdate);
						$("input[name='mdesc']").val(maintainrecord.model.mdesc);
						$("select[name='wemp.wempid']").val(maintainrecord.model.wemp.wempid);
						$("input[name='wdate']").val(maintainrecord.model.wdate);
						$("input[name='wtask']").val(maintainrecord.model.wtask);
						$("input[name='wresult']").val(maintainrecord.model.wresult);
						$("input[name='wstatus'][value='"+maintainrecord.model.wstatus+"']").attr("checked","true");
						$("input[name='wfee']").val(maintainrecord.model.wfee);
						$("input[name='clientfeeback']").val(maintainrecord.model.clientfeeback);
						$("select[name='provider.providerno']").val(maintainrecord.model.provider.providerno);
						
					}
				});
				
				//取得类型列表，填充类型下拉框
				$.getJSON("maintainrecord/list/type",function(MaintainrecordList){
					if(MaintainrecordList){
						$.each(MaintainrecordList,function(index,nm){
							$("select#ModifyTypeSelection").append("<option value='"+nm.mtypeno+"'>"+nm.mtypename+"</option>");
						});
					}
				});
				//取得room序号列表，填充room下拉框
				$.getJSON("maintainrecord/list/room",function(MaintainrecordList){
					if(MaintainrecordList){
						$.each(MaintainrecordList,function(index,nm){
							$("select#ModifyRoomNoSelection").append("<option value='"+nm.roomno+"'>"+nm.roomno+"</option>");
						});
					}
				});
				
				//取得维修公司列表，填充维修公司下拉框
				$.getJSON("maintainrecord/list/provider",function(MaintainrecordList){
					if(MaintainrecordList){
						$.each(MaintainrecordList,function(index,pm){
							$("select#ModifyProviderNoSelection").append("<option value='"+pm.providerno+"'>"+pm.pname+"</option>");
							
						});
					}
				});
				//取得维修工人列表，填充维修工人下拉框
				$.getJSON("maintainrecord/list/wemp",function(MaintainrecordList){
					if(MaintainrecordList){
						$.each(MaintainrecordList,function(index,pm){
							$("select#ModifyWempidSelection").append("<option value='"+pm.wempid+"'>"+pm.wempname+"</option>");
							
						});
					}
				});
				
				//修改维修的弹窗
				$("div#MaintainrecordDailogArea").dialog({
					title:"修改维修单",
					width:600
				});
				
				//拦截修改提交表单
				$("form#MaintainrecordModifyForm").ajaxForm(function(result){
					if(result.status=="ok"){
						reloadMaintainrecordList(); //更新维修列表
					}
					
					BootstrapDialog.show({
			            title: '维修单操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#MaintainrecordDailogArea").dialog( "close" );
					$("div#MaintainrecordDailogArea").dialog( "destroy" );
					$("div#MaintainrecordDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#MaintainrecordDailogArea").dialog( "close" );
					$("div#MaintainrecordDailogArea").dialog( "destroy" );
					$("div#MaintainrecordDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	
	//===============================删除维修表单处理=====================================

	$("a#MaintainrecordDeleteLink").off().on("click",function(){
		
		if(recordId==0){
			BootstrapDialog.show({
	            title: '维修表单操作信息',
	            message:"请选择要删除的维修表",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('大哥真的确认删除此维修底单吗?', function(result){
	            if(result) {
		            $.post("maintainrecord/delete",{recordno:recordId},function(result){
		            	if(result.status=="ok"){
		            		reloadMaintainrecordList(); //更新新闻列表
						}
						BootstrapDialog.show({
				            title: '维修表单操作信息',
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

	//===============================查看维修表单处理=====================================
	
	$("a#MaintainrecordViewLink").off().on("click",function(){
		//若无选中新闻
		if(recordId==0){
			BootstrapDialog.show({
	            title: '维修表单信息',
	            message:"请选择要查看的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#MaintainrecordDailogArea").load("maintainrecord/view.html",function(){
				
				//取得指定的新闻信息
				$.getJSON("maintainrecord/get",{recordno:recordId},function(maintainrecord){
					//alert(recordId)
					
					if(maintainrecord){
						$("span[name='recordno']").html(recordId);
						$("span[name='mtype.mtypeno']").html(maintainrecord.model.mtype.mtypeno);
						$("span[name='room.roomno']").html(maintainrecord.model.room.roomno);
						$("span[name='contactname']").html(maintainrecord.model.contactname);
						$("span[name='mobile']").html(maintainrecord.model.mobile);
						$("span[name='tel']").html(maintainrecord.model.tel);
						$("span[name='mdate']").html(maintainrecord.model.mdate);
						$("span[name='mdesc']").html(maintainrecord.model.mdesc);
						$("span[name='wemp.wempid']").html(maintainrecord.model.wemp.wempid);
						$("span[name='wdate']").html(maintainrecord.model.wdate);
						$("span[name='wtask']").html(maintainrecord.model.wtask);
						$("span[name='wresult']").html(maintainrecord.model.wresult);
						$("span[name='wstatus']").html(maintainrecord.model.wstatus);
						$("span[name='wfee']").html(maintainrecord.model.wfee);
						$("span[name='clientfeeback']").html(maintainrecord.model.clientfeeback);
						$("span[name='provider.providerno']").html(maintainrecord.model.provider.providerno);
						
					}
				});
				
				
				
				//查看维修的弹窗
				$("div#MaintainrecordDailogArea").dialog({
					title:"查看维修单",
					width:600
				});
				
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#MaintainrecordDailogArea").dialog( "close" );
					$("div#MaintainrecordDailogArea").dialog( "destroy" );
					$("div#MaintainrecordDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
});