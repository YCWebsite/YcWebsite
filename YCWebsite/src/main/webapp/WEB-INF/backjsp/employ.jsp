<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
				//		http             ://         localhost         :        8080          /SpringMVC7douban /
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<style>
	
	.label{
		color:red;
	}
	.fitem{
		margin-top:10px;
	}
</style>
<table id="show_employ" fit="true"></table>

<div id="employdlg" class="easyui-dialog"
	style="width:500px;height:400px;padding:10px 20px;" closed="true" buttons="#employdlg-buttons">
	<form id="employfm" method="post" name="employ" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="e_id" value="">
		<div class="fitem">
			<label>职&nbsp;&nbsp;位：&nbsp;</label> 
			<input id="e_position" name="e_position" class="easyui-textbox" style="width:220px;" required="true">
		</div>
		<div class="fitem">
			<label>招聘人数：&nbsp;</label> 
			<input id="e_amount" name="e_amount" class="easyui-textbox" style="width:200px;" required="true">
		</div>
		 <div class="fitem">
			<label>有效时间：&nbsp;</label>
			<input name="e_validtime" id="e_validtime" class="easyui-datebox" style="width:200px;" required="required"/>
		</div>  
		<div class="fitem">
			<label>工资待遇 ：&nbsp;</label>
			<input name="e_salary" id="e_salary" class="easyui-textbox" style="width:200px;" required="required"/>
		</div>
		
		<div class="fitem">
			<label>职位要求说明：</label> 
			<textarea name="e_detail" id="e_detail" required="required" style="width:230px;height:100px;"></textarea>
		</div>
		<div class="fitem">
			<label>简历投递地址：</label>
			<input name="e_addr" id="e_addr" class="easyui-textbox" style="width:230px;" required="required"/>
		</div>
	</form>
</div>
<div id="employdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#employdlg').dialog('close')" style="width:90px">取消</a>
	</div>
<!-- <div id="update_employ"></div> -->
<div id="see_details"></div>
<script type="text/javascript">
var datagridObj;
var editRow=undefined;

datagridObj=$('#show_employ').datagrid({
	url:'show_employ.action',
	pagination : true, //显示分页栏
	pageNumber : 1, //初始化显示页码
	pageSize : 10, //初始化显示记录数
	pageList : [1,2,3,4,5,6,7,8,9,10], //自定义每页显示的记录数
	fitColumns:true,
	loadMsg:'数据加载中...',
	striped:true,
	nowrap:true,
	rownumbers:true,
	remoteSort:false,
	columns:[[ 
				{field:'ck',align:'center',checkbox:true,width:80},
                {field:'e_position',title:'职位',width:100,align:'center'}, 
                {field:'e_amount',title:'招聘人数',width:80,align:'center'},   
                {field:'e_validtime',title:'有效时间',width:150,align:'center',formatter:function(value,row,index){
                	var date=new Date(value);
                	var year=date.getFullYear().toString();
                	var month=(date.getMonth()+1);
                	var day=date.getDate().toString();
                	if(month<10){
                		month="0"+month;
                	}
                	if(day<10){
                		day="0"+day;
                	}
                	return year +"-"+month+"-"+day;
                }},
                {field:'e_salary',title:'薪资待遇 ',width:150,align:'center'},
                {field:'e_addr',title:'简历投递地址 ',width:150,align:'center'},
                {field:'todoa',title:'状态 ',width:30,align:'center',formatter:function(value,row,index){
                	if(value!=null&&value!="有效"){
                		return "有效";
                	}
                }},
                {field:'e_detail',title:'操作 ',width:80,align:'center',formatter:function(value,row,index){
                	if(value!=null&&value!=""){
                		return '<a href="javascript:seeDetails('+row.e_id+')">查看详情</a>';
                	}else{
                		return "无";
                	}
                	
                }},
                
    ]],
	toolbar: [{
		iconCls: 'icon-add',
		text:"添加",
		handler: function(){
			$('#employdlg').dialog('open').dialog('setTitle','发布招聘信息');
     		$('#employfm').form('clear');
     		
		}
	},/* '-',{
		iconCls: 'icon-edit',
		text:"修改",
		handler: function(){
			var rows=datagridObj.datagrid('getChecked');
			if(rows.length<=0){
				$.messager.show({
					title:"温馨提示",
					msg:"请选中您要修改的数据...",
					timeout:1500,
					showType:'slide'
				});
			}else if(rows.length>1){
				$.messager.show({
					title:"温馨提示",
					msg:"请依次进行修改...",
					timeout:1500,
					showType:'slide'
				});
			}else{
				$('#update_employ').dialog({
					title:'修改学员项目',
					width:480,
					height:500,
					cache:false,
					modal:true,
					href:'toUpdateEmploy.action',
					queryParams:{e_id:rows[0].e_id}
				});
			}
		}
	}, */'-',{
		iconCls: 'icon-remove',
		text:"删除",
		handler: function(){
		    var rows=datagridObj.datagrid('getChecked');
			if(rows.length<=0){
				$.messager.show({
					title:"温馨提示",
					msg:"请选中您要删除的数据...",
					timeout:1500,
					showType:'slide'
				});
			}else{
				$.messager.confirm('删除确认', '你确定要删除选中的数据吗?', function(result){
					if (result){
						var e_ids="";
						for(var i=0;i<rows.length-1;i++){
							e_ids+=rows[i].e_id+",";
						}
						e_ids+=rows[i].e_id;
						$.post("delete_employ.action",{e_id:e_ids},function(data){
							if(data!=null){
								$.messager.show({title:'成功提示',msg:'删除成功!',timeout:2000,showType:'slide'});
								datagridObj.datagrid("reload");
							}else{
								$.messager.alert('失败提示','删除失败！','error');
							}
						});
					}
				});
			} 
		}
	},'-',{
		iconCls: 'icon-undo',
		text:"撤销",
		handler: function(){
			datagridObj.datagrid("rejectChanges");
			datagridObj.datagrid("endEdit",editRow);
			datagridObj.datagrid("unselectAll");
			editRow=undefined;
		}
	}]
});
//添加
$("#employfm").form({
	url:"add_employ.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
		}else{
			$.messager.alert('提示','添加失败'); 
		}
		$('#show_employ').datagrid('reload');
		$('#employdlg').dialog('close');
 	}
}, 'json');
function add(){
	$("#employfm").submit();
}

//查看详情
function seeDetails(id){
	$('#see_details').dialog({
		title:'查看详情',
		width:550,
		height:500,
		cache:false,
		model:true,
		href:"toSeeDetails.action",
		queryParams:{e_id:id}
	});
}
</script>