$(function() {
	console.log(JSON.stringify(menu));

});
function initMenu() {
	$.ajax({
		type : "POST",
		url : "wx/create_menu",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(menu), // 或者直接使用menuString
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
			menu:JSON.stringify(menu)
			}, // 或者直接使用menuString
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
		"name" : "我的订单",
		"url" : "http://ryy-api.xiaokejia.com/public/html/order_list.html"
	}, {
		"type" : "view",
		"name" : "我的积分",
		"url" : "http://ryy-api.xiaokejia.com/public/html/my_points.html"
	}, {
		"type" : "view",
		"name" : "我最喜欢",
		"url" : "http://ryy-api.xiaokejia.com/public/html/my_favourite.html"
	} ]
}

var menuString = '{"button":[{"name":"123心","sub_button":[{"type":"click","name":"运营中心","key":"test001"},{"type":"click","name":"认证流程","key":"test004"},{"type":"click","name":"账号安全","key":"type005"}]},{"name":"帮助中心","sub_button":[{"type":"view","name":"wiki","url":"https://mp.weixin.qq.com/wiki"},{"type":"scancode_push","name":"扫一扫","key":"test002","sub_button":[]}]},{"name":"我的账号","sub_button":[{"type":"view","name":"首页","url":"http://zkh.successinfo.com.cn/january/"},{"type":"location_select","name":"群发","key":"test003"}]}]}'
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