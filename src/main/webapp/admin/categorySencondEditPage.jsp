<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'categorySencondEditPage.jsp' starting page</title>
    
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
  
  <body>
    
    <form action="${pageContext.request.contextPath}/admin-categorySencond-edit" method="post" >

		<div class="form-group">
			<label for="exampleInputEmail1">分类名：</label> 
			<input type="hidden" value="${ categorysencond.csid}" name="csid"></input>
			<input name="csname" type="text" class="form-control" value="${categorysencond.csname }"/>
			
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">所属一级分类：</label>
			<select class="form-control" name="cid" >
				<c:forEach items="${categoryList }" var="category" >
				   <c:if test="${category.cid==categorysencond.cid}">
				     <option value="${category.cid }" selected="selected">${category.cname}</option>
				   </c:if>
					<c:if test="${category.cid!=categorysencond.cid}">
				     <option value="${category.cid }">${category.cname}</option>
				   </c:if>
				 </c:forEach>		     
			 
			</select>
		</div>
		
		<button type="submit" class="btn btn-info">确认</button>
	</form>
    
  </body>
</html>
