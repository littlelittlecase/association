/**
 *  年度价格的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var minunitprice=null;
	var maxunitprice=null;
	var startDate=null;
	var endDate=null;
	var feeyear=null;

	//设置系统页面标题
	$("span#mainpagetille").html("年度价格管理");
	//显示年度价格列表
	$("table#YearPriceGrid").jqGrid({
		url: host+'yearPrice/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '收费年度', name: 'feeyear', width: 50 },
			{ label: '单价', name: 'unitprice', width: 50 },
			{ label: '开始日期', name: 'startDate', width: 50 },
			{ label: '结束日期', name: 'endDate', width: 50},
			{ label: '描述', name: 'pricedesc', width: 50 }
		],
		caption:"年度价格列表",
		viewrecords: true, //显示总记录数
		autowidth: true,
		height: 400,
		rowNum: 10,
		rowList:[10,15,20],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "feeyear"},
		pager: "#YearPriceGridPager",
		multiselect:false,
		onSelectRow:function(fno){
			feeyear=fno;
		}
		
	});
	//设置检索参数，更新jQGrid的列表显示
	function reloadYearPriceList()
	{
		$("table#YearPriceGrid").jqGrid('setGridParam',{postData:{
			minunitprice:minunitprice,maxunitprice:maxunitprice,startDate:startDate,endDate:endDate}}).trigger("reloadGrid");
		
	}
		
	//定义的更新事件的处理
	$("input#minunitprice").off().on("change",function(){
		minunitprice=$("input#minunitprice").val();
		reloadYearPriceList();
	});
	$("input#maxunitprice").off().on("change",function(){
		maxunitprice=$("input#maxunitprice").val();
		reloadYearPriceList();
	});

	//定义的更新事件的处理
	$("input#startDate").off().on("change",function(){
		startDate=$("input#startDate").val();
		reloadYearPriceList();
	});
	$("input#endDate").off().on("change",function(){
		endDate=$("input#endDate").val();
		reloadYearPriceList();
	});
	

	//===========================增加年度价格处理================================================
	$("a#YearPriceAddLink").off().on("click",function(){
		
		$("div#YearPriceDialogArea").load("yearprice/add.html",function(){
			//取得年度价格列表，填充年度价格下拉框
			$.getJSON(host+"feeItem/list/all",function(List){
				if(List){ 
					$.each(List,function(index,item){
						$("div#FeeItemList").append("<input type='checkbox' name='feeItems' value='"+item.no+"' />"+item.name);
					});
				}
			});
			
			//验证提交数据
			$("form#YearPriceAddForm").validate({
				rules: {
					feeyear: {
						required: true
					}
				},
				message:{
					feeyear: {
						required: "收费年度为空"
					}
				}
			});
			//增加年度价格的弹窗
			$("div#YearPriceDialogArea").dialog({
				title:"增加年度价格",
				width:600
			});
			
			//拦截增加提交表单
			$("form#YearPriceAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadYearPriceList(); //更新年度价格列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '年度价格操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#YearPriceDialogArea" ).dialog( "close" );
				$("div#YearPriceDialogArea" ).dialog( "destroy" );
				$("div#YearPriceDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#YearPriceDialogArea" ).dialog( "close" );
				$("div#YearPriceDialogArea" ).dialog( "destroy" );
				$("div#YearPriceDialogArea").html("");
			});
		});
	});
	

	
	//===============================修改年度价格处理=============================
	$("a#YearPriceModifyLink").off().on("click",function(){

		if(feeyear==null){
			BootstrapDialog.show({
	            title: '年度价格操作信息',
	            message:"请选择要修改的年度价格",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#YearPriceDialogArea").load("yearprice/modify.html",function(){
				//取得年度价格列表，填充收费项目下拉框
				$.getJSON(host+"feeItem/list/all",function(List){
					if(List){ 
						$.each(List,function(index,item){
							$("div#FeeItemList").append("<input type='checkbox' name='feeItems' value='"+item.no+"' />"+item.name);
						});
					}
				});
				//取得选择的年度价格
				$.getJSON(host+"yearPrice/get",{feeyear:feeyear},function(YearPrice){
					
					if(YearPrice){
						//alert(itemno);
						$("input[name='feeyear']").val(feeyear);
						$("input[name='unitprice']").val(YearPrice.unitprice);
						$("input[name='startDate']").val(YearPrice.startDate);
						$("input[name='endDate']").val(YearPrice.endDate);
						$("input[name='pricedesc']").val(YearPrice.pricedesc);
						if(YearPrice.item){
							$.each(YearPrice.item,function(index,FeeItemModel){
								$("input[name='feeItems'][value='"+FeeItemModel.no+"']").attr("checked","true");
							});
						} 
					}
				});
				//弹出Dialog
				$("div#YearPriceDialogArea").dialog({
					title:"年度价格修改",
					width:600
				});
				$("form#YearPriceModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadYearPriceList(); //更新年度价格列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '年度价格操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#YearPriceDialogArea" ).dialog( "close" );
					$("div#YearPriceDialogArea" ).dialog( "destroy" );
					$("div#YearPriceDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#YearPriceDialogArea" ).dialog( "close" );
					$("div#YearPriceDialogArea" ).dialog( "destroy" );
					$("div#YearPriceDialogArea").html("");
				});

			});
			
		}
	});

	
	//===============================删除年度价格处理=====================================

	$("a#YearPriceDeleteLink").off().on("click",function(){
		
		if(feeyear==null){
			BootstrapDialog.show({
	            title: '年度价格操作信息',
	            message:"请选择要删除的年度价格",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此收费年度么?', function(result){
	            if(result) {
		            $.post("yearPrice/delete",{feeyear:feeyear},function(result){
		            	if(result.status=="OK"){
		            		reloadYearPriceList(); //更新年度价格列表
						}
						BootstrapDialog.show({
				            title: '年度价格操作信息',
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


	//================================查看年度价格处理====================================

	$("a#YearPriceViewLink").off().on("click",function(){
		
		if(feeyear==null){
			BootstrapDialog.show({
	            title: '年度价格操作信息',
	            message:"请选择要查看的年度价格",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#YearPriceDialogArea").load("yearprice/view.html",function(){
				//取得选择的年度价格
				$.getJSON(host+"yearPrice/get",{feeyear:feeyear},function(YearPrice){
					
					if(YearPrice){
						$("span#feeyear").html(YearPrice.feeyear);
						$("span#unitprice").html(YearPrice.unitprice);
						$("span#startDate").html(YearPrice.startDate);
						$("span#endDate").html(YearPrice.endDate);
						$("span#pricedesc").html(YearPrice.pricedesc);
						if(YearPrice.item){
							$.each(YearPrice.item,function(index,FeeItemModel){
								$("span#feeitems").append(FeeItemModel.name+"  ");
							});
						}         
					}
				});
				//弹出Dialog
				$("div#YearPriceDialogArea").dialog({
					title:"年度价格详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#YearPriceDialogArea").dialog( "close" );
					$("div#YearPriceDialogArea").dialog( "destroy" );
					$("div#YearPriceDialogArea").html("");
				});

			});
			
		}
	});
	
	//================================查看年度价格收费项目处理====================================
	
	$("a#FeeItemViewLink").off().on("click",function(){
		
		if(feeyear==null){
			BootstrapDialog.show({
	            title: '年度价格操作信息',
	            message:"请选择要查看的年度价格",
	        	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#YearPriceDialogArea").load("yearprice/feeitemview.html",function(){
				//取得选择的年度价格
				$.getJSON(host+"yearPrice/get",{feeyear:feeyear},function(YearPrice){
					
					if(YearPrice){
						$("span#feeyear").html(YearPrice.feeyear);
						if(YearPrice.item){
							$.each(YearPrice.item,function(index,FeeItemModel){
								$("span#feeitems").append(FeeItemModel.name+"  ");
							});
						}         
					}
				});
				//弹出Dialog
				$("div#YearPriceDialogArea").dialog({
					title:"年度价格详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#YearPriceDialogArea").dialog( "close" );
					$("div#YearPriceDialogArea").dialog( "destroy" );
					$("div#YearPriceDialogArea").html("");
				});
	
			});
			
		}
	});
});