<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal" ms-controller="dictionaryDataModel">
    <input type="hidden" id="idID" name="id"   ms-attr-value="id"/>
    <input type="hidden" id="versionID"
           name="version"   ms-attr-value="version"/>

    <div class="form-group">
        <label class="col-lg-3 control-label">行业一:</label>
        <div class="col-lg-9" id="codeOne"></div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">行业二:</label>
        <div class="col-lg-9"  id="codeTwo">
        </div>
    </div>
</form>
<script type="text/javascript">
//    var selectItems = {};
//    var contents = [{title: '请选择', value: ''}];
//    selectItems['codeOneID'] = contents;
//    var contents = [{title: '请选择', value: ''}];
//    selectItems['codeTwoID'] = contents;
</script>
</body>
</html>