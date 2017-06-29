<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
      <!-- 网页内容start -->  
		  <div class="app-timer-buttons">
		 	<blockquote class="layui-elem-quote">定时器管理</blockquote>
			<button class="layui-btn  layui-btn-small" data-method="add"><i class="layui-icon">&#xe654;</i>新增</button>
			<button class="layui-btn  layui-btn-small layui-btn-normal" data-method="modify"> <i class="layui-icon">&#xe642;</i>修改</button>
			<button class="layui-btn  layui-btn-small layui-btn-danger" data-method="deleteObj"><i class="layui-icon">&#xe640;</i>删除</button>
			<div class="layui-form">
		  <table class="layui-table">
		    <colgroup>
		      <col width="50">
		      <col width="250">
		      <col width="150">
		      <col width="200">
		      <col width="100">
		      <col>
		    </colgroup>
		    <thead>
		      <tr>
		        <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
		        <th>名称</th>
		        <th>编码</th>
		        <th>时间</th>
		        <th>状态</th>
		        <th>执行URL</th>
		      </tr> 
		    </thead>
		    <tbody class="dynamic-data">
		          <!-- 动态内容填充区域 -->
		    </tbody>
		  </table>
		</div>
			<!-- 分页组件 -->
			<div id="pageDemo"></div>
		</div>
		<!-- 网页内容end -->  
<script>
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
		 
		//动态加载表格数据
		var dy_load=function(pageNum){
			$.post("timer/listdata",{pageNum:pageNum,pageSize:8},
			       function(response) {
					 var tbody=$("tbody.dynamic-data");
					 tbody.html("");
					 var dataList=response["page"]["list"];
			         for(var obj in dataList){
			        	 var trdata=dataList[obj];
			        	 var tr=$('<tr></tr>');
			        	 tr.append('<td><input type="checkbox" name="" lay-skin="primary" value="'+trdata["id"]+'"></td>');
			        	 tr.append('<td>'+trdata["name"]+'</td>');
			        	 tr.append('<td>'+trdata["code"]+'</td>');
			        	 tr.append('<td>'+trdata["optTime"]+'</td>');
			        	 tr.append('<td>'+(trdata["state"]=='1'?'<font color="#5FB878">启用</font>':'禁用')+'</td>');
			        	 tr.append('<td>'+handle_url(trdata["url"])+'</td>');
			        	 tbody.append(tr);
			         }
			         page(pageNum,response.page.totalPage);
			         //重新渲染表格数据
			         form.render();
			  });
			/* $.post("test/obj",{},function(gson){
				console.info(gson);
			}); */
			
		};
	 //URL路径信息处理
	 var handle_url=function(url){
		 if(url && url.length>0){
			 if(url.length>45){
				 url="<a title='"+url+"'>"+url.substring(0,45)+"..."+"</a>";
			 }
			 return url;
		 }else{
			 return "";
		 }
	 };  
		
	  //按钮事件集合
	  var excute_events={
		  add:function(){
			  menuclick("timer/add");
		  },
		  modify:function(){
			  var chks=$(".layui-table tbody input:checkbox:checked");
			  if(chks && chks.length==1){
				  chks.each(function() {
					  var id=$(this).val();
					  console.info("timer/detail/"+id);
					  menuclick("timer/detail/"+id);
				  });
			  }else if(chks && chks.length>1){
				  layer.msg('对不起，只能选择一行记录',function(){});
			  }else{
				  //加不加function 弹出层会有抖动效果去区别
				  layer.msg('请选一行记录');
			  }
						  
		  }, 
		  deleteObj:function(){
			  var chks=$(".layui-table tbody input:checkbox:checked");
			  if(chks && chks.length>0){
				  var ids=""; 
				  chks.each(function() {
					  var delId=$(this).val();
					  ids+=delId+",";
				  });
				  //配置一个透明的询问框
				  layer.confirm('确认要删除吗?', {icon:5}, function(index){
						 layer.close(index);
						 //删除请求
						  $.post("timer/delete",{ids:ids},function(response){
							  if(response && response["message"]){
								  layer.msg(response["message"],
										  {time: 1000}); //2秒关闭（如果不配置，默认是3秒）;
								  dy_load(1);
							  }
						  });
				  });
			  }else{
				  layer.msg('请至少勾选一行记录');
			  }
		  } 
	  };
	  $(".app-timer-buttons .layui-btn.layui-btn-small").on('click',function(){
		  var othis=$(this),method=othis.data("method");
		  excute_events[method]?excute_events[method].call(this,othis):'';
	  });
	  
	  var page=function(curr,pagecount){
		  //分页
		  laypage({
		    cont: 'pageDemo', //分页容器的id
		    curr:curr,
		    pages: pagecount, //总页数
		    skin: '#5FB878', //自定义选中色值
		    skip: true, //开启跳页
		    hash:'pageNo',
		    jump: function(obj, first){
		      if(!first){
		        layer.msg('第'+ obj.curr +'页',{time: 500});
		        dy_load(obj.curr);
		      }
		    }
		  });
	  };
	  
	//页面加载完毕后加载数据
	 $(function(){
		 dy_load(1);
	 });
		 
	});
	
</script>