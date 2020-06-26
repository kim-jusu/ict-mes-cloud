$(function(){
	$("#modalHeader").html("<span style='font-weight : 600; font-size:20px'>환풍기 제어</span>");
	selectAjax();

		
	
	
	$("#asd").click(function(){
		$.ajax({
			type : "get",
			data:{"ventA":1,"ventB ":1},
			url : url+'vent/test',
			dataType : "json",
			success : function(data){
			},error : function(){        
			}
		})
	})
	$("#asd1").click(function(){
		$.ajax({
			type : "get",
			data:{"ventA":1,"ventB":0},
			url : url+'vent/test',
			dataType : "json",
			success : function(data){
			},error : function(){        
			}
		})
	})
	$("#asd2").click(function(){
		$.ajax({
			type : "get",
			data:{"ventA":0,"ventB":0},
			url : url+'vent/test',
			dataType : "json",
			success : function(data){
			},error : function(){        
			}
		})
	})
	
})

function selectAjax(){
	var str="";
	$.ajax({
		type : "get",
		url : url+'user/workerInfo',
		dataType : "json",
		success : function(data){
			$.each(data, function(index, item){
				if(item.logState==1){
					str+="<li><img id='image"+item.userCode+"' style='width:230px; height:230px; ' src='"+url+"resources/images/profiles/user"+item.userCode+".jpg' alt='User Image'>" +
					"<center><span style='font-weight : 600;'>"+item.userName+"</span></center>"+
					"<img src='"+url+"resources/images/utils/co.png' style='width:7%;height:7%'>  <span style='font-weight:500;color:green' id='coDensity"+item.userCode+"'></span>" +
					"<center><span class='users-list-date'><a href='#' id='a"+item.userCode+"'><i class='fa fa-circle text-success'></i> <span id='online"+item.userCode+"'>Online</span></a></span></center></li>";
					
					
				}else{
					str+="<li><img id='image"+item.userCode+"' style='width:230px; height:230px; -webkit-filter: grayscale(100%); filter: gray;' src='"+url+"resources/images/profiles/user"+item.userCode+".jpg' alt='User Image'>" +
					"<span style='font-weight : 600;display:block'>"+item.userName+"</a>" +
					"<span class='users-list-date' style='display:block'><a href='#' id='a"+item.userCode+"'><i class='fa fa-circle text-red'></i> <span id='offline"+item.userCode+"'>Offline</span></a><br><br><br></span></li>";		

				}
			})
			$("#workingUl").append(str);

		},error : function(){
			console.log("select workerInfo 실패")	        
		}
	})		
}

function powerUp(ventilCode){
	if(parseInt($("#power"+ventilCode).text())<250){
		$.ajax({
			type : "post",
			url : url+'vent/powerUp',
			data : {"ventilCode":ventilCode,"power":$("#power"+ventilCode).text()+50,_method:'PUT'},
			dataType : "text",
			success : function(data){
				if(data>0)
					$("#power"+ventilCode).text(parseInt($("#power"+ventilCode).text())+50);
			},error : function(){
				console.log('powerUp 실패')
			}
		});
	}else{
		swal("최대 세기","","error")
	}
}
function powerDown(ventilCode){
	if(parseInt($("#power"+ventilCode).text())>25){
		
	$.ajax({
		type : "post",
		url : url+'vent/powerDown',
		data : {"ventilCode":ventilCode,"power":$("#power"+ventilCode).text()-50,_method:'PUT'},
		dataType : "text",
		success : function(data){
			if(data>0)
				$("#power"+ventilCode).text(parseInt($("#power"+ventilCode).text())-50);
		},error : function(){
			console.log('powerDown 실패')
		}
	});
	}else{
		swal("최소 세기","","error")
	}
}
			

function ventilatorTable(){
	var str="";
	
	$.ajax({
		type : "get",
		url : url+'vent/selectAll',
		dataType : "json",
		success : function(data){
			$.each(data, function(index, item){
				str += "<div>"+
		         "<center><span style='font-weight : 500; font-size:18px'>환풍기"+item.ventilCode+"</span>"+
		         "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight : 500;font-size:18px' '>[세기 : <span id='power"+item.ventilCode+"'>"+item.power+"</span>&nbsp;&nbsp;&nbsp;" +
		         		"<img style='width:4%; cursor:pointer;' src='"+url+"resources/images/utils/up.png' onclick='powerUp("+item.ventilCode+")'> <img style='width:4%;cursor:pointer;' src='"+url+"resources/images/utils/down.png' onclick='powerDown("+item.ventilCode+")'>]</center></span>"
		         "&nbsp;</div><br>";   
			});
		    $("#modalBody").empty();
	        $("#modalBody").append(str);
		},error : function(){        
		}
	})
}
