/**
 * 
 */
var _goodsInfo={}
$(function() {

	req({
		url : "/january/goods/details",
		data : {
			goodsId : 1
		},
		type : "POST",
		success : function(data) {
			console.log(data);
			_goodsInfo=data;
			for (var t = 0; t < data.previewPics.length; t++)
				$("#preview-swiper > .swiper-wrapper")
						.append(
								"<div class='swiper-slide'><img class='prev_img' src='"
										+ data.previewPics[t].picturePath
										+ "'/></div>");

			var proSwiper = new Swiper('#preview-swiper', {
				autoplay : 5000,// 可选选项，自动滑动
				pagination : '#preview-pagination',
				paginationClickable : true,
				mousewheelControl : true,
				grabCursor : true
			});
			/* 显示基本信息 */
			/* 默认加载data.goodsSpec[0] */
			$("#preferentialPrice").html(
					"优惠价:¥" + "<span>" + data.goodsSpec[0].preferentialPrice
							+ "</span>");
			$("#retailPrice").html(
					"零售价:¥" + "<span>" + data.goodsSpec[0].retailPrice
							+ "</span>");
			$("#salesVolume").html(
					"已售:" + "<span>" + data.goodsSpec[0].salesVolume
							+ "</span>");
			$("#goodsName").html(data.goodsName);

			/* 显示推荐 */

			if (data.recommendList && data.recommendList.length > 0) {
				$(".recommend").removeClass("d-none");
				for (var t = 0; t < data.recommendList.length; t++) {
					if (t % 3 == 0) {
						$("#recommend-swiper > .swiper-wrapper").append(
								recommend.units());
					}
					var ran = parseInt(t / 3) ;
					console.log(ran)
					$("#recommend-swiper  .swiper-slide").eq(ran).children().eq(0).append(
							recommend.unit(data.recommendList[t]));

				}
				var recSwiper = new Swiper('#recommend-swiper', {});
			}
			

			/* 显示评论:只加载两条 */ 
			if(data.comment && data.comment.length>0){
				$(".comment").removeClass("d-none");
				for(var t=0;t<data.comment.length;t++){
					if(t<2){
						$(".co-body").append(comment.unit(data.comment[t]));
					}else{
						break;
					}
				}
				
			}
			/* 显示详情 */
			$(".goodsDetails").html(data.remark);
		},
		error : function(data) {
			console.log(data);
		}
	});

	$("body").append(pro_bottom());
})
var recommend = {
	units : function() {
		return "<div class='swiper-slide'><div class='d-flex justify-content-start'></div></div>";
	},
	unit : function(data) {
// console.log(data)
		return `<div class="recommend-unit" onclick="recommend.redirect()">
		<img src="${data.goodsLogoPath}"/>
		<div class="recommend-unit-name">${data.goodsName}</div>
		<div class="recommend-unit-name">${data.retailPrice}</div>
		</div>`;
	},
	redirect:function(){
		console.log("redirect");
	}
}
var comment = {
	unit:function(data){
		var pics = comment.commentPics(data.commentPictures);
		return `<div class="comment-unit">
						<div style="line-height: 40px;">
								<img src="${data.userLogo}" class="only-head" />
			                    <span class="only-name">${data.userName}</span>
		                    	<span class="only-date hide" id="only">${data.createDate}</span>
		                </div>
	                    	
                    	<div class="only-comments">
                    	${data.comment}
                    	</div>
                    	<div class="pic-container">
                    		
                    		${pics}
                    		<div style="width:33%;height:0px;"></div>
                    		<div style="width:33%;height:0px;"></div>
                    	</div>
		                
						<div class="only-reply" >
		                    <div class="pure-tarrow"></div>
		                    	回复：${data.shopkeeperReply}
                    	</div>
                </div>`
	},
	commentPics:function(data){
		var str = "";
		for(var t=0;t<data.length;t++){
			str= str + "<img src='"+data[t].filePath+"' class='pic-single'>";
		}
		return str;
	},
	more:function(this_){
		console.log("none",$(".comments-cart"))
		$("#list").empty(),$(".float-bg").removeClass("d-none"),$(".comments-cart").addClass("comment-cart-show");
		$("body").addClass("over-hide");
		for(var t=0;t<_goodsInfo.comment.length;t++){
				$("#list").append(comment.unit(_goodsInfo.comment[t]));
		}
	},
	close:function(this_){
		$("body").removeClass("over-hide");
		$(".comments-cart").removeClass("comment-cart-show"),$(".float-bg").addClass("d-none");
	}
}