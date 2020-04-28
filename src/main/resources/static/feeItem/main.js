/**
 *  收费项目的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var itemno=0;
	var unit=null;
	var feetypeNo=0;
	var cycle=null;
	var status=null;

	//设置系统页面标题
	$("span#mainpagetille").html("收费项目管理");
	//显示收费项目列表
	$("table#FeeItemGrid").jqGrid({
		url: host+'feeItem/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '收费项目编码', name: 'code', width: 50 },
			{ label: '收费项目名称', name: 'name', width: 50 },
			{ label: '收费单位', name: 'unit', width: 50 },
			{ label: '收费类型', name: 'feetype.name', width: 50 },
			{ label: '周期性', name: 'cycle', width: 50},
			{ label: '收费', name: 'status', width: 50 },
			{ label: '收费项目说明', name: 'desc', width: 50 } 
		],
		caption:"收费项目列表",
		viewrecords: true, //显示总记录数
		autowidth: true,
		height: 300,
		rowNum: 5,
		rowList:[5,6,7,8,9,10],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "no"},
		pager: "#FeeItemGridPager",
		multiselect:false,
		onSelectRow:function(ino){
			itemno=ino;
		}
		
	});
	//取得收费项目列表，填充收费单位下拉框
	$.getJSON("feeItem/list/unit",function(unitList){
		if(unitList){
			$.each(unitList,function(index,um){
				$("select#UnitSelection").append("<option value='"+um.unit+"'>"+um.unit+"</option>");
			});
		}
	});
	//取得收费项目列表，填充收费类型下拉框
	$.getJSON("feeType/list/all",function(List){
		if(List){
			$.each(List,function(index,dm){
				$("select#FeeTypeNoSelection").append("<option value='"+dm.no+"'>"+dm.name+"</option>");
			});
		}
	});
	//设置检索参数，更新jQGrid的列表显示
	function reloadFeeItemList()
	{
		$("table#FeeItemGrid").jqGrid('setGridParam',{postData:{unit:unit,
			feetypeNo:feetypeNo,cycle:cycle,status:status}}).trigger("reloadGrid");
		
	}
	
	//定义收费项目下拉框的更新事件的处理
	$("select#UnitSelection").off().on("change",function(){
		unit=$("select#UnitSelection").val();
		reloadFeeItemList();
	});
	//定义收费类型下拉框的更新事件的处理
	$("select#FeeTypeNoSelection").off().on("change",function(){
		feetypeNo=$("select#FeeTypeNoSelection").val();
		reloadFeeItemList();
	});
	
	//定义周期性的更新事件的处理
	$("input[name='cycle']").off().on("change",function(){
		cycle=$("input[name='cycle']:checked").val();
		reloadFeeItemList();
	});
	//定义收费的更新事件的处理
	$("input[name='itemStatus']").off().on("change",function(){
		status=$("input[name='itemStatus']:checked").val();
		reloadFeeItemList();
	});

	
	//点击检索事件处理
	$("a#FeeItemSearchButton").on("click",function(){
		util=$("select#UtilSelection").val();
		feetypeNo=$("select#FeeTypeNoSelection").val();
		cycle=$("input[name='cycle']:checked").val();
		status=$("input[name='itemStatus']:checked").val();
		reloadFeeItemList();
	});
	
	
	//===========================增加收费项目处理================================================
	$("a#FeeItemAddLink").off().on("click",function(){
		//取得收费项目列表，填充收费类型下拉框
		$.getJSON(host+"feeType/list/all",function(List){
			if(List){
				$.each(List,function(index,feetype){
					$("select#FeeTypeNoSelection").append("<option value='"+feetype.no+"'>"+feetype.name+"</option>");
				});
			}
		});
		$("div#FeeItemDialogArea").load("feeItem/add.html",function(){
			//验证提交数据
			$("form#FeeItemAddForm").validate({
				rules: {
					code: {
						required: true
					},
					name: {
						required: true
					},
					unit: {
						required: true
					},
					feetype: {
						required: true
					},
					cycle: {
						required: true
					},
					status: {
						required: true
					}
				},
				message:{
					code: {
						required: "收费项目编码为空"
					},
					name: {
						required: "收费项目名称为空"
					},
					unit: {
						required: "收费单位为空"
					},
					feetype: {
						required: "收费类型为空"
					},
					cycle: {
						required: "周 期性为空"
					},
					status: {
						required: "收费为空"
					}
				}
			});
			//增加收费项目的弹窗
			$("div#FeeItemDialogArea").dialog({
				title:"增加收费项目",
				width:600
			});
			
			//拦截增加提交表单
			$("form#FeeItemAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadFeeItemList(); //更新收费项目列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '收费项目操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#FeeItemDialogArea" ).dialog( "close" );
				$("div#FeeItemDialogArea" ).dialog( "destroy" );
				$("div#FeeItemDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#FeeItemDialogArea" ).dialog( "close" );
				$("div#FeeItemDialogArea" ).dialog( "destroy" );
				$("div#FeeItemDialogArea").html("");
			});
		});
	});
	
	
	//===============================修改收费项目处理=============================
	$("a#FeeItemModifyLink").off().on("click",function(){
		//取得收费项目列表，填充收费类型下拉框
		$.getJSON(host+"feeType/list/all",function(List){
			if(List){
				$.each(List,function(index,feetype){
					$("select#FeeTypeNoSelection").append("<option value='"+feetype.no+"'>"+feetype.name+"</option>");
				});
			}
		});
		if(itemno==0){
			BootstrapDialog.show({
	            title: '收费项目操作信息',
	            message:"请选择要修改的收费项目",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#FeeItemDialogArea").load("feeItem/modify.html",function(){
				//取得选择的收费项目
				$.getJSON(host+"feeItem/get",{no:itemno},function(FeeItem){
					
					if(FeeItem){
						//alert(itemno);
						$("input[name='no']").val(itemno);
						$("input[name='code']").val(FeeItem.code);
						$("input[name='name']").val(FeeItem.name);
						$("input[name='unit'][value='"+FeeItem.unit+"']").attr("checked","true");
						$("select[name='feetype.no']").val(FeeItem.feetype.no);
						$("input[name='cycle'][value='"+FeeItem.cycle+"']").attr("checked","true");
						$("input[name='status'][value='"+FeeItem.status+"']").attr("checked","true");
						$("input[name='desc']").val(FeeItem.desc);
						
					}
				});
				//弹出Dialog
				$("div#FeeItemDialogArea").dialog({
					title:"收费项目修改",
					width:600
				});
				$("form#FeeItemModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadFeeItemList(); //更新收费项目列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '收费项目操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#FeeItemDialogArea" ).dialog( "close" );
					$("div#FeeItemDialogArea" ).dialog( "destroy" );
					$("div#FeeItemDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$("div#FeeItemDialogArea" ).dialog( "close" );
					$("div#FeeItemDialogArea" ).dialog( "destroy" );
					$("div#FeeItemDialogArea").html("");
				});

			});
			
		}
	});


	//===============================删除收费项目处理=====================================

	$("a#FeeItemDeleteLink").off().on("click",function(){
		
		if(itemno==0){
			BootstrapDialog.show({
	            title: '收费项目操作信息',
	            message:"请选择要删除的收费项目",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此收费项目么?', function(result){
	            if(result) {
		            $.post("feeItem/delete",{no:itemno},function(result){
		            	if(result.status=="OK"){
		            		reloadFeeItemList(); //更新收费项目列表
						}
						BootstrapDialog.show({
				            title: '收费项目操作信息',
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



	//================================查看收费项目处理====================================

	$("a#FeeItemViewLink").off().on("click",function(){
		
		if(itemno==0){
			BootstrapDialog.show({
	            title: '收费项目操作信息',
	            message:"请选择要查看的收费项目",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#FeeItemDialogArea").load("feeItem/view.html",function(){
				//取得选择的收费项目
				$.getJSON(host+"feeItem/get",{no:itemno},function(FeeItem){
					
					if(FeeItem){
						//alert(itemno);
						$("span#code").html(FeeItem.code);
						$("span#name").html(FeeItem.name);
						$("span#unit").html(FeeItem.unit);
						$("span#feetype").html(FeeItem.feetype.no+"("+FeeItem.feetype.name+")");
						$("span#cycle").html(FeeItem.cycle);
						$("span#status").html(FeeItem.status);
						$("span#desc").html(FeeItem.desc);
						
					}
				});
				//弹出Dialog
				$("div#FeeItemDialogArea").dialog({
					title:"收费项目详细",
					width:600
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#FeeItemDialogArea").dialog( "close" );
					$("div#FeeItemDialogArea").dialog( "destroy" );
					$("div#FeeItemDialogArea").html("");
				});

			});
			
		}
	});
	

});