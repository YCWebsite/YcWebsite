<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_students" data-options="fit:true"></table>
<script type="text/javascript">
	var datagridObj;
	var editRow=undefined;

	datagridObj=$('#show_students').datagrid({
		url:'show_students.action',
		pagination : true, //显示分页栏
		pageNumber : 1, //初始化显示页码
		pageSize :5, //初始化显示记录数
		pageList : [1,2,3,4,5,6,7,8,9,10], //自定义每页显示的记录数
		fitColumns:true,
		loadMsg:'数据加载中...',
		striped:true,
		nowrap:true,
		rownumbers:true,
		remoteSort:false,
		columns:[[ 
					{field:'ck',align:'center',checkbox:true,width:80},
	                {field:'s_name',title:'学生姓名',width:80,align:'center'}, 
	                {field:'s_tel',title:'电话',width:150,align:'center',editor:{type:"text",options:{required:true}}},
	                {field:'s_direction',title:'意向方向',width:150,align:'center'},
	                {field:'s_time',title:'报名时间',width:120,align:'center',formatter:function(value,row,index){  
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
	                {field:'s_status',title:'报名状态',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	                {field:'message',title:'短信',width:100,align:'center',formatter:function(value,row,index){
	                	return '<a href="javascript:sendMessage('+row.s_id+')">发送短信</a>'
	                }}
	    ]],
		toolbar: [{
			iconCls: 'icon-edit',
			text:"修改",
			handler: function(){
				var rows=datagridObj.datagrid('getChecked')[0];
				if(rows==undefined){
					$.messager.show({
						title:"温馨提示",
						msg:"请选中您要修改的数据...",
						timeout:1500,
						showType:'slide'
					});
				}else{
					if(editRow!=undefined){
						datagridObj.datagrid("rejectChanges");
						datagridObj.datagrid("endEdit",editRow);
					}
					var index=datagridObj.datagrid("getRowIndex",rows);
					datagridObj.datagrid("updateRow",{index:index,row:rows});
					datagridObj.datagrid("beginEdit",index);
					editRow=index;
				}
			}
		},'-',{
			iconCls: 'icon-save',
			text:"保存",
			handler: function(){
				datagridObj.datagrid("endEdit",editRow);
				var rows=datagridObj.datagrid("getChanges")[0];
				if(rows==undefined){
					datagridObj.datagrid("rejectChanges");
					datagridObj.datagrid("unselectAll");
					editRow=undefined;
				}else{
					$.post("update_students.action",rows,function(data){
						if(data==true){
							$.messager.show({
								title:"成功提示",
								msg:"信息修改成功!",
								timeout:1500,
								showType:'slide'
							});
							datagridObj.datagrid("rejectChanges");
							datagridObj.datagrid("unselectAll");
							editRow=undefined;
							rows==undefined;
							datagridObj.datagrid("reload");
						}else{
							$.messager.alert('失败提示','信息修改失败!','error');
						}
					},'json');
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
            text: '<select name="choice"><option value="empty">---请选择---</option><option value="有效">有效</option><option value="无效">无效</option></select>'
        },'-',{
			iconCls: 'icon-search',
			text:"查询",
			handler: function(){
				var choice=$("select[name='choice']").val();
				$('#show_students').datagrid({   
	    			url:'search_students.action',
	    		    queryParams:{choice:choice}
	    		});
			}
		}]
	});
	
	function sendMessage(id){
		$.post('sendMessage.action',{id:id},function(data){
			if(data=="1"){
				alert("短信发送成功！");
			}else{
				alert("请检查网络连接！");
			}
		},'json');
	}
</script>
</body>
</html>