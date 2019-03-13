<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
	<input type="hidden" id="idID" name="id" /> 
	<input type="hidden" id="versionID" name="version" /> 
	
	           <div class="form-group">
                    <label class="col-lg-3 control-label">模板ID（用于接口调用）:</label>
	                    <div class="col-lg-9">
                           <input name="templateId" style="display:inline; width:94%;" class="form-control"  type="text" dataType="Require" id="templateIdID" />
		<span class="required">*</span>	    </div>
	</div>
		           <div class="form-group">
                    <label class="col-lg-3 control-label">模板名称:</label>
	                    <div class="col-lg-9">
                           <input name="name" style="display:inline; width:94%;" class="form-control"  type="text" dataType="Require" id="nameID" />
		<span class="required">*</span>	    </div>
	</div>
		           <div class="form-group">
                    <label class="col-lg-3 control-label">模板内容:</label>
	                <div class="col-lg-9">
                    <textarea class="form-control" style="display:inline; width:94%;" rows="3" id="contentID" name="content"  ></textarea>
			    </div>
	</div>
	</form>
<script type="text/javascript">
    var selectItems = {};
                            </script>
</body>
</html>