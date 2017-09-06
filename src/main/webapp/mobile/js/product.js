/**
 * 
 */

$(function() {

	$.ajax({
		url : "/january/goods/details",
		data : {
			goodsId : 1
		},
		type : "POST",
		success : function(data) {
			console.log(data);
			for (var t = 0; t < data.result.previewPics.length; t++)
				$(".swiper-container > div").append(
						"<div class='swiper-slide'><img class='prev_img' src='"
								+ data.result.previewPics[t].picturePath
								+ "'/></div>");

			var proSwiper = new Swiper('.swiper-container', {
				autoplay : 5000,// 可选选项，自动滑动
				pagination : '.swiper-pagination',
				paginationClickable : true,
				mousewheelControl : true,
				grabCursor : true
			});
		},
		error : function(data) {
			console.log(data);
		}
	});

	$("body").append(pro_bottom());
})