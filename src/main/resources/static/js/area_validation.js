/**
 * 自定义的验证规则
 */

// 匹配integer
$.validator.addMethod("isInteger", function(value, element) {       
     return this.optional(element) || (/^[-\+]?\d+$/.test(value) && parseInt(value)>=0);       
}, "请输入整数值");  



