<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
	
	           <div class="form-group">
                    <label class="col-lg-3 control-label">项目类型:</label>
	                    <div class="col-lg-9">
                           <input name="name" style="display:inline; width:94%;" class="form-control"  type="text" dataType="Require" id="nameID" />
		<span class="required">*</span>	    </div>
	</div>
		           <div class="form-group">
                    <label class="col-lg-3 control-label">说明:</label>
	                <div class="col-lg-9">
                    <textarea class="form-control" style="display:inline; width:94%;" rows="3" id="memoID" name="memo"  ></textarea>
			    </div>
	</div>
	</form>
<script type="text/javascript">
    var selectItems = {};
                    </script>
</body>
</html>