<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form class="form-horizontal ms-controller" ms-controller="dataModel">
    <div class="form-group">
        <label class="col-lg-3 control-label">任务:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="nameID">{{name}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">描述:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="memoID">{{memo}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">cron表达式:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="cronExpressionID">{{cronExpression}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">消息模板:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="messageTemplate">{{messageTemplateName}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">启用状态:</label>
        <div class="col-lg-9">
            <p class="form-control-static" id="statusID">{{status==true?'是':'否'}}</p>
        </div>
    </div>
</form>