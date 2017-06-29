<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>表单 - 在线演示 - layui</title>
<meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  
  <link rel="stylesheet" href="js/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="css/global.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
  <div class="layui-main">
    <a class="logoText" href="/">
    	<i class="layui-icon" style="font-size: 18px;">&#xe632;</i>&nbsp;Control Center
    </a>
    <ul class="layui-nav" pc>
      <li class="layui-nav-item">
        <a href="/demo/">示例</a>
      </li>
      <li class="layui-nav-item" pc>
        <a href="javascript:;">周边</a>
        <dl class="layui-nav-child">
          <dd><a href="http://layim.layui.com/" target="_blank">即时聊天</a></dd>
          <dd><a href="http://fly.layui.com/jie/8157.html" target="_blank">社区模板</a></dd>
          <dd><a href="http://fly.layui.com/jie/9842.html" target="_blank">Axure组件</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item">
       &nbsp;&nbsp;
      </li>
      <li class="layui-nav-item" mobile>
        <a href="javascript:;">更多</a>
        <dl class="layui-nav-child">
          <dd><a href="http://fly.layui.com/" target="_blank">社区</a></dd>
        </dl>
      </li>
    </ul>
  </div>
</div>

<!-- 头部结束 -->
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      
<ul class="layui-nav layui-nav-tree site-demo-nav">
  <li class="layui-nav-item layui-nav-itemed">
    <a class="javascript:;" href="javascript:;"><i class="layui-icon" style="font-size: 14px;">&#xe60e;</i>&nbsp;功能管理</a>
    <dl class="layui-nav-child">
      <dd class="layui-this">
        <a href="javascript:void(0);"  data-url="timer/list">定时器管理</a>
      </dd>
      <dd class="">
        <a href="javascript:void(0);"  data-url="timer/home">表单集合</a>
      </dd>
      <dd class="">
        <a href="javascript:void(0);" onclick="menuclick('tree.jsp');">树形菜单</a>
      </dd>
      <dd class="">
        <a href="/demo/tab.html">选项卡</a>
      </dd>
      <dd class="">
        <a href="/demo/progress.html">进度条</a>
      </dd>
      <dd class="">
        <a href="/demo/collapse.html">折叠面板</a>
      </dd>
      <dd class="">
        <a href="/demo/table.html">基本表格</a>
      </dd>
      <dd class="">
        <a href="/demo/auxiliar.html">简单辅助元素</a>
      </dd>
    </dl>
  </li>
  <li class="layui-nav-item layui-nav-itemed">
    <a class="javascript:;" href="javascript:;"><i class="layui-icon" style="font-size: 14px;">&#xe631;</i>&nbsp;系统管理</a>
    <dl class="layui-nav-child">
      <dd class="">
        <a href="/demo/layer.html">
          <i class="layui-icon" style="top: 3px;">&#xe638;</i><cite>菜单管理</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/layim.html">
          <i class="layui-icon" style="position: relative; top: 3px;">&#xe63a;</i>
          <cite>即时通讯</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/laydate.html">
          <i class="layui-icon" style="top: 1px;">&#xe637;</i><cite>角色管理</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/laypage.html">
          <i class="layui-icon">&#xe633;</i><cite>多功能分页</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/laytpl.html">
          <i class="layui-icon">&#xe628;</i><cite>模板引擎</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/layedit.html">
          <i class="layui-icon">&#xe639;</i>
          <cite>富文本编辑器</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/upload.html">
          <i class="layui-icon">&#xe62f;</i>
          <cite>文件上传</cite>
        </a>
      </dd>
       <dd class="">
        <a href="/demo/tree.html">
          <i class="layui-icon">&#xe62e;</i>
          <cite>树形菜单</cite>
        </a>
      </dd>
      <dd class="">
        <a href="/demo/util.html">
          <i class="layui-icon">&#xe631;</i>
          <cite>工具块</cite>
        </a>
      </dd>
      <dd class="">
        <a href="/demo/flow.html">
          <i class="layui-icon">&#xe636;</i>
          <cite>流加载</cite>
        </a>
      </dd>
      <dd class="">
        <a href="/demo/code.html">
          <i class="layui-icon" style="top: 1px;">&#xe635;</i>
          <cite>代码修饰器</cite>
        </a>
      </dd>
    </dl>
  </li>
  <li class="layui-nav-item" style="height: 30px; text-align: center"></li>
	</ul>
  </div>
</div>
  <!-- 左侧导航结束 -->
  <div class="layui-body">
    <div id="dynamicContent" class="nav-content" style="overflow:visible;"></div>
  </div>
<!-- 内容栏目的底部 -->  
<div class="layui-footer footer footer-demo">
  <div class="layui-main">
    <p>&copy;2017 <a href="/">TimBryant</a> MIT license</p>
  </div>
</div>

<div class="site-tree-mobile layui-hide">
  <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>
</div>
<script src="js/core/jquery-1.11.0.js" ></script>
<script src="js/layui/layui.js" charset="utf-8"></script>
<script>
  $(function(){
	  menuclick("timer/list"); 
  });
  //按钮点击事件 
  var menuclick=function(url){
	  $("#dynamicContent").load(url,function(arg) {}); 
  };
  
  //注意：导航 依赖 element 模块，否则无法进行功能性操作
  layui.use(['element','layer'], function(){
     var element = layui.element();
     var layer = layui.layer;
     var $ = layui.jquery;
     //自定义的页面跳转
     var level=function(url){
    	 var index = layer.load(3);
    	 $("#dynamicContent").load(url,function(arg) {
    	    setTimeout(function(){
    		 layer.close(index);
    	    },500);
    	 });
     };
     //动态实现跳转
     $("li.layui-nav-item dl.layui-nav-child dd a").on('click',function(){
		  var othis=$(this),url=othis.data("url");
		  level.call(this,url);
	 });
  });
</script>
</body>
</html>