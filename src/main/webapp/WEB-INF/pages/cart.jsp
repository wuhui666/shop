<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
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
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<c:if test="${empty orderItemList }">
					   <tr>
						<td colspan="6" style="color:green;font-weight: bolder;">额。。。你还木有订购任何物品</td>
					    </tr>
					</c:if>
					<!-- 总价 -->
					<c:set var="sum" value="0"></c:set>
					
					<c:if test="${orderItemList!=null }">
					 <c:forEach items="${orderItemList }" var="orderItem" varStatus="st">
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
								<span class="subtotal">￥${orderItem.subtotal}</span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/deleteItem?itemid=${orderItem.itemid}" class="delete">删除</a>
							</td>
						  </tr>
					    <c:set var="sum" value="${sum+orderItem.subtotal}"></c:set>
					  </c:forEach>
					</c:if>
				  </tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint">${sum/5}</em>
					商品金额: <strong id="effectivePrice">￥${sum}元</strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/clearCart" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/orderSubmit" id="submit" class="submit">提交订单</a>
				</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
  </body>
 <!--  <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
  <script type="text/javascript">
    $.ajax(function(){
        url:"/shop/showCart";
        type:"POST";
        success:function(result){
           
        };
    
    })
    
  </script> -->
</html>
