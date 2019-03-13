<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form method="post" enctype="multipart/form-data" id="fileUploadId">
	<div id= "upload" class="form-group">
		<div class = "form-group">
		<label for="file">上传图片</label> 
		</div>
		<div class = "form-group">
		<input id="file" name="file" type="file" accept="image/*">
		</div>
		<div class = "form-group">
		<span>描述</span><input class="form-control" name = "note" id = "note" type = "text">
		</div>
		<div class = "form-group">
		<span>URL</span><input class="form-control" name = "url" id = "url" type = "text"/>
		</div>
		<div class = "form-group">
		<button type = "button" class="btn btn-default" onclick = "show()">预览</button>
		</div>
	</div>
</form>
<!-- <button class="btn btn-default" onclick = "add()">添加更多</button> -->
<script type="text/javascript">
	+function(){
		var add = function(){
			console.info("add",$("#upload"))
			$("#upload").append('<hr/>'
					+'<div class = "form-group"><input id="file" name="file" type="file" accept="image/*"></div>'
					+'<div class = "form-group"><span>描述</span><input class="form-control" name = "note" id = "note" type = "text"></div>'
					+'<div class = "form-group"><span>URL</span><input class="form-control" name = "url" id = "url" type = "text"/></div>'
					+'<div class = "form-group"><button type = "button" class="btn btn-default" onclick = "show()">预览</button></div>')
		}
		for(var i = 0; i< 3;i++ ){
			add();
		}
	}()
</script>