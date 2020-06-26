function ddClick(code){
	swal({
        title: "",
        text: "삭제하시겠습니까?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "삭제",
        cancelButtonText: "취소",
        closeOnConfirm: false,
    },
    function(){
    	$.ajax({
    		type : "POST",
    		data : {"ddCode":code,_method:'DELETE'},
    		url : url+'dd/delete',
    		dataType : "text",
    		success : function(data){
    			if(data>0){
    				swal("","삭제 성공","success");
    				$("#ddListDiv").empty();
    				selectList();
    			}
    			else
    				swal("","삭제 실패","error");
    		},error : function(){
    			console.log("DD 삭제 실패")	        
    		}
    	})   
    });
		
}
