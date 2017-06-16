<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_projects" fit="true"></table>
<div id="update_projects"></div>
<div id="seePic"></div>
<style>
	#showUploadPic{
		display:none;
		margin-left:100px;
		margin-top:20px;
	}
</style>
<div id="projectsdlg" class="easyui-dialog"
	style="width:520px;height:450px;padding:35px 82px;" closed="true" buttons="#projectsdlg-buttons">
	<form id="projectsfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="p_id" value="">
		<div class="fitem">
			<label>项目名称：</label> 
			<input id="p_name" name="p_name" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>开发者名称：</label> 
			<input id="p_developer" name="p_developer" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>开发时间:</label>
			<input name="p_time" id="p_time" type="date" required="true"/>
		</div>
		<br/>
		<div class="fitem">
			<label>项目发布地址 ：</label>
			<input name="p_addr" id="p_addr" class="easyui-textbox" required="true"/>
		</div>
		<br/>
		<div class="fitem">
			<label>项目图片：</label> 
			<input id="picUrl" name="picUrl" type="file">
			<div id="showUploadPic">
        		<img name="preview" id="preview" src="" style="width:100px;height:100px;"/>
        	</div>
		</div>
	</form>
</div>
<div id="projectsdlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#projectsdlg').dialog('close');cancle()" style="width:90px">取消</a>
</div>

<script type="text/javascript">
	var datagridObj;
	var editRow=undefined;

	datagridObj=$('#show_projects').datagrid({
		url:'show_projects.action',
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
	                {field:'p_name',title:'项目名',width:80,align:'center'}, 
	                {field:'p_developer',title:'开发者',width:80,align:'center'},   
	                {field:'p_time',title:'开发时间',width:120,align:'center',formatter:function(value,row,index){  
	                	 var date = new Date(value);
	                     var year = date.getFullYear().toString();
	                     var month = (date.getMonth() + 1);
	                     var day = date.getDate().toString();  
	                     if (month < 10) {
	                         month = "0" + month;
	                     }
	                     if (day < 10) {
	                         day = "0" + day;
	                     }
	                     return year + "-" + month + "-" + day 
                    }},
	                {field:'p_addr',title:'项目发布地址 ',width:150,align:'center'},
	                {field:'p_pic',title:'项目图片',width:80,height:50,align:'center',formatter:function(value,row,index){
	                	if(value!=null&& value!=""){ 
	                		return '<a href="javascript:seePic('+row.p_id+')">查看</a>';
	                	}else{
	                		alert(value);
	                		return '无';
	                	}
	                }}
	    ]],
		toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler: function(){
				$('#projectsdlg').dialog('open').dialog('setTitle','添加学员项目');
         		$('#projectsfm').form('clear');
         		//图片预览
         		$("input[name='picUrl']").change(function(){
         			$('#showUploadPic').css("display","block");
         			var objUrl = getObjectURL(this.files[0]) ;
         			if (objUrl) {
         				$("img[name='preview']").attr("src", objUrl) ;
         			}
         		}) ;
         		//建立一個可存取到該file的url
         		function getObjectURL(file) {
         			var url = null ; 
         			if (window.createObjectURL!=undefined) { // basic
         				url = window.createObjectURL(file) ;
         			} else if (window.URL!=undefined) { // mozilla(firefox)
         				url = window.URL.createObjectURL(file) ;
         			} else if (window.webkitURL!=undefined) { // webkit or chrome
         				url = window.webkitURL.createObjectURL(file) ;
         			}
         			return url ;
         		}
			}
		},'-',{
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
					$('#update_projects').dialog({
						title:'修改学员项目',
						width:510,
						height:555,
						cache:false,
						modal:true,
						href:'toUpdateProjects.action',
						queryParams:{p_id:rows[0].p_id}
					});
				}
			}
		},'-',{
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
							var p_ids="";
							for(var i=0;i<rows.length-1;i++){
								p_ids+=rows[i].p_id+",";
							}
							p_ids+=rows[i].p_id;
							$.post("delete_projects.action",{p_id:p_ids},function(data){
								if(data==true){
									$.messager.show({title:'成功提示',msg:'删除成功!',timeout:2000,showType:'slide'});
									datagridObj.datagrid("reload");
								}else{
									$.messager.alert('失败提示','删除失败！','error');
								}
							},'json');
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
	$("#projectsfm").form({
		url:"add_projects.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_projects').datagrid('load');
			$('#projectsdlg').dialog('close');
	 	}
	}, 'json');
	function add(){
		if($('#p_time').val()==''||$('#p_time').val()==null){
			$.messager.alert('提示','请输入时间');
			return;
		}
		$("#projectsfm").submit();
	}
	
	//查看图片
	function seePic(id){
		$('#seePic').dialog({
			title:'查看原图',
			width:527,
			height:563,
			cache:false,
			modal:true,
			href:'toSeeProjectsPic.action',
			queryParams:{p_id:id}
		});
	}
	
	function cancle(){
		$('#showUploadPic').empty('');
	}
	
</script>
</body>
</html>