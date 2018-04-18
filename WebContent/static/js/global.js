//datagird 自适应
$(window).resize(function () { 
	try{
		$('#dg').datagrid('resize');  
	}catch(e){//解决没有dg情况下报错问题
		
	}
    
});


//layer弹出区域在小屏幕下显示不全问题处理
var winH = window.screen.height;
var arrLayerPart = new Array();
if(winH <=900){
	arrLayerPart =['1100px', '500px'];
}else{
	arrLayerPart =['1200px', '850px'];
}

//格式化时间为"yyyy-MM-dd hh:mm:ss"格式
function dateTimeFun(value, rec, index) {
    if (value == null || value == '') {  
     return '';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现) 
}
//格式化时间为"yyyy-MM"格式
function dateMonthFun(value, rec, index) {
    if (value == null || value == '') {  
     return '';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM"); //扩展的Date的format方法(上述插件实现) 
}

//格式化时间为"yyyy-MM-dd"格式
function dateFun(value, rec, index) {
    if (value == null || value == '') {  
     return '';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现) 
}	
Date.prototype.format = function (format) {  
	 var o = {  
	     "M+": this.getMonth() + 1, // month  
	     "d+": this.getDate(), // day  
	     "h+": this.getHours(), // hour  
	     "m+": this.getMinutes(), // minute  
	     "s+": this.getSeconds(), // second  
	     "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
	     "S": this.getMilliseconds()  
	     // millisecond  
	 }  
	 if (/(y+)/.test(format))  
	     format = format.replace(RegExp.$1, (this.getFullYear() + "")  
	         .substr(4 - RegExp.$1.length));  
	 for (var k in o)  
	     if (new RegExp("(" + k + ")").test(format))  
	         format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
	 return format;  
	}	
//想要展示某列全部信息添加该方法
function formatA(value,row,index){
	if(value == undefined || value == null){
		value = "";
	}
	return '<span data-p1='+index+' class="easyui-tooltip">' + value + '</span>';
}
//详情浮动框方法  des：标题名，width：宽度
function createTooltip(des,width){
	$('#dg').datagrid('getPanel').find('.easyui-tooltip').each(function(){
		//var index = parseInt($(this).attr('data-p1'));
		$(this).tooltip({
			content: $('<div style ="padding:5px;"></div>'),
			onUpdate: function(cc){
				//var row = $('#dg').datagrid('getRows')[index];
				var content = '<div>'+des+'<p>';
				//content += '<span>'+row.msg+'</span>';
				content += '<span>'+$(this).text()+'</span>';
				content += '</p></div>';
				cc.panel({
					width:width,
					content:content
				});
			},
			position:'top'
		});
	});
}
//检测ie的版本，低于9的给予提示 浏览不兼容问题的处理
jQuery.browser={};
(function(){
	jQuery.browser.msie=false;
    jQuery.browser.version=0;

    if(navigator.userAgent.match(/MSIE ([0-9]+)./)){
    	 jQuery.browser.msie=true;
    	 jQuery.browser.version=RegExp.$1;
    	}
  })();
if ($.browser.msie  && parseInt($.browser.version, 10) < 9) {
	parent.layer.alert("你的浏览器版本过低，请升级您的浏览器版本到ie9以上，或更换谷歌浏览器！");
}

function unlockOperator(index){  
	   $('#dg').datagrid('selectRow',index);// 关键在这里  
		var row = $('#dg').datagrid('getSelected'); 
		var status=row.status;
		if(status!=3){
			parent.layer.msg("帐号未锁定，无需解锁!",{icon:1,time:1000});
			return;
		} 
		parent.layer.confirm("确定要解锁吗？",function(){
    	if (row){ 
    		var ctx = $("#ctx").val();
        	$.ajax({  
        	         type:'post',      
        		     url:ctx+'/sysOperator/unlock.do',  
        		     data: {'id':row.id},  
        		     cache:false,  
        		     dataType:'json',  
        		     success:function(data){  
        		    	 if(data.success){
        		    		parent.layer.msg(data.result,{icon:1,time:1000});
							location.reload();
						}else{
							parent.layer.alert(data.result);
						}
        		  	}  
	        	 });    
    			}
		
	});
	
} 




