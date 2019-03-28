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
    
    <title>My JSP 'categorySecondAddPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
  </head>
  <style>
    label[for]{
      position: relative;
      top: 35px
    }
    input[type="text"]{
        position: relative;
        left: 80px
    }
     textarea{
        position: relative;
        left: 80px
    }
    
    select{
        position: relative;
        left: 80px
    }
    span{
       color: red;
    }
  </style>
  <body>
    <form action="${pageContext.request.contextPath}/admin-product-edit" method="post"
        style="position: relative;left: 36%" enctype="multipart/form-data" onsubmit="return check()">
       
		<div class="form-group">
			<label for="exampleInputEmail1" >商品名称：</label>
			 <input value="${target.pname }" name="pname" type="text" class="form-control" style="width: 200px;">
			 <span id="pnameSpan"></span>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">市场价格：</label> <input name="marketPrice"
				type="text" class="form-control" style="width: 200px" value="${target.marketPrice }"
				><span id="marketPriceSpan"></span>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">商城价格：</label> <input name="shopPrice"
				type="text" class="form-control" style="width: 200px" value="${target.shopPrice }"
				><span id="shopPriceSpan"></span>
		</div>
		<div class="form-group">
		    <label>是否热门：</label>
		    <c:if test="${target.isHot==1 }"> 
			<label class="radio-inline">
			
			    <input type="radio" name="isHot" id="ishot" value="1" checked="checked">是
			
			</label>
			<label class="radio-inline">
				  <input type="radio" name="isHot" id="inlineRadio2" value="0">否
				</label>
			</c:if>
		     <c:if test="${target.isHot!=1 }">
			     <label class="radio-inline">
				    <input type="radio" name="isHot" id="ishot" value="1">是
				</label>
			   <label class="radio-inline">
				  <input type="radio" name="isHot" id="inlineRadio2" value="0" checked="checked">否
				</label>
			</c:if>
			
			
		</div>
		
		    <div class="form-group ">
		    
			    <label for="exampleInputEmail1">一级分类：</label>
				<select class="form-control" name="cid" style="width: 200px">
					<c:forEach items="${categoryList }" var="category">
					
					  <c:set var="flag" value="true"></c:set>
					  
					  <c:forEach items="${category.categorySencondList }" var="categorySencond">
					     <c:if test="${categorySencond.csid==target.csid }">
					         <c:set var="flag" value="false"></c:set>
					         <c:set var="categorySelected" value="${category}"></c:set>
					         <option value="${category.cid }" selected="selected">${category.cname}</option>
					     </c:if>
					  </c:forEach>
					  <c:if test="${flag==true }">
					    <option value="${category.cid }">${category.cname}</option>
					  </c:if>
					  
					 </c:forEach>
				</select>
		</div>
		
		 <div class="form-group ">
		     
			    <label for="exampleInputEmail1">二级分类：</label>
				<select class="form-control" name="csid" style="width: 200px">
					 <c:forEach items="${categorySelected.categorySencondList}" var="category2" >
						<c:if test="${target.csid==category2.csid}">
						   <option value="${category2.csid }" selected="selected">${category2.csname}</option>
						</c:if>
						<c:if test="${target.csid!=category2.csid}">
						   <option value="${category2.csid }">${category2.csname}</option>
						</c:if>
					 </c:forEach>		     
				 
				</select>
			
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1">商品详情：</label>
			 <textarea name="pdesc" class="form-control" style="width: 200px" 
				>${target.pdesc}</textarea><span id="pdescSpan"></span>
		</div>
		
		<div class="form-group">
			<label>商品图片：<span id="imageSpan"></span></label>
	
			<ul class="list-inline">
			    <li><img src="${pageContext.request.contextPath}/${target.image}" style="width: 60px;height: 60px"/></li>
			    <li><input type="file" name="image" onchange="filechange(this)"></li>
			</ul>
	        
		</div>
		<div class="form-group">
		  <input type="hidden" name="pid"value="${target.pid}"/>
		  <button type="submit" class="btn btn-info" style="width:280px;">确认</button>
		</div>
		
	</form>
  </body>
  <script type="text/javascript">
   //不懂看自己博客（图片预览）
  function filechange(obj){
     var fileReader=new FileReader();
     fileReader.readAsDataURL(obj.files[0]);
     fileReader.onload=function(){
       $("li>img").attr("src",this.result);//结果会存在result属性
     };
  };
  
   function check(){
     var priceReg=/\d/;
     
       if ($("input[name='pname']").val()=="") {
        $("form span").text("");
        $("#pnameSpan").text("不能为空");
		return false;
	   }
	   
      if ($("input[name='marketPrice']").val()=="") {
        $("form span").text("");
        $("#marketPriceSpan").text("不能为空");
	    return false;
	   }
	  
      if (!priceReg.test($("input[name='marketPrice']").val())){
            $("form span").text("");
			$("#marketPriceSpan").text("非法输入");
			return false;
		}
	$("#marketPriceSpan").text("");
	  if ($("input[name='shopPrice']").val()=="") {
	  $("form span").text("");
        $("#shopPriceSpan").text("不能为空");
	    return false;
	   }
	 
	   if (!priceReg.test($("input[name='shopPrice']").val())){
	   $("form span").text("");
			$("#shopPriceSpan").text("非法输入");
			return false;
	  }
      
       
	   if($("textarea").val()=="") {
	   $("form span").text("");
        $("#pdescSpan").text("不能为空");
		return false;
	   }
	   
	   
	   
	   ////说明用户更改了图片，不用非空检查，但要文件格式检查
	  if ($("input[name='image']").val()!=null&&$("input[name='image']").val()!="") {
	  
	    /*  if ($("li>img").attr("src")==null||$("li>img").attr("src")=="") {
			$("form span").text("");
	        $("#imageSpan").text("请选择文件");
		    return false;
		} */
		
		//
	     if ($("input[name='image']").val().match(/([.]([j][p][g]||[p][n][g]))$/)==null) {
	     
	       $("form span").text("");
	       $("#imageSpan").text("only .png或.jpg");
		   return false;
	     }
	     if ($("input[name='image']").files[0].size > (1*1024*1024)) {
		      $("form span").text("");
		      $("#imageSpan").text("文件超过1M！");
	         return false;
        }
	      
	   }
	   
	   //都合法，清空警告
	   $("form span").text("");
	   //console.log(777);
	   return true;
   }

  
   
   
   $("select[name='cid']").change(function(){
       
      fillCategorySecond($("select[name='cid'] option:selected").val());
   
   });
   function fillCategorySecond(cid){
   $.ajax({
	       url:"${pageContext.request.contextPath}/getCategorySecondList",
	       data:'cid='+cid,
	       type:"POST",
	      // contentType: "application/json",//以json方式将参数传至服务端
	       dataType:"json",//以json方式接受服务端结果
	       success:function(re){
	       
	          //append到select菜单
	           $("select[name='csid']").empty();
	          $.each(re.contentMap.categorysecondList,function(i,item){
	             
	             var op=$("<option></option>")
	             .append(item.csname)
	             .attr("value",item.csid);
	             
	             $("select[name='csid']")
	             .append(op);
	            
	          });
	       }
     });
   }
   
  </script>
</html>
