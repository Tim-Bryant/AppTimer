<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<ul id="demoTree"></ul>
</div>
<script>
	layui.use('tree', function(){
		//生成一个模拟树
		  var createTree = function(node, start){
		    node = node || function(){
		      var arr = [];
		      for(var i = 1; i < 10; i++){
		        arr.push({
		          name: i.toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
		        });
		      }
		      return arr;
		    }();
		    start = start || 1;  
		    layui.each(node, function(index, item){  
		      if(start < 10 && index < 9){
		        var child = [
		          {
		            name: (1 + index + start).toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
		          }
		        ];
		        node[index].children = child;
		        createTree(child, index + start + 1);
		      }
		    });
		    return node;
		  };
		
		layui.tree({
			elem : '#demoTree', //传入元素选择器
			//skin: 'shihuang',
			nodes : createTree()
		});
	});
	
</script>