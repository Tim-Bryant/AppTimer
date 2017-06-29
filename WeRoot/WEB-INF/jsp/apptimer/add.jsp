<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<div>
<blockquote class="layui-elem-quote">定时器信息编辑</blockquote>
<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">名称</label>
    <div class="layui-input-inline">
      <input type="hidden" name="id" value='<c:out value="${appTimer.id}" />'  class="layui-input">
      <input type="text" name="name" required  lay-verify="required" value='<c:out value="${appTimer.name}" />' placeholder="请输入名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">编码</label>
    <div class="layui-input-inline">
      <input type="text" name="code" required lay-verify="required" value='<c:out value="${appTimer.code}" />' placeholder="请输入编码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">不可重复使用</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">执行时间</label>
    <div class="layui-input-inline">
      <input type="text" name="optTime" required  lay-verify="required" value='<c:out value="${appTimer.optTime}" />' placeholder="请输入时间表达式" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">是否有效</label>
    <div class="layui-input-block">
      <c:choose> 
		<c:when test="${appTimer.state =='1'}">  
			<input type="checkbox" name="state" lay-skin="switch" checked="checked">
		</c:when>  
		<c:otherwise>  
			<input type="checkbox" name="state" lay-skin="switch">
		</c:otherwise>  
	  </c:choose>  
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">执行URL</label>
    <div class="layui-input-block">
      <input type="text" name="url" required  lay-verify="required" value='<c:out value="${appTimer.url}" />' placeholder="请输入URL" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">描述</label>
    <div class="layui-input-block">
      <textarea name="description" id="textarea-demo" placeholder="请输入内容" class="layui-textarea"><c:out value="${appTimer.description}" /></textarea>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="app_timer_submit">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button type="button" data-method="confirmback" class="layui-btn layui-btn-primary">返回</button>
    </div>
  </div>
</form>
</div>
<script>
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use(['form','layer'], function(){
	   var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
	   var form = layui.form();
	   form.render();
	   //监听提交
	   form.on('submit(app_timer_submit)', function(data){
	     //layer.msg(JSON.stringify(data.field));
	     $.post("timer/save",data.field,
	       function(data) {
	         if(data && data["message"]){
	        	 layer.msg(data["message"]);
	        	 menuclick("timer/list"); 
	         }
	       });
	     
	     return false;
	   }); 
	   //触发事件
	   var active = {
		 confirmback: function(args){
	       //配置一个透明的询问框
		   layer.confirm('是否返回到列表页面?', {icon: 5, title:'操作提示'}, function(index){
				  layer.close(index);
				  menuclick("timer/list"); 
		   });
	     }
	   };
	   
	   $('.layui-input-block .layui-btn').on('click', function(){
		    var othis = $(this), method = othis.data('method');
		    active[method] ? active[method].call(this, othis) : '';
	   });
	   
	});
</script>