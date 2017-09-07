/**
 * 
 */

function pro_bottom() { 
    var temp = `<div class="fixed-bottom" style="background:#fff;">
            <div class="d-flex justify-content-lg-around pro-footer">
                <div class="flex-grow-shrink">
                	<img src="images/home.png" class="menus"/>
                	<div>店铺</div>
                </div>
                
    			<div class="flex-grow-shrink">
    				<img src="images/collection.png" class="menus"/>
    				<div>收藏</div>
    			</div>
    			
                <div class="flex-grow-shrink">
    				<img src="images/cart.png" class="menus" />
    				<div>购物车</div>
    			</div>
    			
                <div style="flex-grow: 2;flex-shrink: 2;" class="d-flex justify-content-center align-items-center menus-text">
                	加入购物车
                </div>
                
            </div>
	    </div>`
    return temp;
}


function req(params){
	$.ajax({
		url : params.url,
		data :params.data,
		type : params.type,
		success : function(data) {
			if(data.code==1001){
				params.success(data.result);
			}else{
				console.log(data);
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}

