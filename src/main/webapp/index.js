$(function() {
	console.log(JSON.stringify(menu));

});
function initMenu() {
	$.ajax({
		type : "POST",
		url : "wx/create_menu",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(menu), // ����?�������������menuString
		dataType : "json",
		success : function(message) {
			console.log(message)
		},
		error : function(message) {
			console.log(message);
		}
	});
}
function create_menu() {
	initMenu();
}

function create_menu_() {
	$.ajax({
		type : "POST",
		url : "wxCon/create_menu",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			menu : JSON.stringify(menu)
		}, // ����?�������������menuString
		dataType : "json",
		success : function(message) {
			console.log(message)
		},
		error : function(message) {
			console.log(message);
		}
	});
}
var menu = {
	"button" : [ {
		"type" : "view",
		"name" : "������������",
		"url" : "http://ryy-api.xiaokejia.com/public/html/order_list.html"
	}, {
		"type" : "view",
		"name" : "������������",
		"url" : "http://ryy-api.xiaokejia.com/public/html/my_points.html"
	}, {
		"type" : "view",
		"name" : "������������",
		"url" : "http://ryy-api.xiaokejia.com/public/html/my_favourite.html"
	} ]
}

var menuString = '{"button":[{"name":"123��?","sub_button":[{"type":"click","name":"������������","key":"test001"},{"type":"click","name":"������������","key":"test004"},{"type":"click","name":"������������","key":"type005"}]},{"name":"������������","sub_button":[{"type":"view","name":"wiki","url":"https://mp.weixin.qq.com/wiki"},{"type":"scancode_push","name":"��������?","key":"test002","sub_button":[]}]},{"name":"������������","sub_button":[{"type":"view","name":"������","url":"http://zkh.successinfo.com.cn/january/"},{"type":"location_select","name":"������","key":"test003"}]}]}'
function sendmsg(this_) {
	$.post("RabbitMQUtils/send_info", {
		info : $(this_).prev().val()
	}, function(data) {
		console.log(data);
	});
}

function setarr() {
	$.post("wx/test_success", {
		str : "asdfasdf"
	}, function(data) {
		console.log(data);
	})
}

function sendArr() {
	$.ajax({
		url : "wx/test_array",
		data : {
			str : [ "1,2,3,4", "5,6,7,8", "7,8,9,0" ]
		},
		type : "post",
		success : function(data) {
			console.log(data);
		},
	}, "json")
}

function compare(flag) {
	$.get("wx/verification?signature=abc&timestamp=bcd&nonce=cde&echostr=def",
			function(data) {
				console.log(data, typeof data, data.length);
				$("#string_show").empty();
				$("#string_show").append(data);
			});
}

function login() {
	$.post("login/login_in", {
		username : "LG0812",
		password : "123456"
	}, function(data) {
		console.log(data);
	},"json")
}

function show_pass(this_){
	$(this_).prev().attr("type","text");
}