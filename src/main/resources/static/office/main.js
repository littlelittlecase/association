/**
 * 新闻管理前端主管理JS
 * 模块：办公
 * 业务对象：新闻管理
 * 作者:张梓琪
 * 
 */
$(function(){
	var officenewsId = 0;
	var newstype = null;
	var startDate = null;
	var endDate = null; 
	var newscontent = null;
	var newstime = null;
	//设置系统页面标题
	$("span#mainpagetille").html("新闻管理");
	//设置日期的格式和选择
	
	//显示新闻列表
	$("table#NewsTable").jqGrid({
		url: 'news/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '编号', name: 'newsno', width: 20 },
			{ label: '新闻类型', name: 'newstype', width: 50 },
			{ label: '新闻事件', name: 'newstime', width: 50 },
			{ label: '新闻内容', name: 'newscontent', width: 100 },
		],
		caption:"新闻列表",
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
		      id: "newsno"},
		pager: "#Newspager",
		multiselect:false,
		//选中点击事件
		onSelectRow:function(newsId){
			officenewsId = newsId;
			
			
		}
		
	});
	
	//取得类型列表，填充类型下拉框
	$.getJSON("news/list/type",function(NewsList){
		if(NewsList){
			$.each(NewsList,function(index,nm){
				$("select#NewsSelection").append("<option value='"+nm.newstype+"'>"+nm.newstype+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadNewsList()
	{
		
		$("table#NewsTable").jqGrid('setGridParam',{postData:{newstype:newstype,
																startDate:startDate,endDate:endDate,page:1}}).trigger("reloadGrid");
		
		
	}
	
	//定义类型下拉框的更新事件的处理
	$("select#NewsSelection").off().on("change",function(){
		newstype=$("select#NewsSelection").val();
		
		reloadNewsList();
	});
	
	
//定义新闻时间的更新事件的处理
	
	$("input#startDate").off().on("change",function(){
		startDate=$("input#startDate").val();
		reloadNewsList();
	});
	$("input#endDate").off().on("change",function(){
		endDate=$("input#endDate").val();
		reloadNewsList();
	});
	
	
	//===========================增加新闻处理================================================
	
	$("a#NewsAddLink").off().on("click",function(){
		$("div#NewsDailogArea").load("office/add.html",function(){
			//验证提交数据
			$("form#NewsAddForm").validate({
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
			$("div#NewsDailogArea").dialog({
				title:"增加新闻",
				width:600
			});
			console.log("89");
			
			//拦截增加提交表单
			$("form#NewsAddForm").ajaxForm(function(result){
				/*if(result.status=="OK"){
					reloadNewsList(); //更新新闻列表
				}*/
				console.log(result.status);
				BootstrapDialog.show({
		            title: '新闻操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#NewsDailogArea").dialog( "close" );
				$("div#NewsDailogArea").dialog( "destroy" );
				$("div#NewsDailogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#NewsDailogArea").dialog( "close" );
				$("div#NewsDailogArea").dialog( "destroy" );
				$("div#NewsDailogArea").html("");
			});
		});
	});
	
	//===============================修改新闻处理=============================

	$("a#NewsModifyLink").off().on("click",function(){
		//若无选中新闻
		if(officenewsId==0){
			BootstrapDialog.show({
	            title: '新闻信息',
	            message:"请选择要修改的信息",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}else{
			
			$("div#NewsDailogArea").load("office/modify.html",function(){
				//验证提交数据
				$("form#NewsModifyForm").validate({
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
				
				
				//取得指定的新闻信息
				$.getJSON("news/get",{newsno:officenewsId},function(news){
					
					if(news){
						$("input[name='newsno']").val(officenewsId);
						$("input[name='newscontent']").val(news.model.newscontent);
						$("input[name='newstime']").val(news.model.newstime);
						$("input[name='newstype']").val(news.model.newstype);
						
					}
				});
				
				//修改新闻的弹窗
				$("div#NewsDailogArea").dialog({
					title:"修改新闻",
					width:600
				});
				
				//拦截修改提交表单
				$("form#NewsModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadNewsList(); //更新新闻列表
					}
					
					BootstrapDialog.show({
			            title: '新闻操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#NewsDailogArea").dialog( "close" );
					$("div#NewsDailogArea").dialog( "destroy" );
					$("div#NewsDailogArea").html("");
					
				});
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#NewsDailogArea").dialog( "close" );
					$("div#NewsDailogArea").dialog( "destroy" );
					$("div#NewsDailogArea").html("");
				});
			});
		}
		
		
	});
	
	
	
	//===============================删除新闻处理=====================================

	$("a#NewsDeleteLink").off().on("click",function(){
		
		if(officenewsId==0){
			BootstrapDialog.show({
	            title: '新闻操作信息',
	            message:"请选择要删除的新闻",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此新闻吗?', function(result){
	            if(result) {
		            $.post("news/delete",{newsno:officenewsId},function(result){
		            	if(result.status=="OK"){
		            		reloadNewsList(); //更新新闻列表
						}
						BootstrapDialog.show({
				            title: '新闻操作信息',
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