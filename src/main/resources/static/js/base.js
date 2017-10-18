$.ajaxSetup({
	type : 'POST',
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(xhr, status) {
		var sessionStatus = xhr.getResponseHeader('sessionstatus');
		if (sessionStatus == 'timeout') {
			// var top = getTopWinow();
			window.location.href = '../logout.html';
		}
	}
});

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * 
 * @return 当前页面的顶层窗口对象
 */

function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}