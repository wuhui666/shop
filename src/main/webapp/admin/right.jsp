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
					<th>分类id</th>
					<th>分类名</th>
					<th>修改</th>
					<th>删除</th>
					<th style="width: 50px">
					     <a href="${pageContext.request.contextPath}/admin-category-addPage">
					        <button type="button" class="btn btn-success btn-sm"
								style="color:white">
								<span class="glyphicon glyphicon-pencil"></span>新增
							</button>
						</a>
					</th>
					
				</tr>
      </thead>
		<tbody>	
				<c:forEach items="${categoryList}" var="category">
					<tr>
						<td>${category.cid}</td>
						<td>${category.cname}</td>
						
						<td   style="width: 50px;margin-left: 0px">
						  <a href="${pageContext.request.contextPath}/admin-category-editPage?cid=${category.cid}">
							<button type="button" class="btn btn-success"
								style="color:white">
								<span class="glyphicon glyphicon-pencil"></span>修改
							</button>
						  </a>
							
					     </td>
					   <td   style="width: 50px;margin-left: 0px">
					   <a href="${pageContext.request.contextPath}/admin-category-delete?cid=${category.cid}">
					        <button class="btn btn-danger" style="color:white;">
								<span class="glyphicon glyphicon-trash"></span>删除
							</button>
						</a>
					   </td>
					</tr>
				</c:forEach>
		</tbody>	
			</table>

  </body>
</html>
