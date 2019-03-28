<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/shop/index"> <img
					src="${pageContext.request.contextPath}/image/shopName.png"
					alt="这是个商城" /> </a>
			</div>

		</div>
		
	<div class="span9">
		<div>
			<img
				src="${pageContext.request.contextPath}/image/r___________renleipic_01/header.jpg"
				width="320" height="50" alt="正品保障" title="正品保障" />
		</div>
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${user==null}">

					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/login-page">登录</a>|</li>

					<li id="headerRegister" class="headerRegister"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/register-page">注册</a>|</li>


				</c:if>
				<c:if test="${user!=null}">
					<li id="headerUsername" class="headerUsername"
						style="display: list-item;"><a>${user.username}</a> |</li>

					<li id="headerLogout" class="headerLogout"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/myOrder">我的订单</a> |</li>
					<li id="headerLogout" class="headerLogout"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/logout">[退出]</a> |</li>
				</c:if>

				<li><a>会员中心</a> |</li>
				<li><a>购物指南</a> |</li>
				<li><a>关于我们</a></li>

			</ul>
		</div>
		<div class="cart">
			<a href="${pageContext.request.contextPath}/toCartPage">购物车</a>
		</div>
		<div class="phone">
			客服热线: <strong>96008/53277764</strong>
		</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
			<li><a href="${pageContext.request.contextPath}/index">首页</a> |</li>
			<c:forEach items="${categorylist }" var="category">
				<li><a
					href="${pageContext.request.contextPath}/category?cid=${category.cid}&pn=1">${category.cname}</a>
					|</li>
			</c:forEach>



		</ul>
	</div>

</div>

