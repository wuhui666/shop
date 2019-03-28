<%@page import="com.github.pagehelper.PageInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<th>商品id</th>
					<th>图片</th>
					<th style="text-align: left;">名称</th>
					<th>价格</th>
					<th>热门</th>
					<th>修改</th>
					<th>删除</th>
					<th style="width: 50px">
					     <a href="${pageContext.request.contextPath}/admin-product-addPage">
					        <button type="button" class="btn btn-success btn-sm"
								style="color:white">
								<span class="glyphicon glyphicon-pencil"></span>新增
							</button>
						</a>
					</th>
					
				</tr>
      </thead>
		<tbody>	
		      <!-- category是product,懒得改 -->
				<c:forEach items="${pageInfo.list}" var="category">
					<tr>
						<td>${category.pid}</td>
						<td>
						   <img alt="" src="${pageContext.request.contextPath}/${category.image}"style="width: 50px;height: 50px">
						</td>
						<td  style="text-align: left;">${category.pname}</td>
						<td>${category.shopPrice}</td>
						<td>
						  <c:if test="${category.isHot==1}">
						         是
						  </c:if>
						  <c:if test="${category.isHot==0}">
						         否
						  </c:if>
						</td>
						<td   style="width: 50px;margin-left: 0px">
						  <a href="${pageContext.request.contextPath}/admin-product-editPage?pid=${category.pid}">
							<button type="button" class="btn btn-success"
								style="color:white">
								<span class="glyphicon glyphicon-pencil"></span>修改
							</button>
						  </a>
							
					     </td>
					   <td   style="width: 50px;margin-left: 0px">
					   <a href="${pageContext.request.contextPath}/admin-product-delete?pid=${category.pid}">
					        <button class="btn btn-danger" style="color:white;">
								<span class="glyphicon glyphicon-trash"></span>删除
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
			<li><a href="${pageContext.request.contextPath}/admin-product-select?pn=1" aria-label="Next"> <span aria-hidden="true">首页</span>
				</a></li>
			<c:if test="${pageInfo.hasPreviousPage==true}">
				<li><a href="${pageContext.request.contextPath}/admin-product-select?pn=${pageInfo.prePage }" aria-label="Next"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:if test="${pageInfo.hasPreviousPage!=true}">
				<li class="disabled"><a> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
				<c:forEach items="${pageInfo.navigatepageNums}" var="p" >
				   <c:if test="${pageInfo.pageNum==p}">
				      <li  class="active">
				      <a href="${pageContext.request.contextPath}/admin-product-select?pn=${p }">${p }</a>
				      </li>
				   </c:if>
				   <c:if test="${pageInfo.pageNum!=p}">
				      <li>
				      <a href="${pageContext.request.contextPath}/admin-product-select?pn=${p }">${p }</a>
				      </li>
				   </c:if>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage==true}">
				<li><a href="${pageContext.request.contextPath}/admin-product-select?pn=${pageInfo.nextPage }" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				</c:if>
				<c:if test="${pageInfo.hasNextPage!=true}">
				<li class="disabled">
				<a> <span aria-hidden="true">&raquo;</span>
				</a>
				</li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/admin-product-select?pn=${pageInfo.pages}" aria-label="Next"> <span aria-hidden="true">末页</span>
				</a></li>
				
			</ul>
			</nav>
     </div>
  </body>
  <script type="text/javascript">
        <% int pageNow=((PageInfo)request.getAttribute("pageInfo")).getPageNum();
           int lastPageNum=((PageInfo)request.getAttribute("pageInfo")).getPages();
           long recordcount=((PageInfo)request.getAttribute("pageInfo")).getTotal();
               session.setAttribute("nowPageNum-product", pageNow);
               session.setAttribute("lastPageNum-product", lastPageNum);
               session.setAttribute("recordcount",recordcount);
               %>
  </script>
</html>
