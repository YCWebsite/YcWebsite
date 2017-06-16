<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<input class="date" type="date" name="date" placeholder="请输入您要查看的日志日期"/>
	<a  href="javascript:void(0);" onclick="showLog()" name="log" id="log" >下载并查看日志</a>
	<div id="showLogs"></div>
</div>
<script>
	function showLog(){
		var d=$('input[name="date"]').val();
		 
	  $.ajax({
		   type: "POST",
		   url: "showLog.action",
		   data: {da:d},
		   success: function(date){
		     $('#showLogs').append(date);
		   },
		   error:function(data){
			   $('#showLogs').append("未查到您所搜索的文件");
		   }
		});  
	};

</script>