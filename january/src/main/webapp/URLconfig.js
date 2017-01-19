var UrlConfig = {
	"host" : "http://zkh.successinfo.com.cn",
	"january" : "january/",
	"messionOperation" : "messionOperation/",
	"WeixinController" : "WeixinController/",
	"Weixin" : {
		"getuserinfo" : "getuserinfo",// params : code
		"getJsApiTicket" : "getJsApiTicket", // params : reqUrl
	},
	"mession" : {
		"create_mession" : "create_mession", // params : mession
		"share_mession" : "share_mession",// params : share
		"click_share" : "click_mession",// params : messionId, openId
		"get_messions" : "get_messions",// 
		"getMessionById" : "getMessionById",// params : messionId
		"getShares" : "getShares",// 
		"getAttends" : "getAttends",// params : messionId
		"attend" : "attend"// params : openId messionId attendName attendPhone
	}
}
