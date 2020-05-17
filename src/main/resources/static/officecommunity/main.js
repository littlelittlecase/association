/**
 * 社区活动前端主管理JS
 * 模块：办公
 * 业务对象：社区活动
 * 作者:张梓琪
 * 
 */
$(function(){
	var officecommunityId = null;
	var activeno = 0;
	var activeplace = null;
	var startActiveDate = null;
	var endActiveDate = null;
	var activetype = null;
	var activecontent = null;
	
	//设置系统页面标题
	$("span#mainpagetille").html("社团活动管理");
	//设置日期的格式和选择
	
	//显示社团列表
	$("table#CommunityTable").jqGrid({
		url: 'community/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '编号', name: 'activeno', width: 20 },
			{ label: '活动时间', name: 'activetime', width: 50 },
			{ label: '活动地点', name: 'activeplace', width: 50 },
			{ label: '活动内容', name: 'activecontent', width: 100 },
			{ label: '社团名称', name: 'activetype', width: 50},
			
		],
		caption:"社团活动列表",
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
		      id: "activeno"},
		pager: "#CommunityGridPager",
		multiselect:false,
		
		//选中点击事件
		onSelectRow:function(communityId){
			officecommunityId = communityId;
			
			
		}
	});
	
	
	//取得类型列表，填充类型下拉框
	$.getJSON("community/list/type",function(CommunityList){
		if(CommunityList){
			$.each(CommunityList,function(index,tm){
				$("select#TypeSelection").append("<option value='"+tm.activetype+"'>"+tm.activetype+"</option>");
			});
		}
	});
	//取得地点列表，填充类型下拉框
	$.getJSON("community/list/place",function(CommunityList){
		if(CommunityList){
			$.each(CommunityList,function(index,pm){
				$("select#PlaceSelection").append("<option value='"+pm.activeplace+"'>"+pm.activeplace+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadCommunityList()
	{
		
		$("table#CommunityTable").jqGrid('setGridParam',{postData:{activeplace:activeplace,activetype:activetype,
			                                                       startActiveDate:startActiveDate,endActiveDate:endActiveDate,page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义类型下拉框的更新事件的处理
	$("select#TypeSelection").off().on("change",function(){
		activetype=$("select#TypeSelection").val();
		
		reloadCommunityList();
	});
	
	//定义地点下拉框的更新事件的处理
	$("select#PlaceSelection").off().on("change",function(){
		activeplace=$("select#PlaceSelection").val();
		
		reloadCommunityList();
	});
	//定义社团活动时间的更新事件的处理
	
	$("input#startActiveDate").off().on("change",function(){
		startActiveDate=$("input#startActiveDate").val();
		reloadCommunityList();
	});
	$("input#endActiveDate").off().on("change",function(){
		endActiveDate=$("input#endActiveDate").val();
		reloadCommunityList();
	});
	
	
	
	
	
	
	//===========================增加活动处理================================================
	
	$("a#CommunityAddLink").off().on("click",function(){
		$("div#CommunityDailogArea").load("officecommunity/add.html",function(){
			//验证提交数据
			$("form#CommunityAddForm").validate({
				rules: {
					activetype: {
						required: true
					},
					activetime: {
						required: true
					},
					activecontent: {
						required: true
					},
					activeplace: {
						required: true
					},
					
				},
				message:{
					activetype: {
						required: "活动类型为空"
					},
					activetime: {
						required: "活动时间为空"
					},
					activecontent: {
						required: "活动内容为空"
					},
					activeplace: {
						required: "活动地点为空"
					},
					
				}
			});
			
			//增添活动的弹窗
			$("div#CommunityDailogArea").dialog({
				title:"增加活动",
				width:600
			});
			
			//拦截增加提交表单
			$("form#CommunityAddForm").ajaxForm(function(result){
				if(result.status=="ok"){
					reloadCommunityList(); //更新活动列表
				}
				
				BootstrapDialog.show({
		            title: '活动操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#CommunityDailogArea").dialog( "close" );
				$("div#CommunityDailogArea").dialog( "destroy" );
				$("div#CommunityDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#CommunityDailogArea").dialog( "close" );
				$("div#CommunityDailogArea").dialog( "destroy" );
				$("div#CommunityDailogArea").html("");
			});
		});
	});
	
	
	//===============================修改活动处理=============================

	$("a#CommunityModifyLink").off().on("click",function(){
		//若无选中活动
		if(officecommunityId==0){
			BootstrapDialog.show({
	            title: '活动信息',
	            message:"请选择要修改的活动",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#CommunityDailogArea").load("officecommunity/modify.html",function(){
				//验证提交数据
				$("form#CommunityModifyForm").validate({
					rules: {
						
						activeplace: {
							required: true
						},
						activecontent: {
							required: true
						},
						activetime: {
							required: true
						},
						activetype: {
							required: true
						},
						
					},
					message:{
						
						activeplace: {
							required: "活动地点"
						},
						activecontent: {
							required: "活动内容"
						},
						activetime: {
							required: "活动时间"
						},
						activetype: {
							required: "活动类型"
						},
						
					}
				});
				
				
				//取得指定的社团活动信息
				$.getJSON("community/get",{activeno:officecommunityId},function(community){
					
					if(community){
						$("input[name='activeno']").val(officecommunityId);
						$("input[name='activetype']").val(community.model.activetype);
						$("input[name='activeplace']").val(community.model.activeplace);
						$("input[name='activetime']").val(community.model.activetime);
						$("input[name='activecontent']").val(community.model.activecontent);
						
					}
				});
				
				//修改社团活动的弹窗
				$("div#CommunityDailogArea").dialog({
					title:"修改活动",
					width:600
				});
				
				//拦截修改提交表单
				$("form#CommunityModifyForm").ajaxForm(function(result){
					if(result.status=="ok"){
						reloadCommunityList(); //更新活动列表
					}
					
					BootstrapDialog.show({
			            title: '活动操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#CommunityDailogArea").dialog( "close" );
					$("div#CommunityDailogArea").dialog( "destroy" );
					$("div#CommunityDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#CommunityDailogArea").dialog( "close" );
					$("div#CommunityDailogArea").dialog( "destroy" );
					$("div#CommunityDailogArea").html("");
				});
			});
		}
		
		
	});
	
	//===============================删除新闻处理=====================================

	$("a#CommunityDeleteLink").off().on("click",function(){
		
		if(officecommunityId==0){
			BootstrapDialog.show({
	            title: '社区操作信息',
	            message:"请选择要删除的活动",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('大哥真的确认删除此新闻吗?', function(result){
	            if(result) {
		            $.post("community/delete",{activeno:officecommunityId},function(result){
		            	if(result.status=="ok"){
		            		//alert("123");
		            		reloadCommunityList(); //更新社区活动列表
						}
						BootstrapDialog.show({
				            title: '社区活动操作信息',
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