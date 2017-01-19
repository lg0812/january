var HttpService = function(){
	var TYPE = {
		"post":"POST",
		"get":"GET"
	};
	var _ajax = function(contentType,type,url,data,succ,failed){
		console.log("url:"+url);
		$.ajax({
			type: type,
			url: url,
			dataType: "json",
			contentType: contentType,
			data: data ,
			async: false ,
			success: function(data) {
				if(typeof(succ)=="function"){
					if (data.code == "1003" && data.message == "请去登录") {
						$("#out",parent.document).click();
					}
					return succ(data);
				}else{
					console.log("the method is no a function!");
				}				
			},
			error: function(error) {
				if(typeof(failed)=="function"){
					failed(error);
				}else{
					console.log("the method is no a function!");
				}
			}
		});
	}
	var _upload = function(type,url,data,succ,failed){
		$.ajax({  
          url: url ,  
          type: type,  
          data: data,  
          async: true,  
          cache: false,  
          contentType: false,  
          enctype: 'multipart/form-data',
          processData: false,  
          success: function(data) {
			if(typeof(succ)=="function"){
				return succ(data);
			}else{
				console.log("the method is no a function!");
			}
			
		  },
		  error: function(error) {
			if(typeof(failed)=="function"){
				failed(error);
			}else{
				console.log("the method is no a function!");
			}
		 } 
     	});  
	}
	this.upload = function(url,data,succ,failed){
		return _upload(TYPE.post,url,data,succ,failed)
	}
	this.get = function(url,data,succ,failed){
		return _ajax("application/json",TYPE.get,url,data,succ,failed);
	}
	this.post = function(url,data,succ,failed){
		return _ajax("application/json",TYPE.post,url,JSON.stringify(data),succ,failed);
	}
	this.cGet = function(url,data,succ,failed){
		return _ajax("application/x-www-form-urlencoded",TYPE.get,url,data,succ,failed);
	}
	this.cPost = function(url,data,succ,failed){
		return _ajax("application/x-www-form-urlencoded",TYPE.post,url,data,succ,failed);
	}
}

