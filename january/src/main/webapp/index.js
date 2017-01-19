/**
 * 
 */

$(document)
		.ready(
				function() {
					$.ajaxSettings.async = false;
					var ajax_ = new HttpService();
					ajax_.get(
							"http://zkh.successinfo.com.cn/january/WeixinController/verification?echostr="
									+ "abcdefg", function(data) {
								if (data.code == "1001") {
									console.log(data);
									$("#abc").append(JSON.stringify(data));
								}
							}, function(data) {
								$("#abc").append(JSON.stringify(data));
							});

					 var ajax = new HttpService();
					// $("#abc").append(getUrlArgObject()["code"]);
					 ajax.cGet(
							"http://zkh.successinfo.com.cn/january/WeixinController/getuserinfo?code="
									+ getUrlArgObject()["code"],
							function(data) {
								if (data.code == "1001") {
									console.log(data);
									$("#abc").append(JSON.stringify(data));
								}
							}, function(data) {
								$("#abc").append(JSON.stringify(data));
							});
					ajax
							.get(
									"http://zkh.successinfo.com.cn/january/WeixinController/getJsApiTicket",
									{
										reqUrl : location.href
									},
									function(data) {
										console.log(data);
										$("#abc")
												.append(
														"--------------------------------------------");
										$("#abc").append(JSON.stringify(data));
										$("#abc")
												.append(
														"--------------------------------------------");
										if (data.code == "1001") {
											wx
													.config({
														debug : true,
														appId : data.result.appId, // 必填，公众号的唯一标识
														timestamp : data.result.timestamp, // 必填，生成签名的时间戳
														nonceStr : data.result.nonceStr, // 必填，生成签名的随机串
														signature : data.result.signature,// 必填，签名，见附录1
														jsApiList : [
																'onMenuShareTimeline',
																'onMenuShareAppMessage',
																'onMenuShareQQ',
																'onMenuShareWeibo',
																'onMenuShareQZone' ]
													// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
													});
										}
									});

				});

function getUrlArgObject() {
	var args = {};
	var query = location.search.substring(1);
	var pairs = query.split("&");
	for (var i = 0; i < pairs.length; i++) {
		var pos = pairs[i].indexOf('=');
		if (pos == -1) {
			continue;
		}
		var argname = pairs[i].substring(0, pos);
		var value = pairs[i].substring(pos + 1);
		args[argname] = unescape(value);
	}
	return args;
}