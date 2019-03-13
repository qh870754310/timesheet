<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="form-horizontal ms-controller" ms-controller="dictionaryDataModel">
    <div class="form-group">
        <label class="col-lg-3 control-label">项目名称:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="nameID">{{name}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目类型:</label>
        <div class="col-lg-9" id="projectType">{{projectTypeName}}
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目阶段:</label>
        <div class="col-lg-9" id="projectStage">{{projectStageName}}</div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目经理:</label>
        <div class="col-lg-9" id="pm">{{pmName}}</div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目部门:</label>
        <div class="col-lg-9" id="department">{{departmentName}}</div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">说明:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="memoID">{{memo}}</p>
        </div>
    </div>
</form>