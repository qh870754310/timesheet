<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/taglibs.jsp" %>
<%
    String contextPath = request.getContextPath();
    String formId = "form_" + new Date().getTime();
    String gridId = "grid_" + new Date().getTime();
    String path = request.getContextPath() + request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/") + 1);
%>

<div id="calendar" class="calendar">
</div>
<input type="hidden" id="dateHidden"/>
<script type="text/javascript">
    var grid;
    var form;
    $(function () {
        grid = $("#<%=gridId%>");
        form = $("#<%=formId%>");
        PageLoader = {
            initSearchPanel: function () {
            },
            initGridPanel: function () {
                var self = this;
                var width = 180;
                initFullCalendar(self);
            },
            add: function (grid) {
                var self = this;
                var dialog = $('<div class="modal fade"><div class="modal-dialog">'
                        + '<div class="modal-content"><div class="modal-header"><button type="button" class="close" '
                        + 'data-dismiss="modal" aria-hidden="true">&times;</button>'
                        + '<h4 class="modal-title">设置</h4></div><div class="modal-body">'
                        + '<p>One fine body&hellip;</p></div><div class="modal-footer">'
                        + '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
                        + '<button type="button" class="btn btn-success" id="save">保存</button></div></div>'
                        + '</div></div>');
                $.get('<%=path%>/TimeSetting-add.jsp').done(function (html) {
                    dialog.modal({
                        keyboard: false
                    }).on({
                        'shown.bs.modal' : function() {
                            var radioHtml = "";
                            $.get("${pageContext.request.contextPath}/TimeSetting/getDayType.koala", "", function (data) {
                                var valuePrev = 0;
                                for (var prop in data) {
                                    if(data[prop] == 1) {
                                        var checked = "checked='checked'";
                                    } else {
                                        var checked = "";
                                    }
                                    if (data[prop] > valuePrev) {
                                        radioHtml += "<input type='radio' "+checked+" name='dayType' value='" + data[prop] + "' id='dayType" + data[prop] + "' /><label style='padding-left:3px;margin-right:10px;' for='dayType" + data[prop] + "'>" + prop + "</label> ";
                                    } else {
                                        radioHtml = "<input type='radio' "+checked+" name='dayType' value='" + data[prop] + "' id='dayType" + data[prop] + "' /><label style='padding-left:3px;margin-right:10px;' for='dayType" + data[prop] + "'>" + prop + "</label> " + radioHtml;
                                    }
                                    valuePrev = data[prop];
                                }
                                $.get("${pageContext.request.contextPath}/TimeSetting/getByDate.koala", "date=" + $("#dateHidden").val(), function (result) {
                                    $("#dayTypeDiv").html(radioHtml);
                                    if(result.success) {
                                        $("#idID").val(result.data.id);
                                        $("#versionID").val(result.data.version);
                                        $("#createTimeID").val(result.data.createTime);
                                        $("#creatorIdID").val(result.data.creatorId);
                                        $("#startTimeID").val(result.data.startTime);
                                        $("#endTimeID").val(result.data.endTime);
                                        $("#contentID").val(result.data.content);
                                        $("#dayType" + result.data.dayType).attr("checked","checked");
                                    }
                                });
                            });
                        },
                        'hidden.bs.modal': function () {
                            $(this).remove();
                        }
                    }).find('.modal-body').html(html);
                    self.initPage(dialog.find('form'));
                });
                dialog.find('#save').on('click', {grid: grid}, function (e) {
                    if (!Validator.Validate(dialog.find('form')[0], 3)) {
                        return;
                    }
                    $.post('${pageContext.request.contextPath}/TimeSetting/add.koala', dialog.find('form').serialize()).done(function (result) {
                        if (result.success) {
                            //e.data.grid.data('koala.grid').refresh();
                            dialog.modal('hide');
                            $("#calendar").message({
                                type: 'success',
                                content: '保存成功'
                            });
                            // 刷新父页面
//                            $("#calendar").html("");
//                            initFullCalendar(self);
                        } else {
                            dialog.find('.modal-content').message({
                                type: 'error',
                                content: result.actionError
                            });
                        }
                    });
                });
            },
            initPage: function(form) {

                var date = new Date($("#dateHidden").val());
                date.setHours(9,0,0,0);
                form.find('.form_datetime_start').datetimepicker({
                    language: 'zh-CN',
                    format: "yyyy-mm-dd HH:00:00",
                    autoclose: true,
                    todayBtn: true,
                    minView: 1,
                    maxView: 1,
                    pickerPosition: 'bottom-left'
                }).datetimepicker('setDate', date);//加载日期选择器
                date.setHours(17,00,0,0);
                form.find('.form_datetime_end').datetimepicker({
                    language: 'zh-CN',
                    format: "yyyy-mm-dd HH:00:00",
                    autoclose: true,
                    todayBtn: true,
                    minView: 1,
                    maxView: 1,
                    pickerPosition: 'bottom-left'
                }).datetimepicker('setDate', date);//加载日期选择器
            }
        }
        PageLoader.initSearchPanel();
        PageLoader.initGridPanel();
    });
</script>
