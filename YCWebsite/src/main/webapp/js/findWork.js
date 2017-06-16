$.post('../show_jobDetails.action',function(data){
	show_JobDetails(data);
},'json');

function show_JobDetails(data){
	$('#show_JobDetails').empty('');
	var str='';
	for(var i=0;i<data.length;i++){
		str+='<li><img src="'+data[i].jd_pic+'" data-original="" alt=""/><div>'+
			'<p>'+data[i].jd_name+'&nbsp;'+data[i].jd_salary+'</p></div></li>';
	}
	$('#show_JobDetails').append(str);
}