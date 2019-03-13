<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal" ms-controller="dictionaryDataModel">
    <input type="hidden" id="id" name="id"  ms-attr-value="id"/>
    <input type="hidden" id="version" name="version" ms-attr-value="version"/>
    <input type="hidden" id="status" name="status" ms-attr-value="status"/>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目名称:</label>
        <div class="col-lg-9">
            <input name="name" style="display:inline; width:94%;" class="form-control" type="text" dataType="Require"
                   id="name" ms-attr-value="name"/>
            <span class="required">*</span></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目类型:</label>
        <div class="col-lg-9" id="projectType">
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目阶段:</label>
        <div class="col-lg-9" id="projectStage"></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目经理:</label>
        <div class="col-lg-9" id="pm"></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">项目部门:</label>
        <div class="col-lg-9" id="department"></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">说明:</label>
        <div class="col-lg-9">
            <textarea class="form-control" style="display:inline; width:94%;" rows="3" id="memo"
                      name="memo" ms-attr-value="memo"></textarea>
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
</script>
</body>
</html>