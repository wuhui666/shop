<%@page import="com.github.pagehelper.PageInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

  </head>
  <style>
     th{
       text-align: center;
     }
  </style>
  <body>
  
   <table class="table table-hover" style="text-align: center;">
      <thead>
                <tr>
					<th>订单id</th>
					<th>收货人</th>
					<th style="text-align: left;">金额</th>
					<th>状态</th>
					<th>日期</th>
					<th>详情</th>
				</tr>
      </thead>
		<tbody>	
		      <!-- category是order,懒得改 -->
				<c:forEach items="${pageInfo.list}" var="category">
					<tr>
					   <td>${category.oid}</td>
					   <td>${category.name}</td>
					   <td style="text-align: left;">${category.total}</td>
				
				       <td>
				       <c:if test="${category.state==1}">
				         <p style="color: red">未付款</p>
				       </c:if>
				       <c:if test="${category.state==2}">
				         <a href="${pageContext.request.contextPath}/admin-order-delivery?oid=${category.oid}"style="color:orange">
				                            已付款，点击发货
				         </a>
				       </c:if>
				       <c:if test="${category.state==3}">
				         <p style="color: blue">已发货，待收货</p>
				       </c:if>
				       <c:if test="${category.state==4}">
				         <p style="color: green">已收货</p>
				       </c:if>
				       </td>
					   <td >
					     <fmt:formatDate value="${category.ordertime}"  pattern="yyyy-MM-dd HH:mm:ss"/>
					   </td>
					   <td   style="width: 50px;margin-left: 0px">
					   
						   <a href="${pageContext.request.contextPath}/admin-order-detail?oid=${category.oid}">
						        <button class="btn btn-info" style="color:white;">
									<span class="glyphicon glyphicon-eye-open"></span>查看详情
								</button>
							</a>
					   </td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
     <div style="margin-left: 500px">
        <nav aria-label="Page navigation">
			
			<ul class="pagination">
			<li><a href="${pageContext.request.contextPath}/admin-order-select?pn=1" aria-label="Next"> <span aria-hidden="true">首页</span>
				</a></li>
			<c:if test="${pageInfo.hasPreviousPage==true}">
				<li><a href="${pageContext.request.contextPath}/admin-order-select?pn=${pageInfo.prePage }" aria-label="Next"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:if test="${pageInfo.hasPreviousPage!=true}">
				<li class="disabled"><a> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
				<c:forEach items="${pageInfo.navigatepageNums}" var="p" >
				   <c:if test="${pageInfo.pageNum==p}">
				      <li  class="active">
				      <a href="${pageContext.request.contextPath}/admin-order-select?pn=${p }">${p }</a>
				      </li>
				   </c:if>
				   <c:if test="${pageInfo.pageNum!=p}">
				      <li>
				      <a href="${pageContext.request.contextPath}/admin-order-select?pn=${p }">${p }</a>
				      </li>
				   </c:if>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage==true}">
				<li><a href="${pageContext.request.contextPath}/admin-order-select?pn=${pageInfo.nextPage }" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				</c:if>
				<c:if test="${pageInfo.hasNextPage!=true}">
				<li class="disabled">
				<a> <span aria-hidden="true">&raquo;</span>
				</a>
				</li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/admin-order-select?pn=${pageInfo.pages}" aria-label="Next"> <span aria-hidden="true">末页</span>
				</a></li>
				
			</ul>
			</nav>
     </div>
  </body>
  <script type="text/javascript">
      <% int pageNow=((PageInfo)request.getAttribute("pageInfo")).getPageNum();
               session.setAttribute("nowPageNum-order", pageNow);
               %>
  </script>
</html>
