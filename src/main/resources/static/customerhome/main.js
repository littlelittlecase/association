/**
 * 客户前端主管理JS
 * 模块：baseinfo
 * 业务对象：客户
 * 作者陈思颖
 */

$(function(){
	var chno=0;
	var customerno=0;
	var livedate=null;
	var receivedate=null;

	var host="http://localhost:8100";
	//设置系统页面标题
	$("span#mainpagetille").html("入住历史查询");
	
	//显示客户列表
	$("table#CustomerHomeGrid").jqGrid({
		url: 'customerhome/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '客户序号', name: 'customer.customerno', width: 70 },
			//{ label: '客户姓名', name: 'customer.cname', width: 70 },
			{ label: '房间编号', name: 'room.roomno', width: 70 },
			{ label: '居住类型', name: 'livingtype.typename', width: 70 },
			{ label: '入住日期', name: 'livedate', width: 70 },
			{ label: '收房日期', name: 'receivedate', width: 70 },
			{ label: '居住人数', name: 'humancount', width: 70 } 
		],
		caption:"客户房间列表",
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
		      id: "chno"},
		pager: "#CustomerHomeGridPager",
		multiselect:false,
		onSelectRow:function(cchno){
			chno=cchno;
			//alert(chno);
		}
	});
	
	//更新jQGrid的列表显示
	function reloadCustomerHomeList()
	{
		$("table#CustomerHomeGrid").jqGrid('setGridParam',{postData:{customerno:customerno,
			livedate:livedate, receivedate:receivedate}}).trigger("reloadGrid");
		
	};
	;
	
	//定义输入客户序号更新事件的处理
	$("input#CustomerNoSelection").off().on("change",function(){
		customerno=$("input#CustomerNoSelection").val();
		reloadCustomerHomeList();
	});
	
	
	//定义输入入住日期收房日期更新事件的处理
	$("input#livedate").off().on("change",function(){
		livedate=$("input#livedate").val();
		reloadCustomerHomeList();
	});
	$("input#receivedate").off().on("change",function(){
		receivedate=$("input#receivedate").val();
		reloadCustomerHomeList();
	});
	
	
	//点击检索事件处理
	$("a#CustomerHomeSearchButton").on("click",function(){

		customerno=$("input#CustomerNoSelection").val();
		
		livedate=$("input#livedate").val();
		receivedate=$("input#receivedate").val();

     	reloadCustomerHomeList();
		
	});
	
	
	
	//===========================增加客户处理================================================
	
	
	
	$("a#CustomerHomeAddLink").off().on("click",function(){
		$("div#CustomerHomeDialog").load("customerhome/add.html",function(){
			
			//验证提交的数据
			$("form#CustomerHomeAddForm").validate({
				  rules: {
					  customer:{
				  		  required: true,
				  	  },
					  room:{
				  		  required: true,
				  	  },
				  	  livingtype:{
					      required: true
					  },
					  livedate:{
					      required: true
					  },
					  receivedate:{
					      required: true
					  },
					  feestartdate:{
					      required: true
					  },
					  feeenddate:{
					      required: true
					  },
					  humancount:{
					      required: true
					  },
				  },
				  messages:{
					  customerno:{
				  		  required: "客户序号为空"
				  	  },
					  roomno:{
				  		  required: "房间编号为空"
				  	  },
				  	  livingtype:{
					      required: "居住类型为空"
					  },
					  livedate:{
					      required: "入住日期为空"
					  },
					  receivedate:{
					      required: "收房日期为空"
					  },
					  feestartdate:{
					      required: "缴费开始日期为空"
					  },
					  feeenddate:{
					      required: "缴费截止日期为空"
					  },
					  humancount:{
					      required: "居住人数为空"
					  },

				 }
			});
			
			
			//添加客户房间弹窗
			$("div#CustomerHomeDialog").dialog({
				title:"客户房间增加",
				width:950
			});
			
			//拦截增加提交表单
			$("form#CustomerHomeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadCustomerHomeList();  //更新客户房间列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '客户房间操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#CustomerHomeDialog").dialog( "close" );
				$("div#CustomerHomeDialog").dialog( "destroy" );
				$("div#CustomerHomeDialog").html("");
				
			});

			
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#CustomerHomeDialog").dialog( "close" );
				$("div#CustomerHomeDialog").dialog( "destroy" );
				$("div#CustomerHomeDialog").html("");
			});
			
		});
	});

	//===============================修改客户房间处理===============================================================
	
	$("a#CustomerHomeModifyLink").off().on("click",function(){
		//alert(chno);
		if(chno==0){
			BootstrapDialog.show({
	            title: '客户房间信息',
	            message:"请选择要修改的客户房间",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#CustomerHomeDialog").load("customerhome/modify.html",function(){
				
				//验证提交的数据
				$("form#CustomerHomeModifyForm").validate({
					  rules: {
						  customer:{
					  		  required: true,
					  	  },
						  room:{
					  		  required: true,
					  	  },
					  	  livingtype:{
						      required: true
						  },
						  livedate:{
						      required: true
						  },
						  receivedate:{
						      required: true
						  },
						  feestartdate:{
						      required: true
						  },
						  feeenddate:{
						      required: true
						  },
						  humancount:{
						      required: true
						  },
					  },
					  messages:{
						  customerno:{
					  		  required: "客户序号为空"
					  	  },
						  roomno:{
					  		  required: "房间编号为空"
					  	  },
					  	  livingtype:{
						      required: "居住类型为空"
						  },
						  livedate:{
						      required: "入住日期为空"
						  },
						  receivedate:{
						      required: "收房日期为空"
						  },
						  feestartdate:{
						      required: "缴费开始日期为空"
						  },
						  feeenddate:{
						      required: "缴费截止日期为空"
						  },
						  humancount:{
						      required: "居住人数为空"
						  },

					 }
				});
				
				//取得指定的客户房间信息
				$.getJSON("customerhome/get",{chno:chno},function(em){
					if(em){
						$("input[name='chno']").val(chno);
						$("input[name='customer.customerno']").val(em.customer.customerno);
						//$("input[name='customer.cname']").val(em.customer.cname);
						$("input[name='room.roomno']").val(em.room.roomno);
						$("input[name='typeno']").val(em.livingtype.typeno);
						$("input[name='livedate']").val(em.livedate);
						$("input[name='receivedate']").val(em.receivedate);
						$("input[name='feestartdate']").val(em.feestartdate);
						$("input[name='feeenddate']").val(em.feeenddate);
						$("input[name='humancount']").val(em.humancount);
	
					}
				});
				
				
				
				
				//弹出Dialog
				$("div#CustomerHomeDialog" ).dialog({
					title:"客户房间信息修改",
					width:800
				});
				
				//拦截修改提交表单
				$("form#CustomerHomeModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadCustomerHomeList();  //更新客户房间列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '客户房间操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#CustomerHomeDialog").dialog( "close" );
					$("div#CustomerHomeDialog").dialog( "destroy" );
					$("div#CustomerHomeDialog").html("");
					
				});
	
				
				//点击取消按钮，管理弹出窗口
				$("input[value='取消']").off().on("click",function(){
					$("div#CustomerHomeDialog").dialog("close");
					$("div#CustomerHomeDialog").dialog("destroy")
					$("div#CustomerHomeDialog").html("");
				});
				
				
			});
		}
	});	
	
	//===============================删除客户房间处理=====================================

	$("a#CustomerHomeDeleteLink").off().on("click",function(){
		
		if(chno==0){
			BootstrapDialog.show({
	            title: '客户房间操作信息',
	            message:"请选择要删除的客户房间",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此客户房间?', function(result){
	            if(result) {
		            $.post("customerhome/delete",{chno:chno},function(result){
		            	if(result.status=="OK"){
		            		reloadCustomerHomeList(); 
						}
						BootstrapDialog.show({
				            title: '客户房间操作信息',
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

	//================================查看客户房间详细信息===================================

	$("a#CustomerHomeViewLink").off().on("click",function(){
		
		if(chno==0){
			BootstrapDialog.show({
	            title: '客户房间操作信息',
	            message:"请选择要查看的客户房间",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#CustomerHomeDialog").load("customerhome/view.html",function(){
				//alert(chno);
				//取得选择的客户房间
				$.getJSON("customerhome/get",{chno:chno},function(data){
					if(data){
						$("span#chno").html(chno);
						$("span#customerno").html(data.customer.customerno);
						$("span#cname").html(data.customer.cname);
						$("span#roomno").html(data.room.roomno);
						$("span#areano").html(data.room.area.no);
						$("span#buildingno").html(data.room.building.no);
						$("span#typeno").html(data.livingtype.typeno);
						$("span#livedate").html(data.livedate);
						$("span#receivedate").html(data.receivedate);
						$("span#feestartdate").html(data.feestartdate);
						$("span#feeenddate").html(data.feeenddate);
						$("span#humancount").html(data.humancount);
					}
				});
				//弹出Dialog
				$("div#CustomerHomeDialog" ).dialog({
					title:"客户房间详细信息",
					width:800
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#CustomerHomeDialog" ).dialog( "close" );
					$("div#CustomerHomeDialog" ).dialog( "destroy" );
					$("div#CustomerHomeDialog").html("");
				});

			});
			
		}
	});
	
	
	
});