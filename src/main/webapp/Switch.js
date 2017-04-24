(function($) {
	var Switch = function(element, options) {
		this.init(element, options);
	}

	Switch.prototype = {
		init : function(element, options) {
			this.$element = $(element);
			this.initialized = true;
			this.setOptions(options);

			this.status = false;

			// this.openColor =
		},
		setOptions : function(options) {
			this.options = $.extend({},
					(this.options || $.fn.mSwitch.defaults), options);

			this.status = options.status;
			this.openColor = options.openColor;
			this.closeColor = options.closeColor;
			this.sliderColor = options.sliderColor;

			this.listen();
			this.render();
		},
		listen : function() {
			// this.$element.off("switch-on");
			this.$element.off("click");
			this.$element.bind("click", $.proxy(this.onItemClick, this));
		},
		distory : function() {

		},
		show : function() {

		},
		onItemClick : function(event) {
			this.status = !this.status;
			console.log("click", event, this.$element);
			if (!this.status) {
				this.$element.removeClass("switch-off").addClass("switch-on");
				this.$element.css({
					"background-color" : this.openColor,
					"border" : "1px solid " + this.openColor,
					"box-shadow" : this.openColor + " 0px 0px 0px 16px inset"
				});
			} else {
				this.$element.removeClass("switch-on").addClass("switch-off");
				this.$element.css({
					"background-color" : this.closeColor,
					"border" : "1px solid " + this.closeColor,
					"box-shadow" : this.closeColor + " 0px 0px 0px 16px inset"
				});
			}

			this.options.statusChange(this.status);
		},
		render : function() {
			this.$element.append("<span class='slider'></span>");
			this.$element.find(".slider").css("background-color",
					this.sliderColor)
			if (this.status) {
				this.$element.css({
					"background-color" : this.openColor,
					"border" : "1px solid " + this.openColor,
					"box-shadow" : this.openColor + " 0px 0px 0px 16px inset"
				});
			} else {
				this.$element.css({
					"background-color" : this.closeColor,
					"border" : "1px solid " + this.closeColor,
					"box-shadow" : this.closeColor + " 0px 0px 0px 16px inset"
				});

			}
		},
		createItem : function() {

		},
		setCurrentStatus : function(status) {

		},
		getCurrentStatus : function() {

		}
	}

	$.fn.mSwitch = function(option) {
		// 这里的this指的是jquery选择出来的所有的dom,each中的this指的是item
		$(this).each(function(index, item) {
			// 遍历使用选择器选择出来的所有jquery对象
			var $this = $(item);
			// 获取数据 mSwitch
			var data = $this.data("mSwitch");
			options = (typeof option !== 'object') ? null : option;
			// 如果data存在(表示已经被初始化过，可以通过option来时设置值);
			// 否则, new一个新的对象, 并将改对象作为data绑定到dom上
			if (!data) {
				data = new Switch(this, options);
				// $element 指init中的this.$element,表示一个jquery对象
				$this = $(data.$element);
				// 将数据绑定到mSwitch上
				$this.data("mSwitch", data);

				// 这里的return相当于continue
				return;
			}

			// 设置options参数
			data.setOptions(option);
		});
	}
}(window.jQuery))

$(function() {
	$(".switch-on").mSwitch({
		status : true,
		openColor : "green",
		closeColor : "yellow",
		sliderColor : "silver",
		statusChange : function(status) {
			console.log(status);
		}
	});
})
