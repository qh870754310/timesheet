<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  class="form-horizontal Image-template">
		<input type="hidden" id="isAvailableID" name="isAvailable" value="1" />
		<input type="hidden" id="versionID" name="version" value="0"/>
		<input type="hidden" id="nameID" name="name">
		<input type="hidden" id="realNameID" name="realName">
		<input type="hidden" id="pathID" name="img">
		<input type="hidden" id="suffixationID" name="suffixation">
	 <div class="col-sm-6 col-md-4" id = "image">
	   <div class="thumbnail">
	   <div style="width: 160px;height: 160px;">
	    <img id = "img" class="img-responsive img"  alt="Responsive image" src="http://a0.att.hudong.com/44/36/01300000318079128273369493046_s.jpg"
	      onclick="$.uploadImg(dynamicMenu.callback,this)">
	    <input type="hidden" id = "idID" name="id">
	   </div>
	      <div class="caption">
			描述：<textarea name="note" id = "note" class="form-control" rows="3"></textarea>
			URL:<input name="url" id = "url" type = "text" class = "form-control" >
	      </div>
		</div>
	  </div>
	</form>
</body>
<script type="text/javascript">
	/* var selectItems = {};
	var callback = function(res){
		console.info('this',this);
		var img = $(this);
		var form = img.parent(".Image-template");
		console.info('formmm',form)
		console.info('ressss',res)
		form.find("#nameID").val(res.data.originalFilename);
		form.find("#realNameID").val(res.data.fileName);
		form.find("#pathID").val(res.data.filePath);
		form.find("#suffixationID").val(res.data.suffixation);
	} */
</script>
</html>