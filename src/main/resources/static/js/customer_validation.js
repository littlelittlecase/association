/**
 * 自定义的验证规则
 */

//验证客户编码
$.validator.addMethod("ccode", function(value,element,params){
   var zippattern = /^\d[a-z]\d[a-z]$/; 
   return this.optional(element)||zippattern.test(value);
	
} ,"非法的客户编码");

//验证身份证号
$.validator.addMethod("cardcode", function(value,element,params){
   var zippattern = /^[0-9]{6}$/; 
   return this.optional(element)||zippattern.test(value);
	
} ,"非法的身份证号");

//验证手机号码
$.validator.addMethod("mobile", function(value,element,params){
	var mobilepattern = /^1\d{10}$/;
	
	return this.optional(element)||mobilepattern.test(value);
	
		
	} ,"非法的手机号码");




