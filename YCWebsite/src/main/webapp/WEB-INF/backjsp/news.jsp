<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_news" fit="true"></table>
<div id="update_news"></div>
<div id="newsdlg" class="easyui-dialog" closed="true" buttons="#newsdlg-buttons1" style="width:715px;height:608px;padding:20px 25px;display:none;">
	<form id="newsfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="n_id" value="">
			<div class="fitem">
				<label>标题：</label> 
				<input id="n_title" name="n_title" style="width:300px;">
			</div>
			<br/>
			<div class="fitem">
				<label>发表人 ：</label>
				<input name="n_reportor" id="n_reportor" style="width:284px;"/>
			</div>
			<br/>
			<div class="fitem">
				<label>排序：</label>
				<input name="n_sort" id="n_sort" style="width:300px;"/>
			</div>
			<br/>
			<div class="fitem">
				<label>内容：</label> 
				<div style=" overflow: hidden;">
					<script id="content" name="content" type="text/plain" style="width:640px;height:205px; ">
					
					</script>
				</div>
				<input type="hidden" value="" name="n_content"/>
			</div>
	</form>
</div>
<div id="newsdlg-buttons1">
	<div id="add-button">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#newsdlg').dialog('close')" style="width:90px">取消</a>
	</div>
	<div id="update-button">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#newsdlg').dialog('close')" style="width:90px">取消</a>
	</div>
</div>

<!-- 配置文件-->
<script type="text/javascript" src="utf8-jsp/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="utf8-jsp/ueditor.all.js"></script>

<script type="text/javascript">
var ue=UE.getEditor('content');

var datagridObj;
var editRow=undefined;
var flag;

datagridObj=$('#show_news').datagrid({
	url:'show_news.action',
	pagination : true, //显示分页栏
	pageNumber : 1, //初始化显示页码
	pageSize : 5, //初始化显示记录数
	pageList : [1,2,3,4,5,6,7,8,9,10], //自定义每页显示的记录数
	fitColumns:true,
	loadMsg:'数据加载中...',
	striped:true,
	nowrap:true,
	rownumbers:true,
	remoteSort:false,
	columns:[[ 
				{field:'ck',align:'center',checkbox:true,width:80},
				{field:'n_title',title:'标题',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
				{field:'n_reportor',title:'发布者 ',width:150,align:'center',editor:{type:"text",options:{required:true}}},   
				{field:'n_time',title:'创建时间',width:180,align:'center',formatter:function(value,row,index){  
               	 var date = new Date(value);
                 var year = date.getFullYear().toString();
                 var month = (date.getMonth() + 1);
                 var day = date.getDate().toString(); 
                 var hour=date.getHours().toString();
                 var minutes=date.getMinutes().toString();
                 var seconds=date.getSeconds().toString();
                 if (month < 10) {
                     month = "0" + month;
                 }
                 if (day < 10) {
                     day = "0" + day;
                 }
                 if (hour < 10) {
                	 hour = "0" + hour;
                 }
                 if (minutes < 10) {
                	 minutes = "0" + minutes;
                 }
                 if (seconds < 10) {
                	 seconds = "0" + seconds;
                 }
                 return year + "-" + month + "-" + day +" "+ hour+":"+minutes+":"+seconds
            }},
				{field:'n_sort',title:'排序(数字越大显示越前)',width:80,align:'center',editor:{type:"text",options:{required:true}}},
				{field:'details',title:'操作 ',width:80,align:'center',formatter:function(value,row,index){  
	            	return '<a href="javascript:findDetails('+row.n_id+')">查看详情</a>'
				}}
    ]],
	toolbar: [{
		iconCls: 'icon-add',
		text:"添加",
		handler: function(){
			$('#update-button').css({display:'none'});
			$('#add-button').css({display:'block'});
			$('#newsdlg').dialog('open').dialog('setTitle','添加新闻');
			ue.setContent('');
     		$('#newsfm').form('clear');
		}
	},'-',{
		iconCls: 'icon-remove',
		text:"删除",
		handler: function(){
			flag="删除";
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
						var n_ids="";
						for(var i=0;i<rows.length-1;i++){
							n_ids+=rows[i].n_id+",";
						}
						n_ids+=rows[i].n_id;
						$.post("delete_news.action",{n_id:n_ids},function(data){
							if(data==true){
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
	},'-',{ 
        text:'<input type="text" id="search" name="search" style="width:150px;" />' 
    },'-',{
		iconCls: 'icon-search',
		text:"查询",
		handler: function(){
			var value=$('input[name="search"]').val();
			$('#show_news').datagrid({   
    			url:'search_news.action',
    		    queryParams:{value:value}
    		}); 
		}
	}]
});

	//添加
	$("#newsfm").form({
		url:"add_news.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_news').datagrid('load');
			$('#newsdlg').dialog('close');
	 	}
	}, 'json');
	function add(){
		var tx=ue.getContentTxt();
		var n_content=$.trim(tx);
		$('input[name="n_content"]').val(n_content);
		var n_title=$('#n_title').val();
		var n_reportor=$('#n_reportor').val();
		var n_sort=$('#n_sort').val();
		if(n_title==null || n_title==''){
			$.messager.alert('提示','请填写新闻标题'); 
			return;
		}
		if(n_reportor==null || n_reportor==''){
			$.messager.alert('提示','请填写发表人'); 
			return;
		}
		if(n_sort==null || n_sort==''){
			$.messager.alert('提示','请给新闻进行排序处理'); 
			return;
		}
		if(n_content==null || n_content==''){
			$.messager.alert('提示','请填写新闻内容'); 
			return;
		}
		$("#newsfm").submit();
	}
	
	//修改
	function update(){
		var n_id=datagridObj.datagrid('getChecked')[0].n_id;
		var n_title=$('#n_title').val();
		var n_reportor=$('#n_reportor').val();
		var n_sort=$('#n_sort').val();
		var tx=ue.getContentTxt();
		var n_content=$.trim(tx);
		$.post('update_news.action', {n_id:n_id,n_title:n_title,n_reportor:n_reportor,n_sort:n_sort,n_content:n_content},function(data) {
			if(data==true){
				$.messager.show({title:'提示',msg:'修改成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','修改失败'); 
			}
			$('#show_news').datagrid('load');
			$('#newsdlg').dialog('close');
		}, 'json');
	}
	
	//查看详情
	 function findDetails(id){
		/* $('#update_news').dialog({
			title:'查看详情',
			width:700,
			height:540,
			closed:false,
			cache:false,
			modal:true,
			href:'toUpdateNews.action',
			queryParams:{n_id:id}
		}); */
		$('#add-button').css({display:'none'});
		$('#update-button').css({display:'block'});
		$('#newsdlg').dialog('open').dialog('setTitle','查看详情');
		var n_title=datagridObj.datagrid('getChecked')[0].n_title;
		$('#n_title').val(n_title);
		var n_reportor=datagridObj.datagrid('getChecked')[0].n_reportor;
		$('#n_reportor').val(n_reportor);
		var n_content=datagridObj.datagrid('getChecked')[0].n_content;
		ue.setContent(n_content);
		var n_sort=datagridObj.datagrid('getChecked')[0].n_sort;
		$('#n_sort').val(n_sort);
	}
</script>

</body>
</html>