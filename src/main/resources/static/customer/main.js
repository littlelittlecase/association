/**
 * 客户前端主管理JS
 * 模块：baseinfo
 * 业务对象：客户
 * 作者陈思颖
 */

$(function(){
	var customerno=0;
	var typeno=0;
	var typename=null;
	//var customertype.typeno=0;
	var ccode=null;
	var cname=null;
	var cardcode=null;
	var mobile=null;
	var feestartdate=null;
	var feeenddate=null;
	var cstatus=null;
	var host="http://localhost:8100/";
	//设置系统页面标题
	$("span#mainpagetille").html("客户档案管理");
	
	//显示客户列表
	$("table#CustomerGrid").jqGrid({
		url: 'customer/list/condition/page',
		datatype: "json",
		colModel: [
			{ label: '客户类型', name: 'customertype.typename', width: 50 },
			{ label: '客户编码', name: 'ccode', width: 50 },
			{ label: '客户姓名', name: 'cname', width: 70 },
			{ label: '身份证号', name: 'cardcode', width: 70 },
			{ label: '手机号码', name: 'mobile', width: 70},
			{ label: '收费开始日期', name: 'feestartdate', width: 70 },
			{ label: '收费截止日期', name: 'feeenddate', width: 70 }  ,
			{ label: '客户状态', name: 'cstatus', width: 70 } 
		],
		caption:"客户列表",
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
		      id: "customerno"},
		pager: "#CustomerGridPager",
		multiselect:false,
		onSelectRow:function(cno){
			customerno=cno;
			//alert(customerno);
		}
	});
	
	//更新jQGrid的列表显示
	function reloadCustomerList()
	{
		$("table#CustomerGrid").jqGrid('setGridParam',{postData:{typeno:typeno,ccode:ccode,cname:cname,
		feestartdate: feestartdate, feeenddate:feeenddate}}).trigger("reloadGrid");
		
	};
	
	//定义选中客户类型单选按钮更新事件的处理
	$("input[name='typeno']").off().on("change",function(){
		typeno=$("input[name='typeno']:checked").val();
		reloadCustomerList();
	});
	
	//定义输入客户名称更新事件的处理
	$("input#CnameSelection").off().on("change",function(){
		cname=$("input#CnameSelection").val();
		reloadCustomerList();
	});
	
	//定义输入客户编码更新事件的处理
	$("input#CcodeSelection").off().on("change",function(){
		ccode=$("input#CcodeSelection").val();
		reloadCustomerList();
	});
	
	//定义输入收费日期更新事件的处理
	$("input#feestartdate").off().on("change",function(){
		feestartdate=$("input#feestartdate").val();
		reloadCustomerList();
	});
	$("input#feeenddate").off().on("change",function(){
		feeenddate=$("input#feeenddate").val();
		reloadCustomerList();
	});
	
	
	//点击检索事件处理
	$("a#CustomerSearchButton").on("click",function(){
		typeno=$("input[name='typeno']:checked").val();
		cname=$("input#CnameSelection").val();
		ccode=$("input#CcodeSelection").val();
		
		feestartdate=$("input#feestartdate").val();
		feeenddate=$("input#feeenddate").val();
		
     	reloadCustomerList();
		
	});
	
	
	
	//===========================增加客户处理================================================
	
	
	
	$("a#CustomerAddLink").off().on("click",function(){
		$("div#CustomerDialog").load("customer/add.html",function(){
			
			//验证提交的数据
			$("form#CustomerAddForm").validate({
				  rules: {
				  	  ccode:{
				  		  required: true,
				  		  ccode: true
				  	  },
					  cname:{
					      required: true
					  },
					  cardcode:{
					      required: true,
					      cardcode: true
					  },
					  mobile:{
					      required: true,
					      mobile: true
					  },
					  feestartdate:{
					      required: true
					  },
					  feeenddate:{
					      required: true
					  },
					  cstatus:{
					      required: true
					  },
				  },
				  messages:{
				      ccode:{
				    	  required: "客户编码为空",
				      },
					  cname:{
					      required: "客户姓名为空",
					  },
					  cardcode:{
					      required: "身份证号为空",
					  },
					  mobile:{
					      required: "手机号码为空",
					  },
					  feestartdate:{
					      required: "收费开始日期为空",
					  },
					  feeenddate:{
					      required: "收费截止日期为空",
					  },
					  cstatus:{
					      required: "客户状态为空",
					  },

				 }
			});
			
			
			//添加客户弹窗
			$("div#CustomerDialog").dialog({
				title:"客户增加",
				width:950
			});
			
			//拦截增加提交表单
			$("form#CustomerAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadCustomerList();  //更新客户列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '客户操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#CustomerDialog").dialog( "close" );
				$("div#CustomerDialog").dialog( "destroy" );
				$("div#CustomerDialog").html("");
				
			});

			
			//点击取消按钮，管理弹出窗口
			$("input[value='取消']").off().on("click",function(){
				$("div#CustomerDialog").dialog( "close" );
				$("div#CustomerDialog").dialog( "destroy" );
				$("div#CustomerDialog").html("");
			});
			
		});
	});

	//===============================修改客户处理===============================================================
	
	$("a#CustomerModifyLink").off().on("click",function(){
		//alert(customerno);
		if(customerno==0){
			BootstrapDialog.show({
	            title: '客户信息',
	            message:"请选择要修改的客户",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#CustomerDialog").load("customer/modify.html",function(){
				
				//验证修改的数据
				$("form#CustomerModifyForm").validate({
					  rules: {
					  	  ccode:{
					  		  required: true,
					  		  ccode: true
					  	  },
						  cname:{
						      required: true
						  },
						  cardcode:{
						      required: true,
						      cardcode: true
						  },
						  mobile:{
						      required: true,
						      mobile: true
						  },
						  feestartdate:{
						      required: true
						  },
						  feeenddate:{
						      required: true
						  },
						  cstatus:{
						      required: true
						  },
					  },
					  messages:{
					      ccode:{
					    	  required: "客户编码为空",
					      },
						  cname:{
						      required: "客户姓名为空",
						  },
						  cardcode:{
						      required: "身份证号为空",
						  },
						  mobile:{
						      required: "手机号码为空",
						  },
						  feestartdate:{
						      required: "收费开始日期为空",
						  },
						  feeenddate:{
						      required: "收费截止日期为空",
						  },
						  cstatus:{
						      required: "客户状态为空",
						  },

					 }
				});
				
				//取得指定的员工信息
				$.getJSON("customer/get",{customerno:customerno},function(em){
					if(em){
						$("input[name='customerno']").val(customerno);
						$("input[name='typeno']").val(em.customertype.typeno);
						$("input[name='ccode']").val(em.ccode);
						$("input[name='cname']").val(em.cname);
						$("input[name='cardcode']").val(em.cardcode);
						$("input[name='mobile']").val(em.mobile);
						$("input[name='feestartdate']").val(em.feestartdate);
						$("input[name='feeenddate']").val(em.feeenddate);
						$("input[name='cstatus']").val(em.cstatus);
	
					}
				});
				
				
				
				
				//弹出Dialog
				$("div#CustomerDialog" ).dialog({
					title:"客户信息修改",
					width:800
				});
				
				//拦截修改提交表单
				$("form#CustomerModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						reloadCustomerList();  //更新客户列表
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '客户操作信息',
			            message:result.message,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }]
			        });
					$("div#CustomerDialog").dialog( "close" );
					$("div#CustomerDialog").dialog( "destroy" );
					$("div#CustomerDialog").html("");
					
				});
	
				
				//点击取消按钮，管理弹出窗口
				$("input[value='取消']").off().on("click",function(){
					$("div#CustomerDialog").dialog("close");
					$("div#CustomerDialog").dialog("destroy")
					$("div#CustomerDialog").html("");
				});
				
				
			});
		}
	});	
	
	//===============================删除客户处理=====================================

	$("a#CustomerDeleteLink").off().on("click",function(){
		
		if(customerno==0){
			BootstrapDialog.show({
	            title: '客户操作信息',
	            message:"请选择要删除的客户",
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除此客户?', function(result){
	            if(result) {
		            $.post("customer/delete",{customerno:customerno},function(result){
		            	if(result.status=="OK"){
		            		reloadCustomerList(); 
						}
						BootstrapDialog.show({
				            title: '客户操作信息',
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

	//================================查看客户详细信息===================================

	$("a#CustomerViewLink").off().on("click",function(){
		
		if(customerno==0){
			BootstrapDialog.show({
	            title: '客户操作信息',
	            message:"请选择要查看的客户",
            	buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
		}
		else{
			$("div#CustomerDialog").load("customer/view.html",function(){
				//alert(customerno);
				//取得选择的客户
				$.getJSON("customer/get",{customerno:customerno},function(data){
					if(data){
						$("span#customerno").html(customerno);
						$("span#typename").html(data.customertype.typename);
						$("span#ccode").html(data.ccode);
						$("span#cname").html(data.cname);
						$("span#cardcode").html(data.cardcode);
						$("span#mobile").html(data.mobile);
						$("span#feestartdate").html(data.feestartdate);
						$("span#feeenddate").html(data.feeenddate);
						$("span#cstatus").html(data.cstatus);
					}
				});
				//弹出Dialog
				$("div#CustomerDialog" ).dialog({
					title:"客户详细信息",
					width:800
				});
				//点击取消按钮处理
				$("input[value='关闭']").on("click",function(){
					$("div#CustomerDialog" ).dialog( "close" );
					$("div#CustomerDialog" ).dialog( "destroy" );
					$("div#CustomerDialog").html("");
				});

			});
			
		}
	});
	
	
	
});