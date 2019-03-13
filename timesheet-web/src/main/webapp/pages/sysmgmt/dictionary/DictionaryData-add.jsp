<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="form-horizontal ms-controller" ms-controller="dictionaryDataModel">

		<div class="form-group">
			<label class="col-lg-3 control-label">字典名称:</label>
			<div class="col-lg-9" id="add-dictionaryId">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">名称:</label>
			<div class="col-lg-9">
				<input name="dictdataName" style="display: inline; width: 94%;"
					class="form-control" type="text" id="dictdataNameID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">值:</label>
			<div class="col-lg-9">
				<input name="dictdataValue" style="display: inline; width: 94%;"
					class="form-control" type="text" id="dictdataValueID" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var selectItems = {};
	</script>
</body>
</html>