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
    
    <title>My JSP 'myOrder.jsp' starting page</title>
    
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
     img{
       width: 50px;
       height: 50px;
     }
  </style>
  <body>
      
       <table  class="table table-hover" style="text-align: center;">
				
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						
					</tr>
					
					<c:if test="${order.orderItemList!=null }">
					  <c:forEach items="${order.orderItemList }" var="orderItem" varStatus="st">
					     <tr>
							<td width="60px">
								<input type="hidden" name="id" value="22">
								<img src="${pageContext.request.contextPath}/${orderItem.product.image}">
							</td>
							<td>
								${orderItem.product.pname}
							</td>
							<td>
								￥${orderItem.product.shopPrice}
							</td>
							<td class="quantity" width="60">
								${orderItem.count}
							</td>
							<td width="140">
								<span class="subtotal" >￥${orderItem.subtotal}</span>
							</td>
							
						</tr>
					   </c:forEach>
					</c:if>
				  </tbody>
				  <tfoot>
				   <tr>
				      <td colspan="6" >
				      <span style="position: relative;left: 400px">
				                    共计：<span style="color: red;">${order.total }</span>元
				       </span>         
				      
				      </td>
				      
				   </tr>
				  </tfoot>
		</table>
  </body>
</html>
