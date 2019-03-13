<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal" ms-controller="dataModel">
    <input type="hidden" id="id" name="id"  ms-attr-value="id"/>
    <input type="hidden" id="version" name="version" ms-attr-value="version"/>

    <div class="form-group">
        <label class="col-lg-3 control-label">任务:</label>
        <div class="col-lg-9">
            <input name="name" style="display:inline; width:94%;" class="form-control" type="text" dataType="Require"
                   id="nameID"  ms-attr-value="name"/>
            <span class="required">*</span></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">描述:</label>
        <div class="col-lg-9">
            <input name="memo" style="display:inline; width:94%;" class="form-control" type="text" id="memoID" ms-attr-value="memo"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">cron表达式:</label>
        <div class="col-lg-9">
            <input name="cronExpression" style="display:inline; width:94%;" class="form-control" type="text"
                   dataType="Require" id="cronExpressionID" ms-attr-value="cronExpression"/>
            <span class="required">*</span></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">消息模板:</label>
        <div class="col-lg-9" id="messageTemplate"></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">启用状态:</label>
        <div class="col-lg-9">
            <div class="btn-group select" id="statusID" ></div>
            <input type="hidden" id="statusID_" name="status" />
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
    selectItems['statusID'] = [
        {title: '是', value: 'true'},
        {title: '否', value: 'false'}
    ];
</script>
</body>
</html>