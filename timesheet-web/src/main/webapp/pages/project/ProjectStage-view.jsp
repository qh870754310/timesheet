<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="form-horizontal ms-controller" ms-controller="dictionaryDataModel">
    <div class="form-group">
        <label class="col-lg-3 control-label">项目类型:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="projectTypeName">{{projectTypeName}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">阶段名称:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="nameID">{{name}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">说明:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="memoID">{{memo}}</p>
        </div>
    </div>
</form>