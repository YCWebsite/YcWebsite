//点击报名
function apply(){
	//获取选中的a标签的班级姓名
	$('.main_L_d').css({display:'none'});
	$('.main_L_d2').css({display:'block'});
	$(".kbtextInp2").click(function(){
	      var classes=$(this).parent().find('h5').text();
	      $("input[name='s_direction']").val(classes);
	});
}

function checkName() {
	var s_name = $("input[name='s_name']").val();
	if (s_name!='' && s_name!=null) {
		$("#check1").text('√');
		$("#check1").css({'color':'green'});
		return true;	
	} else {
		$("#check1").text('请输入姓名');
		$("#check1").css({'font-size':'12px'});
		return false;
	}
}
function checkTel() {
	var s_tel = $("input[name='s_tel']").val();
	var reg = /^1[3-8]\d{9}$/;
	if (reg.test(s_tel)) {
		$("#check2").text('√');
		$("#check2").css({'color':'green'});
		return true;	
	} else {
		$("#check2").text('请输入正确的手机号');
		$("#check2").css({'font-size':'12px'});
		return false;
	}
}
function sureApply(){
	if(checkTel() && checkName()){
		return true;
	}else{
		return false;
	}
}
