/**
 *  小区的前端控制JS
 * 作者：吕淑兰
 * 
 */
$(function(){
	var typeno=0;
	var typename=null;
	var host="http://localhost:8100";
	//设置系统页面标题
	$("span#mainpagetille").html("客户类型管理");
	//显示客户类型列表
	$("table#CustomerTypeGrid").jqGrid({
		url: 'customertype/list/all',
		datatype: "json",
		colModel: [
			{ label: '客户类型序号', name: 'typeno', width: 50 },
			{ label: '客户类型名称', name: 'typename', width: 50 },
		],
		
		caption:"客户类型列表",
		viewrecords: true, //显示总记录数
		autowidth: true,
		height: 300,
		rowNum: 5,
		rowList:[5,10],
		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "no"},
		pager: "#CustomerTypeGridPager",
		multiselect:false,
		onSelectRow:function(ctno){
			no=ctno;
		}
		
	});


	//设置检索参数，更新jQGrid的列表显示
	function reloadCustomerTypeList()
	{
		$("table#CustomerTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,typename:typename}}).trigger("reloadGrid");	
	}
	
	//===========================增加客户类型处理================================================
	
	$("a#CustomerTypeAddLink").off().on("click",function(){
		$("div#CustomerTypeDialog").load("customertype/add.html",function(){
			/*
			//验证提交数据
			$("form#CustomerTypeAddForm").validate({
				rules: {
					name: {
						required: true
					},

				},
				message:{
					name: {
						required: ""
					},
}
		
			});
			*/
		
			
			//拦截增加提交表单
			$("form#CustomerTypeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadCustomerTypeList(); //更新客户类型列表
				}
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
				BootstrapDialog.show({
		            title: '客户类型操作信息',
		            message:result.message,
		            buttons: [{
		                label: '确定',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
				$("div#CustomerTypeDialog" ).dialog( "close" );
				$("div#CustomerTypeDialog" ).dialog( "destroy" );
				$("div#CustomerTypeDialog").html("");
				
			});
			
			//增加客户类型的弹窗
			$("div#CustomerTypeDialog").dialog({
				title:"增加客户类型",
				width:900
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$("div#CustomerTypeDialog" ).dialog( "close" );
				$("div#CustomerTypeDialog" ).dialog( "destroy" );
				$("div#CustomerTypeDialog").html("");
			});
		});
	});
	
	/*
	//===============================修改客户类型处理=============================

	 */
	
	//===============================删除客户类型处理=====================================



	

});




