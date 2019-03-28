<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>

  </head>
  <body>
   <div class="dtree" >
   <span><a href="javascript:tree.openAll()">全部展开</a>|<a href="javascript:tree.closeAll()">全部关闭</a></span>
   <script type="text/javascript">
      var tree=new dTree("tree");
	  tree.add(0,-1,"某某管理系统");
	  tree.add(1,0,"用户管理");
	  tree.add(11,1,"用户管理","data.html","","right");
	  tree.add(2,0,"一级分类管理");
	  tree.add(21,2,"一级分类管理","${pageContext.request.contextPath}/admin-category-select","","right");
	  tree.add(3,0,"二级分类管理");
	  tree.add(31,3,"二级分类管理","${pageContext.request.contextPath}/admin-categorysencond-select?pn=1","","right");
	  tree.add(4,0,"商品管理");
	  tree.add(41,4,"商品管理","${pageContext.request.contextPath}/admin-product-select?pn=1","","right");
	  tree.add(5,0,"订单管理");
	  tree.add(51,5,"订单管理","${pageContext.request.contextPath}/admin-order-select?pn=1","","right");
	  document.write(tree); 
   </script>
  </div>
 </body>
</html>
