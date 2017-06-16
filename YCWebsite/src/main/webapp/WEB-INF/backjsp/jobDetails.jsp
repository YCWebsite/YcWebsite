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
		padding-left:30px;
	}
	#showUploadPic{
		display:none;
		margin-left:120px;
		margin-top:20px;
	}
</style>
<table id="show_jobDetails" width="100%" fit="true"></table>
<div id="jobDetailsdlg" class="easyui-dialog"
	style="width:500px;height:470px;padding:10px 20px;" closed="true" buttons="#projectsdlg-buttons">
	<!-- <form id="jobDetailsfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="jd_id" value="">
		<div class="fitem">
			<label>学员姓名：</label> 
			<input id="jd_name" name="jd_name" class="easyui-textbox" style="width:200px;" required="true">
		</div>
		<div class="fitem">
			<label>毕业学校：</label> 
			<input id="jd_school" name="jd_school" class="easyui-textbox" style="width:200px;" required="true">
		</div>
		
		<div class="fitem">
			<label>所学专业：</label> 
			<input id="jd_profession" name="jd_profession" class="easyui-textbox" style="width:200px;" required="true">
		</div>
		<div class="fitem">
			<label>现有薪资：</label>
			<input name="jd_salary" id="jd_salary" placeholder="请输入整数" class="easyui-textbox" style="width:200px;" required="true"/>
		</div>
		<div class="fitem">
			<label>所在公司名称：</label>
			<input name="jd_company" id="jd_company" class="easyui-textbox" style="width:200px;" required="true"/>
		</div>
		<div class="fitem">
			<label>就业时间:</label>
			<input name="jd_emptime" id="jd_emptime" type="date" required="true"/>
		</div>
		
		<div class="fitem">
			<label>学员个人照片：</label> 
			<input id="jd_picURL" name="jd_picURL" type="file">
			<div id="showUploadPic">
        		<img name="preview" id="preview" src="" style="width:100px;height:100px;"/>
        	</div>
		</div>
	</form> -->
</div>
<div id="projectsdlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#jobDetailsdlg').dialog('close');cancle()" id="cancle" style="width:90px">取消</a>
</div>
<div id="update_jobDetails"></div> 
<div id="see_pic"></div>

<script type="text/javascript">

	var datagridObj;
	var editRow=undefined;
	var flag;

	datagridObj=$('#show_jobDetails').datagrid({
		url:'backShow_jobDetails.action',
		fitColumns:false,
		loadMsg:'数据加载中...',
		striped:false,
		nowrap:true,
		pagination:true,//显示分页工具栏
		pageSize:5,//初始化显示记录数
		pageList:[1,2,3,4,5,6,7,8,9,10], 
		rownumbers:true,//显示行数
		rownumbers:true,
		remoteSort:false,
		autoRowHeight:true,
		
		columns:[[ 
					{field:'ck',align:'center',checkbox:true,width:80},
	                {field:'jd_name',title:'学员姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}}, 
	                {field:'jd_school',title:'毕业学校',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
	                {field:'jd_profession',title:'所属专业',width:120,align:'center',editor:{type:"text",options:{required:true}}},
	                {field:'jd_salary',title:'现有薪资',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	                {field:'jd_company',title:'所在公司 ',width:220,align:'center',editor:{type:"text",options:{required:true}}},
	                {field:'jd_emptime',title:'就业时间',width:200,align:'center',editor:{type:"text",options:{required:true}},formatter:function(value,row,index){
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
	                {field:'jd_pic',title:'个人照片',width:200,height:250,align:'center',editor:{type:"text",options:{required:true}},formatter:function(value,row,index){
	                	if(value!=null&& value!=""){ 
	                		return '<a href=javascript:seePic('+row.jd_id+')>查看</a>'
	                	}else{
	                		return '无';
	                	}
	                }}
	    ]],
		 toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler:   function(){
				$('#jobDetailsdlg').dialog('open').dialog('setTitle','添加学员信息');
         		//$('#jobDetailsfm').form('clear');
         		
         		
         		//图片预览
         		$("input[name='jd_picURL']").change(function(){
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
				flag="修改";
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
					$('#update_jobDetails').dialog({
						title:'修改就业详情',
						width:540,
						height:560,
						cache:false,
						modal:true,
						href:'toUpdateJobDetails.action',
						queryParams:{jd_id:rows[0].jd_id}
					});
				}
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
							var jd_ids="";
							for(var i=0;i<rows.length-1;i++){
								jd_ids+=rows[i].jd_id+",";
							}
							jd_ids+=rows[i].jd_id;
							$.post("delete_jobDetails.action",{jd_id:jd_ids},function(data){
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
			text:'<select name="choice"><option value="empty">--请选择--</option><option value="jd_salary">薪资</option><option value="jd_emptime">时间</option>',
		},'-',{
			text:'<input type="text" id="search" name="search" style="width:80px;"/> ~ <input type="text" id="search2" name="search2" style="width:80px;"/>',
		},'-',{
			iconCls: 'icon-search',
			text:"查询",
			handler: function(){
				var choice=$('select[name="choice"]').val();
				var value=$('input[name="search"]').val();
				var value2=$('input[name="search2"]').val();
				$('#show_jobDetails').datagrid({
					url:'search_jobDetails.action',
					queryParams:{value:value,choice:choice,value2:value2}
				});
			}
		}]
	});
	
	$("#jobDetailsfm").form({
		url : "add_jobDetails.action",
		success : function(data) {
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_jobDetails').datagrid('reload');
			$('#jobDetailsdlg').dialog('close');
		}
	},'json');
	//添加
	function add(){
		var money=$('#jd_salary').val();
		if(isNaN(money)){
			$.messager.alert('提示','添加的参数错误'); 
			return;
		}
		$("#jobDetailsfm").submit();
	}
	
	function seePic(id){
		$('#see_pic').dialog({
			title:'查看原图',
			width:400,
			height:400,
			cache:false,
			model:true,
			href:"toSeeJdPic.action",
			queryParams:{jd_id:id}
		});
	};
	
	function cancle(){
		$('#showUploadPic').empty('');
	}
	
	
</script>	
	