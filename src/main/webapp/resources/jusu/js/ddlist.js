$(function(){
	selectList();
})
function selectList(){
	var str='';
	$.ajax({
		type : "get",
		url : url+'dd/listAll',
		dataType : "json",
		success : function(data){
			$.each(data, function(index, item){
				str+='<div class="col-md-3">'
					+'<img style="width:250px;height:230px;cursor:pointer" id="dd'+item.ddCode+'" onclick="ddClick('+item.ddCode+')" src="'+url+'resources/images/dd/dd'+item.ddCode+'.jpg" alt=" "/>'
					+'<div><center><span style="font-weight : 600;">일련번호 : '+item.ddSerialNo+'</span>'
					+'<br><span style="font-weight : 600;">등록일자 : '+item.ddRegistDate+'</span>' 
					+'</center></div></div>';
			})
			
			$("#ddListDiv").append(str);
			
		},error : function(){
			console.log("select ddlist 실패")	        
		}
	})
	
}