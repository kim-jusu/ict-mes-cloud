$(function(){
	$("#submitBtn").on('click',function(){
		$.ajax( {
	         type : "post", 
	         url : url+'user/login',
	         data: $("#login-form").serialize(),
	         dataType:"text",
	         success : function(data) {
	        	 if(data=='manager'){
	        		location.href=url+"user/goMain"; 
	        	 }
	         },
	         error : function() {

	         console.log("login 실패")
	         }
	      });
	});
	
})