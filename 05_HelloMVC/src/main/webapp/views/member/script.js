/**
 * 
 */
 console.log("확인");
 $("#password_2").blur(e=>{
	if($(e.target).val() == $("#password_1").val()){
		$("#passwordCheck").css("color","green").text("일치합니다.");
	}
	else{
		$("#passwordCheck").css("color","red").text("일치하지않습니다.");
	}
}) 