<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${contextPath}/lib/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${contextPath}/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" href="${contextPath}/css/index.css"/>
<link rel="stylesheet" href="${contextPath}/css/koala.css"/>
<link rel="stylesheet" href="${contextPath}/css/koala-tree.css"/>
<link rel="stylesheet" href="${contextPath}/css/login.css"/>
<link rel="stylesheet" href="${contextPath}/css/main.css"/>
<link rel="stylesheet" href="${contextPath}/css/security.css"/>
<link rel="stylesheet" href="${contextPath}/css/organisation.css"/>
<link rel="stylesheet" href="${contextPath}/lib/validateForm/css/style.css"/>
<link rel="stylesheet" href="${contextPath}/css/gqc.css"/>
<link rel="stylesheet" href="${contextPath}/lib/fullcalendar/fullcalendar.min.css"/>
<div class="table-responsive">
<form id="form" action="/auth/user/import" enctype="multipart/form-data" method="post">
    <table class="table table-responsive table-bordered grid">
        <thead>
        <tr>
            <th><label style="font-size:14px;">批量导入用户</label></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <div class="form-group">
                    <a href="/exceltemplate/user.xlsx" class="btn btn-info"
                       target="_blank">下载模板</a>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="file">请选择Excel文件</label>
                </div>
                <div class="form-group">
                    <input type="file" name="file" id="file"/>
                    <p class="help-block">需要上传模板格式的Excel文件，点击上方下载按钮下载模板。</p>
                </div>
                <div class="form-group">
                    <button id="upload" type="submit" class="btn btn-success">上传</button>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <p></p>
                </div>

            </td>
        </tr>
        </tbody>

    </table>
    <p>${message}</p>
</form>

</div>