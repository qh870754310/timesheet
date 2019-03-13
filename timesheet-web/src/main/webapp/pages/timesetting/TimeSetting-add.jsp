<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
    <input type="hidden" name="id" value="" id="idID"/>
    <input type="hidden" name="version"  value="" id="versionID"/>
    <input type="hidden" name="createTime"  value="" id="createTimeID"/>
    <input type="hidden" name="creatorId"  value="" id="creatorIdID"/>
    <div class="form-group">
        <label class="col-lg-3 control-label">类型:</label>
        <div class="col-lg-9">
            <div id="dayTypeDiv" class="input-group" style="width:300px;float:left;">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">从:</label>
        <div class="col-lg-9">
            <div class="input-group date form_datetime_start" style="width:160px;float:left;">
                <input type="text" class="form-control" style="width:160px;" name="startTime" id="startTimeID">
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">到:</label>
        <div class="col-lg-9">
            <div class="input-group date form_datetime_end" style="width:160px;float:left;">
                <input type="text" class="form-control" style="width:160px;" name="endTime" id="endTimeID">
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">备注:</label>
        <div class="col-lg-9">
            <textarea name="content" style="display:inline; width:94%;" class="form-control" id="contentID"></textarea>
        </div>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function(){




    });

</script>
</body>
</html>