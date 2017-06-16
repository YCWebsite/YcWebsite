<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="show_activities" fit="true"></table>
<style>
#showUploadPic{
	display:none;
	margin-left:100px;
	margin-top:20px;
}
	
li{list-style:none}
a{text-decoration:none}
.activities_pic{width:600px;height:400px;position:relative;overflow:hidden}
.activities_pic .pic{width:100px;height:400px}
.activities_pic .pic li{width:580px;height:400px;float:left}

.activities_pic .lr .pre{width:20px;height:50px;float:left;background:none repeat scroll 0px 0px rgba(1, 0, 0, 0.6);text-align:center;line-height:50px;cursor:pointer}
.activities_pic .lr .next{width:20px;height:50px;float:right;background:none repeat scroll 0px 0px rgba(1, 0, 0, 0.6);text-align:center;line-height:50px;cursor:pointer}

.activities_pic .lr{width:530px;height:40px;position:absolute;top:160px;display:none}
.activities_pic .lr a{color:white}

</style>

<div id="showacpic" class="easyui-dialog"
	style="width:650px;height:460px;padding:10px 20px;display:none;" closed="true">
	<div class="update activities_pic">
		
	</div>
</div>
	
<div id="update_activities"></div>
<div id="seePic"></div>
<div id="activitiesdlg" class="easyui-dialog"
	style="width:470px;height:525px;padding:30px 82px;" closed="true" buttons="#activitiesdlg-buttons">
	<form id="activitiesfm" method="post" enctype="multipart/form-data" novalidate>
		<input type="hidden" name="ac_id" value="">
		<div class="fitem">
			<label>活动时间：</label> 
			<input name="ac_time" id="ac_time" type="date" required="true"/>
		</div>
		<br/>
		<div class="fitem">
			<label>活动说明：</label> 
			<textarea id="ac_illus" name="ac_illus" style="height:80px;" required="true"></textarea>
		</div>
		<br/>
		<div class="fitem">
			<label>活动图片:</label>
			<input id="picUrl" name="picUrl" multiple="multiple" type="file">
			<div id="showUploadPic">
        		
        	</div>
		</div>
	</form>
</div>
<div id="activitiesdlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="add()" style="width:90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#activitiesdlg').dialog('close');cancle()" style="width:90px">取消</a>
</div>

<script type="text/javascript">
	var datagridObj;
	var editRow=undefined;

	datagridObj=$('#show_activities').datagrid({
		url:'show_activities.action',
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
	                {field:'ac_time',title:'活动时间',width:120,align:'center',formatter:function(value,row,index){  
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
	                {field:'ac_illus',title:'详细说明',width:150,align:'center'},   
	                {field:'ac_pic',title:'活动图片',width:80,height:50,align:'center',formatter:function(value,row,index){
	                	if(value!=null&& value!=""){
	                		return '<a href="javascript:showacpic()">查看</a>';
	                	}else{
	                		return '无';
	                	}
	                }}
	    ]],
		toolbar: [{
			iconCls: 'icon-add',
			text:"添加",
			handler: function(){
				$('#activitiesdlg').dialog('open').dialog('setTitle','添加活动');
         		$('#activitiesfm').form('clear');
         		//图片预览
         		$("input[name='picUrl']").change(function(){
         			$('#showUploadPic').css("display","block");
         			$('#showUploadPic').empty('');
         			var str='';
         			for(var i=0;i<this.files.length;i++){
         				var objUrl = getObjectURL(this.files[i]) ;
         				if (objUrl) {
         					str+='<img name="preview" id="preview" src="'+objUrl+'" style="width:100px;height:100px;"/>';
             			}
         			}
         			$('#showUploadPic').append(str);
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
					$('#update_activities').dialog({
						title:'修改活动',
						width:500,
						height:460,
						closed:false,
						cache:false,
						modal:true,
						href:'toUpdateActivities.action',
						queryParams:{ac_id:rows[0].ac_id}
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
							var ac_ids="";
							for(var i=0;i<rows.length-1;i++){
								ac_ids+=rows[i].ac_id+",";
							}
							ac_ids+=rows[i].ac_id;
							$.post("delete_activities.action",{ac_id:ac_ids},function(data){
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
	$("#activitiesfm").form({
		url:"add_activities.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'添加成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','添加失败'); 
			}
			$('#show_activities').datagrid('load');
			$('#activitiesdlg').dialog('close');
	 	}
	}, 'json');
	function add(){
		if($('#ac_time').val()==''||$('#ac_time').val()==null){
			$.messager.alert('提示','请输入时间');
			return;
		}
		$("#activitiesfm").submit();
	}
	
	function cancle(){
		$('#showUploadPic').empty('');
	}
	
	//查看图片
	/* function seePic(id){
		$('#seePic').dialog({
			title:'查看原图',
			width:580,
			height:590,
			cache:false,
			modal:true,
			href:'toSeeActivitiesPic.action',
			queryParams:{ac_id:id}
		});
	} */
	
	//查看图片
	function showacpic(){
		var ac_pic=$('#show_activities').datagrid('getSelected').ac_pic;
		var ac_illus=$('#show_activities').datagrid('getSelected').ac_illus;
		var a_pic=ac_pic.split(",");
		$('div.activities_pic').empty('');
		$('div.activities_pic').append('<ul class="anniu"><li class="on"></li></ul><ul class="pic">');
		for(var i=0;i<a_pic.length-1;i++){
			a_pic[i]=a_pic[i].substring(22);
			$('div.activities_pic').prepend(
					'<li><a><img class="lazyload" height="400" width="600" src="../'+a_pic[i]+'"visibility="hidden""></a></li>');
		}
		$('div.activities_pic').append('</ul><ul class="lr"><li class="pre"><a> < </a></li><li class="next"><a> > </a></li></ul>');

		var images =document.getElementsByClassName('lazyload');
	    var pos = 0;
	    var len = images.length;
	    
	    if(len>1){
	    	//鼠标滑过，左右按钮进行显示和隐藏
		    $(".activities_pic").hover(function(){
		      $(".lr").show();
		    },function(){
		      $(".lr").hide();
		    });
	    }
	    
	  //点击左右按钮，图片进行切换效果
	    $(".pre").click(function(){
	    	  images[pos].style.display = 'none';
	          pos--;
	          var i=pos+1;
	          if(pos<0){
	        	  alert('当前为第一张，图片将显示最后一张');
	          }
	         pos=(pos+len)%len;
	         images[pos].style.display = 'inline'; 
	    });
	    $(".next").click(function(){
	      images[pos].style.display = 'none';
	      pos++;
	      var i=pos+1;
	      if(pos>=len){
	    	  alert('当前为最后一张，图片将显示第一张');
	      }
	      pos=pos%len;
	      images[pos].style.display = 'inline';
	    });
		$('#showacpic').dialog('open').dialog('setTitle','图片共'+len+'张');
	}
	
</script>
</body>
</html>