<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<base href="<%=basePath%>">
<title>无悔商城</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
</head>
  
  <body>
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
	<div class="span10 last" style="position: relative;left:550px;top:-50px">
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
	<div class="span24" style="position: relative;top:-40px">
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
   <div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
			  <c:forEach items="${categorylist }" var="category" varStatus="st">
			       <dl>
							<dt>
								<a href="${pageContext.request.contextPath}/category?cid=${category.cid}&pn=1">${category.cname }</a>
							</dt>
							
							<c:forEach items="${category.categorySencondList }" var="secondcategory" varStatus="st">
							        <dd    style="line-height: 33px">
										<a href="${pageContext.request.contextPath}/secondcategory?csid=${secondcategory.csid}&pn=1">${secondcategory.csname }</a>
									</dd>
							</c:forEach>
									
									
				    </dl>
						
			  </c:forEach>
						
			</div>
		</div>
		
		<div id="result" class="result table clearfix" style="margin-bottom: 0px;width: 950px;">
			
			   <%-- <c:forEach items="${pageInfo.list }" var="product" varStatus="st">
			      <div style="display: inline-block;">
					<a href="${pageContext.request.contextPath}/productDetails?pid=${st.current.pid}">
					    <span>
						<img src="${pageContext.request.contextPath}/${product.image }" width="170" height="170"  style="display: inline-block;">
						<br>
						<span style='color:green'>
						 ${product.pname }
						</span>
						 <br>
						<span class="price">
							商城价： <span style="color:red">￥${product.shopPrice }</span>/份
						</span>
						
					</a>
				</div>	
			   </c:forEach> --%>
		<c:forEach items="${pageInfo.list }" var="product" varStatus="st">
			      <div style="display: inline-block;">
					<a href="${pageContext.request.contextPath}/productDetails?pid=${st.current.pid}">
					   <dl>
					   <dd>
					   <img src="${pageContext.request.contextPath}/${product.image }" width="167" height="160"  style="display: inline-block;">
						
						</dd>
						<dd style="color:green;width: 160px;height: 30px">
					    ${product.pname }
						</dd>
						<dd style="width:160px">
					            商城价： <span style="color:red">￥${product.shopPrice }</span>/份
						</dd>
						
						</dl>
					</a>
				</div>	
			   </c:forEach>
			
	    </div>
		<nav aria-label="Page navigation" style="margin-top: -30px" >
		<ul class="pagination" style="position: relative;left:450px;">
		<li><a href="category?cid=${cid}&pn=1" aria-label="Previous"> <span
					aria-hidden="true">首页</span> </a>
			</li>
			<c:if test="${pageInfo.hasPreviousPage==false }">
				<li  class="disabled"><a href="category?cid=${cid}&pn=${pageInfo.prePage}" aria-label="Previous"> <span
					aria-hidden="true" >&laquo;</span> </a>
			</li>
			</c:if>
			<c:if test="${pageInfo.hasPreviousPage}">
				<li><a href="category?cid=${cid}&pn=${pageInfo.prePage}" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span> </a>
			</li>
			</c:if>
			
			<c:forEach items="${pageInfo.navigatepageNums }" var="navigatepageNum">
			   <c:if test="${pageInfo.pageNum==navigatepageNum }">
			   <li class="active"><a href="category?cid=${cid}&pn=${ navigatepageNum}">${
						navigatepageNum}</a></li>
			   </c:if>
			   <c:if test="${pageInfo.pageNum!=navigatepageNum }">
			   <li><a href="category?cid=${cid}&pn=${ navigatepageNum}">${
						navigatepageNum}</a></li>
			   </c:if>
				
			</c:forEach>
			<c:if test="${pageInfo.hasNextPage==false }">
				<li  class="disabled"><a href="category?cid=${cid}&pn=${pageInfo.nextPage}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
				</li>
			</c:if>
			<c:if test="${pageInfo.hasNextPage}">
				<li><a href="category?cid=${cid}&pn=${pageInfo.nextPage}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
				</li>
			</c:if>
			
			
			<li><a href="category?cid=${cid}&pn=${pageInfo.pages}" aria-label="Previous"> <span
					aria-hidden="true">末页</span> </a>
			</li>
		  </ul>
		</nav>
		
		</body>
</html>
