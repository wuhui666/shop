<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">
  
  </head>
  
  <body>
     <%@ include file="head.jsp"%>
     <div class="container cart">
		<div class="span24">
			<div class="step step1">
				
			</div>
			 <c:forEach items="${orderList}" var="order" varStatus="st">
				<table>
				  <thead>
				        <tr>
				          <td colspan="2" width="50%">订单编号:${order.oid }</td>
				          <td colspan="3">
				          <c:if test="${order.state==1 }">
				             <span style="color: red">未付款</span>
				          </c:if>
				          <c:if test="${order.state==2 }">
				             <span style="color: orange">待发货</span>
				          </c:if>
				          <c:if test="${order.state==3 }">
				             <span style="color: blue">待收货</span>
				            </c:if>
				          <c:if test="${order.state==4 }">
				             <span style="color:green">交易完成</span>
				         </c:if>        
				          </td>
				        </tr>
				  </thead>
				 
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						
					</tr>
					<c:if test="${order.orderItemList==null }">
					   <tr>
						<td colspan="6" style="color:green;font-weight: bolder;">额。。。你还木有订购任何物品</td>
					    </tr>
					</c:if>
					
					<c:if test="${order.orderItemList!=null }">
					  <c:forEach items="${order.orderItemList }" var="orderItem" varStatus="st">
					     <tr>
							<td width="60">
								<input type="hidden" name="id" value="22">
								<img src="${pageContext.request.contextPath}/${orderItem.product.image}">
							</td>
							<td>
								<a target="_blank">${orderItem.product.pname}</a>
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
				      <span style="position: relative;left: 600px">
				                    共计：<span style="color: red;">${order.total }</span>元
				       </span>
				          <c:if test="${order.state==1 }">
				             <a style="position: relative;left: 660px" 
				                href="${pageContext.request.contextPath}/toOrderPage?oid=${order.oid}">
				                <button class="btn btn-info">付款</button>
				             </a>
				             
				             <a style="position: relative;left: 680px" href="${pageContext.request.contextPath}/cancelOrder?oid=${order.oid}">
				                <button class="btn btn-danger">取消订单</button>
				               </a>
				          </c:if>
				          <c:if test="${order.state==2 }">
				             <a style="position: relative;left: 660px" href="${pageContext.request.contextPath}/finishOrder?oid=${order.oid}">
				               <button class="btn btn-info">确认收货</button>
				             </a>
				             <a style="position: relative;left: 680px">
				               <button class="btn btn-danger">退款</button>
				             </a>
				          </c:if>
				          <c:if test="${order.state==3 }">
				             <a style="position: relative;left: 660px" href="${pageContext.request.contextPath}/finishOrder?oid=${order.oid}">
				                <button class="btn btn-info">确认收货</button>
				             </a>
				             <a style="position: relative;left: 680px">
				                 <button class="btn btn-danger">退款</button>
				                  </a>
				            </c:if>
				          <c:if test="${order.state==4 }">
				             <a style="position: relative;left: 660px"><button class="btn btn-info">交易完成</button></a>
				         </c:if>        
				      
				      
				      </td>
				      
				   </tr>
				  </tfoot>
				</table>
			  </c:forEach>
		</div>
	</div>
  </body>
</html>
