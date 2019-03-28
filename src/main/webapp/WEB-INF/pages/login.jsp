<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="${pageContext.request.contextPath}/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg"
					width="500" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					
					<form id="loginForm" method="post" novalidate="novalidate" action="${pageContext.request.contextPath}/login">
						<table>
							<tbody>
							    <tr><th colspan="2" style="color:red" id="pleaselogin">${pleaselogin }</th></tr>
								<tr>
									<th>用户名/E-mail:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20">
										<c:if test="${nameNotExist!=null }">
										  <span style="color:red">${nameNotExist }</span>
										</c:if>
										<c:if test="${userNotActived!=null }">
										  <span style="color:red">${userNotActived }</span>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off">
										<c:if test="${passwordError!=null }">
										  <span style="color:red">${passwordError }</span>
										</c:if>
									</td>
								</tr>
								
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox"
											id="isRememberUsername" name="isRememberUsername"
											value="true">记住用户名 </label> <label> &nbsp;&nbsp;<a>找回密码</a>
									</label>
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录" >
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！ <a
													href="${pageContext.request.contextPath}/register-page">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势" />
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>招贤纳士</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>服务声明</a> |</li>
				<li><a>广告声明</a>
				</li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
<script type="text/javascript">
  //去掉提示
  $("input[type='submit']").click(){
    <%session.setAttribute("pleaselogin","");%>
    };
    alert("5333");
    isLogin();
    function isLogin(){
    var v=${user};
    alert(v);
     alert(888);
      if (v!=null&&v!=""){
		location.href="${pageContext.request.contextPath}/";
	 }
    };
</script>
</html>
