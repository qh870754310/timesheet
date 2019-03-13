<%-- 所有的静态资源导入都放在这里--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 样式 默认就有 type="text/css" --%>
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


<%-- js 默认就有 type="text/javascript" --%>
<script  src="${contextPath}/lib/jquery-1.8.3.min.js"></script>
<script  src='${contextPath}/lib/fullcalendar/lib/moment.min.js'></script>
<script  src='${contextPath}/lib/fullcalendar/fullcalendar.js'></script>
<script  src='${contextPath}/lib/fullcalendar/lang-all.js'></script>
<script  src='${contextPath}/lib/Map.js'></script>
<script  src='${contextPath}/js/timesetting/myCalendar.js'></script>
<script  src="${contextPath}/lib/jquery.form.js"></script>
<%-- avalon 和 fullcalendar.js有冲突 因为avalon自带amd加载器顾需要将fullcalendar.js放在avalon之前 --%>
<script  src="${contextPath}/lib/avalon.min.js"></script>
<script  src="${contextPath}/lib/respond.min.js"></script>
<script  src="${contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script  src="${contextPath}/lib/koala-tree.js"></script>
<script  src="${contextPath}/lib/koala-ui.plugin.js" ></script>
<script  src="${contextPath}/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script  src="${contextPath}/lib/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script  src="${contextPath}/lib/validate.js"></script>
<script  src="${contextPath}/js/security/role.js" ></script>
<script  src="${contextPath}/js/security/user.js" ></script>
<script  src="${contextPath}/js/main.js" ></script>
<script  src="${contextPath}/lib/validateForm/validateForm.js"></script>

<script>
	$.ajaxSetup({cache:false});
</script>
<style>
	.ms-controller {
		visibility: hidden
	}
</style>