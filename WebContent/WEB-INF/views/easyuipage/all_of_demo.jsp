<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完成工单</title>
<%@ include file="/WEB-INF/views/common/easyuifile.jsp"%>
<style type="text/css">
.layout-split-north {
	border-bottom: 0;
}

.inputstyle {
	height: 30px;
	width: 100px;
}
</style>
</head>
<body>
	<div id="build-layout" class="easyui-layout"
		style="width:100%;margin: 0px; height: 760px;">
		<div data-options="region:'north',split:true,title:'组合查询',iconCls:'icon-search'"
			style="width:100%; height: 180px;">
			<form name="searchform" method="post" action="" id="searchform">
				<div class="form-row">

					<div class="col" style="width:80px; text-align:center">姓名:</div>
					<div class="col" style="width:170px">
						<input type="text" id="name" name="name"
							class="form-control">
					</div>
					<div class="col" style="width:80px; text-align:center">年龄:</div>
					<div class="col" style="width:170px">
						<input type="text" id="age" name="age"
							class="form-control">
					</div>

					<div class="col" style="width:80px; text-align:center">创建时间:</div>
					<div class="col" style="width:170px">
						<input type="text" id="createTime1" name="createTime1"
							data-options="showSeconds:true"
							class="easyui-datetimebox form-control inputstyle"
							style="height:30px;width:170px;"></input>
					</div>
					<div class="col" style="width:40px;text-align:center">至</div>
					<div class="col" style="width:170px">
						<input type="text" id="createTime2" name="createTime2"
							data-options="showSeconds:true"
							class="easyui-datetimebox form-control inputstyle"
							style="height:30px;width:170px;"></input>
					</div>
					<div class="col" style="width:100px; text-align:center">
						<t:permission id="1051D,1091A,1102A,1108A">
							<a style="height:30px;font-size:12px;" id="submit_search"
								href="javascript:search();" class="btn btn-info " role="button"><i
								class="mjmh-iconfont mjmh-iconfont-search1"></i> &nbsp;查询</a>
						</t:permission>
					</div>
					<div class="col" style="width:100px; text-align:center">
                        <a style ="height:30px;font-size:12px" id="submit_search" href="javascript:void(0)"  class="btn btn-info" role="button"  onclick="doExport()">导出</a>
                    </div>
					<div class="col"></div>
				</div>
			</form>

		</div>
		<div data-options="region:'center'"
			style="border:none;margin-top:10px;">
			<table id="dg">
			</table>
		</div>

	</div>
</body>
<script type="text/javascript">
		function search(){
			$('#dg').datagrid('reload',{
				createTime1: $('#createTime1').datebox('getValue'),
				createTime2:$('#createTime2').datebox('getValue'),
				name:$('#name').val(),
				age:$('#age').val()
			});
		}

        function doExport() {
            $("#searchform").attr("action", "${ctx}/easyuipage/exportDemo.do");
            $("#searchform").submit();
            $("#searchform").attr("action", "");
        }

		//zhujianfeng add
		$('#dg').datagrid({
			fitColumns:false,
			//width: '100%',
	        height: 550,
			nowrap:false,
			singleSelect:true,
			collapsible:true,
			url:'listajax.do',
			method:'post',
			title:'demo信息',
			iconCls:'icon-search',
			pagination:'true',
			rownumbers:true,
			pageSize:10,	
			frozenColumns:[[    
				{field:'createTime',width:170,title:'创建时间',formatter:dateTimeFun}, 
				{field:'name',width:130,title:'姓名'}
	    	]],		
			columns:[[
				{field:'id',hidden:'true'}, 
				{field:'age',width:100,title:'年龄'}
			]]
		});

	//创建开始时间
	$('#createTime1').datetimebox({  
	    required : false,  
	    onShowPanel:function(){  
	        $(this).datetimebox("spinner").timespinner("setValue","0:0:0");  
	    }  
	});	
	//创建结束时间
	$('#createTime2').datetimebox({  
	    required : false,  
	    onShowPanel:function(){  
	        $(this).datetimebox("spinner").timespinner("setValue","0:0:0");  
	    }  
	});
	</script>
</html>
