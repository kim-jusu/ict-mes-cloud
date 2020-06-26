var webSocket = new WebSocket('ws://localhost/server');
var intervalState = 0;
var userCode = 0;
var interval;
webSocket.onerror = function(event) {
	onError(event)
};
webSocket.onopen = function(event) {
	onOpen(event)
};
webSocket.onmessage = function(event) {
	onMessage(event)
};

function onMessage(event) {
	console.log(event.data);
	if (event.data.substring(0, 5) == 'start') {
		$("#workingUl").empty();
		selectAjax();
	} else if (event.data.substring(0, 4) == 'stop') {
		$("#workingUl").empty();
		selectAjax();
	} else {
		var ary = event.data.split("/");
		if (parseInt(ary[1]) > 1000)
			return;
		$("#coDensity" + ary[0]).text(ary[1] + " ppm");
		if (parseInt(ary[1]) >= 400) {
			$("#image" + ary[0]).css('-webkit-filter',
					'drop-shadow( 5px 5px 5px red )').fadeOut('slow').fadeIn(
					'slow').fadeOut('slow').fadeIn('slow').fadeOut('slow')
					.fadeIn('slow');
			if (intervalState == 0) {
				userCode = ary[0];
				intervalState = 1;
				console.log("intervalState 1~~~~~" + ary[0])
				powerUpAll();
			}
		} else if (intervalState == 1 && parseInt(ary[1]) < 400) {
			if (userCode == ary[0] && intervalState == 1) {
				intervalState = 0;
				powerDownAll();
				console.log("intervalState 0~~~~~" + ary[0])
			}
			if (intervalState == 0
					&& $("#image" + ary[0]).css('-webkit-filter') != 'none') {
				$("#image" + ary[0]).css('-webkit-filter', 'none');

			}
		}
		if (ary[0]<400
				&& $("#image" + ary[0]).css('-webkit-filter') != 'none') {
			$("#image" + ary[0]).css('-webkit-filter', 'none');

		}

	}
}

function powerUpAll() {
	$.ajax({
		type : "post",
		url : url + 'vent/powerUp',
		data : {
			"ventilCode" : 3,
			"power" : 200,
			_method : 'PUT'
		},
		dataType : "text",
		success : function(data) {
			if (data > 0)
				$("#power" + ventilCode).text(
						parseInt($("#power" + ventilCode).text()) + 50);
		},
		error : function() {
			console.log('powerUp실패')
		}
	});
}

function powerDownAll() {
	$.ajax({
		type : "post",
		url : url + 'vent/powerDown',
		data : {
			"ventilCode" : 3,
			"power" : 100,
			_method : 'PUT'
		},
		dataType : "text",
		success : function(data) {
			if (data > 0)
				$("#power" + ventilCode).text(
						parseInt($("#power" + ventilCode).text()) - 50);
		},
		error : function() {
			console.log('powerDown 실패')
		}
	});
}
function speaker() {
	$.ajax({
		type : "post",
		url : url + 'vent/speaker',
		dataType : "text",
		success : function(data) {
			alert("성공")
		},
		error : function() {
			console.log('speaker 실패')
		}
	});
}

function onOpen(event) {
	console.log("연결")
}
function onError(event) {
	console.log(event)
}