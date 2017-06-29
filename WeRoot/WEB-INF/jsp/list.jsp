<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<div>
	<button class="layui-btn  layui-btn-small layui-btn-primary"><i class="layui-icon">&#xe608;</i>原始按钮</button>
	<button class="layui-btn  layui-btn-small" onclick="add()">默认按钮</button>
	<button class="layui-btn  layui-btn-small layui-btn-normal">百搭按钮</button>
	<button class="layui-btn  layui-btn-small layui-btn-danger">暖色按钮</button>
	<button class="layui-btn  layui-btn-small layui-btn-disabled">禁用按钮</button>
	<div class="layui-btn-group">
  <button class="layui-btn">增加</button>
  <button class="layui-btn">编辑</button>
  <button class="layui-btn">删除</button>
</div>
      
<div class="layui-btn-group">
  <button class="layui-btn layui-btn-small">
    <i class="layui-icon">&#xe654;</i>
  </button>
  <button class="layui-btn layui-btn-small">
    <i class="layui-icon">&#xe642;</i>
  </button>
  <button class="layui-btn layui-btn-small">
    <i class="layui-icon">&#xe640;</i>
  </button>
  <button class="layui-btn layui-btn-small">
    <i class="layui-icon">&#xe602;</i>
  </button>
</div>
	<hr>
	<div class="layui-form">
  <table class="layui-table">
    <colgroup>
      <col width="50">
      <col width="150">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>序号</th>
        <th>名称</th>
        <th>编码</th>
        <th>时间</th>
        <th>状态</th>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${dataList}" var="app" varStatus="num">
          <tr>
	        <td><input type="checkbox" name="" lay-skin="primary"></td>
	        <td>${num.index+1}</td>
	        <td>${app.name}</td>
	        <td>${app.code}</td>
	        <td>${app.optTime}</td>
	        <td>${app.state}</td>
      	 </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
	<!-- 分页组件 -->
	<div id="pageDemo"></div>
	
</div>
<script>
	var add=function(){
		menuclick("area1.jsp");
	};
	
	layui.use(['laypage','form'], function(){
		  var laypage = layui.laypage,$ = layui.jquery, form = layui.form();
		  form.render();
		 //全选
		  form.on('checkbox(allChoose)', function(data){
		    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
		    child.each(function(index, item){
		      item.checked = data.elem.checked;
		    });
		    form.render('checkbox');
		  });
		  
		  //分页
		  laypage({
		    cont: 'pageDemo', //分页容器的id
		    pages: 100, //总页数
		    skin: '#5FB878' //自定义选中色值
		    //,skip: true //开启跳页
		    ,jump: function(obj, first){
		      if(!first){
		        layer.msg('第'+ obj.curr +'页');
		      }
		    }
		  });
	});
</script>