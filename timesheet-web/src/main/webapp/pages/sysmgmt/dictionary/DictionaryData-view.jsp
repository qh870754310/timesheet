<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="form-horizontal ms-controller" ms-controller="dictionaryDataModel">
	<div class="form-group">
		<label class="col-lg-3 control-label">字典名称:</label>
		<div class="col-lg-9">
			<p class="form-control-static" id="dictionaryDTO_dictName_ID">{{dictName}}</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">键值:</label>
		<div class="col-lg-9">
			<p class="form-control-static" id="dictdataValue_ID">{{dictdataValue}}</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">值:</label>
		<div class="col-lg-9">
			<p class="form-control-static" id="dictdataDesc_ID">{{dictdataName}}</p>
		</div>
	</div>
</form>