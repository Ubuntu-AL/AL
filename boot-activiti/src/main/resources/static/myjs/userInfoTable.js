$(function(){
	//此函数用于发送ajax请求，并获取返回值更新数据表
	$.extend({'createTR':function(setting){
		$.ajax(setting).done(function (response) {
			console.log(response);
			//清空子节点，即清空表格行
			$("#J_TbData").empty();
			//jQuery循环遍历json数据
			$.each (response, function(index){
        		//动态创建一个tr行标签,并且转换成jQuery对象
        		var $trTemp = $("<tr></tr>");
        	
        
        		//往行里面追加 td单元格
        		$trTemp.append("<td>"+ response[index].user.id +"</td>");
        		$trTemp.append("<td>"+ response[index].user.firstName+response[index].user.lastName +"</td>");
        		$trTemp.append("<td>"+ response[index].user.email +"</td>");
        		$trTemp.append("<td>"
        				 	 +"<button id='modify-user' type='button' class='btn btn-info btn-sm'>修改</button>"
        				  	 +"<button id='delete-user' type='button' class='btn btn-danger btn-sm'>删除</button>"
        				  	 +"</td>");
        
        			// $("#J_TbData").append($trTemp);
        		$trTemp.appendTo("#J_TbData");
    		});	
  		});
   	}});
   	
   	
	//调用函数发送请求并创建表格行数据
	var set1 = {
		"async": true,
		"crossDomain": true,
    	"url": "http://localhost:8080/organization/userList",
		"method": "GET"
	}
   	$.createTR(set1);


   $("#J_TbData").on('click', '#delete-user', function(){
   		var userId = $(this).parent().parent("tr").children('td').eq(0).html();
   		var userName = $(this).parent().parent("tr").children('td').eq(1).html();
   		
   		set2 = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/organization/deletUser?userId="+userId,
			"method": "GET",
		}
		
		$.createTR(set2);
   		alert("delete:"+userName);
  });

});


