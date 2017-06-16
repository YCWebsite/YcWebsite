<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_courses" fit="true"></table>
<style>
#showUploadPic1{
		display:none;
		margin-left:50px;
		margin-top:0px;
	}
#showUploadPic2{
		display:none;
		margin-left:50px;
		margin-top:0px;
	}
</style>
<div id="update_courses"></div>
<div id="seeCoursesNamePic"></div>
<div id="seeCoursesPic"></div>
<div id="coursesdlg" class="easyui-dialog"
	style="width:600px;height:400px;padding:50px 100px;" closed="true" buttons="#coursesdlg-buttons">
	<form id="coursesfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="cs_id" value="">
		<div class="fitem">
			<label>课程名：</label> 
			<input id="cs_name" name="cs_name" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>课程方向图片：</label> 
			<input id="cs_nameURL" name="cs_nameURL" type="file" required="true">
			<div id="showUploadPic1">
        		<img name="preview1"  src="" style="width:100px;height:100px;"/>
        	</div>
		</div>
		<br/>
		<div class="fitem">
			<label>版本号：</label> 
			<input id="cs_version" name="cs_version" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>文字说明：</label> 
			<input id="cs_text" name="cs_text" class="easyui-textbox" required="true">
		</div>
		<br/>
		<div class="fitem">
			<label>图片说明：</label> 
			<input id="cs_picURL" name="cs_picURL" type="file" required="true">
			<div id="showUploadPic2">
        		<img name="preview2"  src="" style="width:100px;height:100px;"/>
        	</div>
		</div>
	</form>
</div>
<div id="coursesdlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#coursesdlg').dialog('close')" style="width:90px">取消</a>
</div>

<script type="text/javascript">
	var datagridObj;
	var editRow=undefined;

	datagridObj=$('#show_courses').datagrid({
		url:'show_courses2.action',
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
					{field:'cs_name',title:'课程方向名',width:80,align:'center'}, 
					{field:'cs_namePic',title:'课程方向图片',width:50,align:'center',editor:{type:"text",options:{required:true}},formatter:function(value,row,index){
						if(value!=null&& value!=""){
							return '<a href="javascript:seeCoursesNamePic('+row.cs_id+')">查看</a>';
						}else{
							return '无';
						}
					}},
					{field:'cs_version',title:'版本号',width:80,align:'center',editor:{type:"text",options:{required:true}}},   
					{field:'cs_text',title:'文字说明',width:100,align:'center',editor:{type:"text",options:{required:true}}},
					{field:'cs_pic',title:'图片说明',width:50,align:'center',editor:{type:"text",options:{required:true}},formatter:function(value,row,index){
						if(value!=null&& value!=""){
							return '<a href="javascript:seeCoursesPic('+row.cs_id+')">查看</a>';
						}else{
							return '无';
						}
					}}
	    ]],
		toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler: function(){
				$('#coursesdlg').dialog('open').dialog('setTitle','添加课程');
         		$('#coursesfm').form('clear');
         		//图片预览
        		$("input[name='cs_nameURL']").change(function(){
        			$('#showUploadPic1').css("display","block");
        			var objUrl = getObjectURL(this.files[0]) ;
        			if (objUrl) {
        				$("img[name='preview1']").attr("src", objUrl) ;
        			}
        		}) ;
         		
        		$("input[name='cs_picURL']").change(function(){
            		$('#showUploadPic2').css("display","block");
            		var objUrl = getObjectURL(this.files[0]) ;
            		if (objUrl) {
            			$("img[name='preview2']").attr("src", objUrl) ;
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
					$('#update_courses').dialog({
						title:'修改学员项目',
						width:480,
						height:500,
						cache:false,
						modal:true,
						href:'toUpdateCourses.action',
						queryParams:{cs_id:rows[0].cs_id}
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
							var cs_ids="";
							for(var i=0;i<rows.length-1;i++){
								cs_ids+=rows[i].cs_id+",";
							}
							cs_ids+=rows[i].cs_id;
							$.post("delete_courses.action",{cs_id:cs_ids},function(data){
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
	$("#coursesfm").form({
		url:"add_courses.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_courses').datagrid('load');
			$('#coursesdlg').dialog('close');
	 	}
	}, 'json');
	function seeCoursesPic(id){
		$('#seeCoursesPic').dialog({
			title:'查看原图',
			width:460,
			height:453,
			cache:false,
			modal:true,
			href:'toSeeCoursesPic.action',
			queryParams:{cs_id:id}
			
		})
	}
	function seeCoursesNamePic(id){
		$('#seeCoursesNamePic').dialog({
			title:'查看原图',
			width:460,
			height:453,
			cache:false,
			modal:true,
			href:'toSeeCoursesNamePic.action',
			queryParams:{cs_id:id}
			
		})
	}
	function add(){
		$("#coursesfm").submit();
	}
	
</script>
</body>
</html>