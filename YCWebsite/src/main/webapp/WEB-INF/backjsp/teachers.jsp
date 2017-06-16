<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_teachers" fit="true"></table>
<style>
#showUploadPic{
		display:none;
		margin-left:50px;
		margin-top:0px;
	}
</style>
<div id="update_teachers"></div>
<div id="seeTeachersPic"></div>
<div id="teachersdlg" class="easyui-dialog"
	style="width:600px;height:400px;padding:35px 82px;" closed="true" buttons="#teachersdlg-buttons">
	<form id="teachersfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="t_id" value="">
		<div class="fitem">
			<label>头衔名称：</label> 
			<input id="t_title" name="t_title" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>老师姓名：</label> 
			<input id="t_name" name="t_name" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>职位名称：</label> 
			<input id="t_job" name="t_job" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>座右铭&nbsp&nbsp&nbsp ：</label>
			<input name="t_motto" id="t_motto" class="easyui-textbox" required="true"/>
		</div>
		<br/>
		<div class="fitem">
			<label>技术方向 ：</label>
			<input name="t_skill" id="t_skill" class="easyui-textbox" required="true"/>
		</div>
		<br/>
		<br/>
		<div class="fitem">
			<label>头像：</label> 
			<input id="t_picURL" name="t_picURL" type="file" required="true">
			<div id="showUploadPic">
        		<img name="preview"  src="" style="width:100px;height:100px;"/>
        	</div>
		</div>
		
	</form>
</div>
<div id="teachersdlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#teachersdlg').dialog('close')" style="width:90px">取消</a>
</div>

<script type="text/javascript">
	var datagridObj;
	var editRow=undefined;

	datagridObj=$('#show_teachers').datagrid({
		url:'show_teachers2.action',
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
					{field:'t_title',title:'头衔',width:50,align:'center'}, 
	                {field:'t_name',title:'姓名',width:50,align:'center'}, 
	                {field:'t_job',title:'职位',width:80,align:'center'},   
	                {field:'t_motto',title:'座右铭',width:150,align:'center'}, 
	                {field:'t_skill',title:'技术方向 ',width:200,align:'center'},
	                {field:'t_pic',title:'头像',width:50,height:50,align:'center',formatter:function(value,row,index){
	                	if(value!=null&& value!=""){
							return '<a href="javascript:seeTeachersPic('+row.t_id+')">查看</a>';
						}else{
							return '无图片说明';
						}
	                }}
	    ]],
		toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler: function(){
				$('#teachersdlg').dialog('open').dialog('setTitle','添加教师资料');
         		$('#teachersfm').form('clear');
         		//图片预览
        		$("input[name='t_picURL']").change(function(){
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
					$('#update_teachers').dialog({
						title:'修改教师信息',
						width:480,
						height:500,
						cache:false,
						modal:true,
						href:'toUpdateTeachers.action',
						queryParams:{t_id:rows[0].t_id}
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
							var t_ids="";
							for(var i=0;i<rows.length-1;i++){
								t_ids+=rows[i].t_id+",";
							}
							t_ids+=rows[i].t_id;
							$.post("delete_teachers.action",{t_id:t_ids},function(data){
								if(data=="success"){
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
	$("#teachersfm").form({
		url:"add_teachers.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_teachers').datagrid('load');
			$('#teachersdlg').dialog('close');
	 	}
	}, 'json');
	function seeTeachersPic(id){
		$('#seeTeachersPic').dialog({
			title:'查看原图',
			width:460,
			height:453,
			cache:false,
			modal:true,
			href:'toSeeTeachersPic.action',
			queryParams:{t_id:id}
			
		})
	};
	function add(){
		$("#teachersfm").submit();
	}
</script>
</body>
</html>