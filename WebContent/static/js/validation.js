
//表单名称验证
function valdationform(formname){
	return $('#'+formname).form('validate');
}


$.extend($.fn.validatebox.defaults.rules, {
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,13}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
//            return /^((\(\d{2,3}\))|(\d{3}\-))?1\d{10}$/.test(value);
        	return /^(\d{11})$|^((\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$|^(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$)$/.test(value) ;
        },
        message: '手机号码不正确'
    },
          YYYYMM: {
        validator: function (value, param) {
            return  /\d{4}-(?:0[1-9]|1[0-2])/.test(value);
        },
        message: '日期格式不正确'
        },
    loginName1: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    safepass: {
        validator: function (value, param) {
            return password(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一致'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    idcard: {
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/.test(value);
        },
        message:'请输入正确的身份证号码'
    },
    EnNumber: {
        validator: function (value) {
            return /^[A-Za-z0-9]*$/.test(value);
        },
        message: '请输入英数字'
    },
    english: {
        validator: function (value) {
            return /^[A-Za-z]*$/.test(value);
        },
        message: '请输入字母'
    },
    allTime:{
    	validator: function (value) {
        	return /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/.test(value);
	    },
	    message: '请输入正确的时间格式(如：2014-05-06)'
    },
     telephone:{
    	validator: function (value) {
        	return  /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value);
	    },
	    message:'请输入有效的电话号码,如:010-12345678'
    },
    roleName:{
    	validator :function(value){
	    	return  roleNameflg;
    	}
    },
    umitemName:{
    	validator :function(value){
	    	return  umitemNameflg;
    	}
    },
    loginName:{
    	validator :function(value){
	    	return  loginNameflg;
    	}
    }
});

/* 密码由字母和数字组成，至少6位 */
var password = function (value) {
    return !(/^((^[A-Z]*$|^[a-z]*$|^\d*$|^[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)$|^.{0,5}$)$|\s/.test(value));
}
 
$(function(){
  $('input').each(function () {
            if ($(this).attr('required') || $(this).attr('validType'))
                $(this).validatebox();
        })
 });
 //单个input验证
function isvalid(id){
 	return $("#"+id).validatebox("isValid");
 }
 //验证所有div或form下的input
function isvalids(contid){
	var res=true;
	$("#"+contid+" input").each(function () {
                try{
                	$(this).validatebox("validate");
                	if(!$(this).validatebox("isValid")){
                		res=false;
                	};
                }catch(err){
                };
        }
        )
    return res;
};

//取得系统当前时间
function getCurrentDateTime() {
	var d = new Date();
	var str = '';
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	str += vYear;
	str += '-';
	str += (vMon < 10 ? "0" + vMon : vMon);
	str += '-';
	str += (vDay < 10 ? "0" + vDay : vDay);
	str += ' ';
	var h = d.getHours();
	var m = d.getMinutes();
	var se = d.getSeconds();
	str += (h < 10 ? "0" + h : h);
	str += ':';
	str += (m < 10 ? "0" + m : m);
	str += ':';
	str += (se < 10 ? "0" + se : se);
	return str;
}
//取得系统当前日期
function getCurrentDate() {
	var d = new Date();
	var str = '';
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	str += vYear;
	str += '-';
	str += (vMon < 10 ? "0" + vMon : vMon);
	str += '-';
	str += (vDay < 10 ? "0" + vDay : vDay);
	return str;
}