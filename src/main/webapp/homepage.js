$(function() {
	var mySwiper = new Swiper('.swiper-container', {
		pagination : '.swiper-pagination',
		direction : 'vertical',
		slidesPerView : 1,
		paginationClickable : true,
		mousewheelControl : true,
		grabCursor : true
	});

	particlesJS.load('particles-canvas', './config/particles-config.json',
			function() {
				console.log('callback - particles.js config loaded');
			});

	$(".setCover").on("mouseenter", function() {
		$(this).children().eq(0).addClass("hasCover-right");
	});
	$(".setCover").on("mouseleave", function() {
		$(this).children().eq(0).removeClass("hasCover-right");
	});
});
