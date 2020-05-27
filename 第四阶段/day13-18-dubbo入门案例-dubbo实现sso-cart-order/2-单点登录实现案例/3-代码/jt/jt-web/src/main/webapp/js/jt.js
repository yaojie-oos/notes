var TT = JT = {
	checkLogin : function(){
		//判断是否为null "" undefinedl
		var _ticket = $.cookie("JT_TICKET");
		var _username = $.cookie("JT_TICKET_USER");
		if(!_ticket || !_username){
			return ;
		}
		//当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
		$.ajax({
			url : "http://sso.jt.com/user/query/" + _ticket+"/"+_username,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					//把json串转化为js对象
					var _data = JSON.parse(data.data);
					var html =_data.username+"，欢迎来到京淘！<a href=\"http://www.jt.com/user/logout.html\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}else{
					alert("当前用户已经在其他服务器登录!!!");
					showMessage("当前用户已经在其他服务器登录!!!");
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});