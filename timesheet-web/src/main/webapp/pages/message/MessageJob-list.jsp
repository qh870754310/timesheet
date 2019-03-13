<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/pages/common/header.jsp"%><!--引入权限系统该页面需无须引用header.jsp -->
    <%@ page import="java.util.Date" %>
    <% String formId = "form_" + new Date().getTime();
        String gridId = "grid_" + new Date().getTime();
        String path = request.getContextPath() + request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/") + 1);
    %>
    <script type="text/javascript" src="<%=contextPath %>/js/dropDown.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/js/dropDowns.js"></script>
    <script type="text/javascript">
        var dataModel = avalon.define({
            $id: 'dataModel',
            id: '',
            name: '',
            memo: '',
            version: '',
            status: '',
            cronExpression: '',
            messageTemplateId: '',
            messageTemplateName: ''
        })
        var grid;
        var form;
        var _dialog;
        $(function () {
            grid = $("#<%=gridId%>");
            form = $("#<%=formId%>");
            PageLoader = {
                //
                initSearchPanel: function () {
                    form.find('#status_SELECT').select({
                        title: '请选择',
                        contents: [
                            {title: '请选择', value: ''},
                            {title: '是', value: '1'},
                            {title: '否', value: '0'}
                        ]
                    }).on('change', function () {
                        form.find('#statusID_').val($(this).getValue());
                    });
                },
                initGridPanel: function () {
                    var self = this;
                    var width = 180;
                    return grid.grid({
                        identity: "id",
                        buttons: [
                            {
                                content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>',
                                action: 'add'
                            },
                            {
                                content: '<button class="btn btn-info" type="button"><span class="glyphicon glyphicon-th-large"></span>&nbsp;分配发送对象</button>',
                                action: 'grantRoleToUser'
                            },
                            {
                                content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>',
                                action: 'modify'
                            },
                            {
                                content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>',
                                action: 'delete'
                            }

                        ],
                        url: "${pageContext.request.contextPath}/MessageJob/pageJson.koala",
                        columns: [
                            {title: '任务', name: 'name', width: width},
                            {title: '描述', name: 'memo', width: width},
                            {title: 'cron表达式', name: 'cronExpression', width: width},
                            {title: '消息模板', name: 'messageTemplateName', width: width},
                            {
                                title: '启用状态', name: 'status', width: 120, render: function (rowdata, rowindex, value) {
                                return rowdata.status ? "<span style='color:#47a447' class='glyphicon glyphicon-ok'></span>" : "<span style='color:#d2322d' class='glyphicon glyphicon-remove'></span>";
                            }
                            },
                            {
                                title: '操作', width: 120, render: function (rowdata, name, index) {
                                var param = '"' + rowdata.id + '"';
                                var h = "<a href='javascript:openDetailsPage(" + param + ")'>查看</a> ";
                                return h;
                            }
                            }
                        ]
                    }).on({
                        'add': function () {
                            self.add($(this));
                        },
                        'grantRoleToUser' : function(event, data) {
                            var indexs = data.data;
                            var $this = $(this);
                            if (indexs.length == 0) {
                                $this.message({
                                    type : 'warning',
                                    content : '请选择一条记录'
                                });
                                return;
                            }
                            if (indexs.length > 1) {
                                $this.message({
                                    type : 'warning',
                                    content : '只能选择一条记录'
                                });
                                return;
                            }
                            $.get(contextPath + '/pages/message/messageJob-setRoles.jsp').done(function(data){

                                var dialog  = $(data);
                                dialog.find('.modal-header').find('.modal-title').html('为定时任务[' +$(".checked").parent().next().html()+']分配发送对象');
                                dialog.find('#grantAuthorityToUserButton').on('click',function(){
                                    var grantRolesToUserTableItems = dialog.find('#notGrantAuthoritiesToUserGrid').getGrid().selectedRows();

                                    if(grantRolesToUserTableItems.length == 0){
                                        dialog.find('#notGrantAuthoritiesToUserGrid').message({
                                            type: 'warning',
                                            content: '请选择要需要被分配的角色'
                                        });
                                        return;
                                    }
                                    var url = contextPath+'/MessageJob/setMessageJobRole.koala';
                                    var ids = ""
                                    $.each(grantRolesToUserTableItems,function(index,grantRolesToUserTableItem){
                                        ids +=  grantRolesToUserTableItem.id + ",";
                                    });
                                    ids = ids.substring(0, ids.length - 1);
                                    var data = [{name: 'messageJobId', value: indexs},{name: 'authorityIds', value: ids}];
                                    $.post(url, data).done(function(data) {
                                        if(data.success){
                                            dialog.find('#grantAuthorityToUserMessage').message({
                                                type: 'success',
                                                content: '分配角色成功'
                                            });
                                            dialog.find('#notGrantAuthoritiesToUserGrid').grid('refresh');
                                            dialog.find('#grantAuthoritiesToUserGrid').grid('refresh');
                                        }
                                    });
                                });

                                dialog.find('#notGrantAuthorityToUserButton').on('click',function(){
                                    var notGrantRolesToUserGridItems = dialog.find('#grantAuthoritiesToUserGrid').getGrid().selectedRows();

                                    if(notGrantRolesToUserGridItems.length == 0){
                                        dialog.find('#grantAuthoritiesToUserGrid').message({
                                            type: 'warning',
                                            content: '请选择要需要取消的角色'
                                        });
                                        return;
                                    }

                                    var ids = "";
                                    $.each(notGrantRolesToUserGridItems,function(index,notGrantRolesToUserGridItem){
                                        ids += (notGrantRolesToUserGridItem.id + ",");
                                    });
                                    ids = ids.substring(0, ids.length-1);
                                    var data = [{name: 'messageJobId', value: indexs},{name: 'authorityIds', value: ids}];
                                    $.post(contextPath + '/MessageJob/unSetMessageJobRole.koala', data).done(function(data) {
                                        if(data.success){
                                            dialog.find('#grantAuthorityToUserMessage').message({
                                                type: 'success',
                                                content: '撤销角色成功！'
                                            });
                                            dialog.find('#notGrantAuthoritiesToUserGrid').grid('refresh');
                                            dialog.find('#grantAuthoritiesToUserGrid').grid('refresh');
                                        }
                                    });

                                });

                                dialog.modal({
                                    keyboard: false,
                                    backdrop: false
                                }).on({
                                    'hidden.bs.modal' : function(){
                                        $(this).remove();
                                    },
                                    'shown.bs.modal' : function(){
                                        var hasGrantRoleColumns = [{
                                            title : "角色名称",
                                            name : "name",
                                            width : 80
                                        }, {
                                            title : "角色描述",
                                            name : "description",
                                            width : 100
                                        }];

                                        var notGrantRolecolumns = [{
                                            title : "角色名称",
                                            name : "name",
                                            width : 80
                                        }, {
                                            title : "角色描述",
                                            name : "description",
                                            width : 100
                                        }];

                                        dialog.find('#notGrantAuthoritiesToUserGrid').grid({
                                            identity: 'id',
                                            columns: notGrantRolecolumns,
                                            url: contextPath + '/MessageJob/pagingQueryNotGrantRoles.koala?messageJobId='+indexs
                                        });

                                        dialog.find('#grantAuthoritiesToUserGrid').grid({
                                            identity: 'id',
                                            columns: hasGrantRoleColumns,
                                            url: contextPath + '/MessageJob/pagingQueryGrantRoleByMessageJobId.koala?messageJobId='+indexs
                                        });
                                    }

                                });

                                //兼容IE8 IE9
                                if(window.ActiveXObject){
                                    if(parseInt(navigator.userAgent.toLowerCase().match(/msie ([\d.]+)/)[1]) < 10){
                                        dialog.trigger('shown.bs.modal');
                                    }
                                }

                            });

                        },
                        'modify': function (event, data) {
                            var indexs = data.data;
                            var $this = $(this);
                            if (indexs.length == 0) {
                                $this.message({
                                    type: 'warning',
                                    content: '请选择一条记录进行修改'
                                })
                                return;
                            }
                            if (indexs.length > 1) {
                                $this.message({
                                    type: 'warning',
                                    content: '只能选择一条记录进行修改'
                                })
                                return;
                            }
                            self.modify(indexs[0], $this);
                        },
                        'delete': function (event, data) {
                            var indexs = data.data;
                            var $this = $(this);
                            if (indexs.length == 0) {
                                $this.message({
                                    type: 'warning',
                                    content: '请选择要删除的记录'
                                });
                                return;
                            }
                            var remove = function () {
                                self.remove(indexs, $this);
                            };
                            $this.confirm({
                                content: '确定要删除所选记录吗?',
                                callBack: remove
                            });
                        }
                    });
                },
                add: function (grid) {
                    var self = this;
                    var dialog = $('<div class="modal fade"><div class="modal-dialog">'
                            + '<div class="modal-content"><div class="modal-header"><button type="button" class="close" '
                            + 'data-dismiss="modal" aria-hidden="true">&times;</button>'
                            + '<h4 class="modal-title">新增</h4></div><div class="modal-body">'
                            + '<p>One fine body&hellip;</p></div><div class="modal-footer">'
                            + '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
                            + '<button type="button" class="btn btn-success" id="save">保存</button></div></div>'
                            + '</div></div>');
                    $.get('<%=path%>/MessageJob-add.jsp').done(function (html) {

                        dataModel.id = '';
                        dataModel.version = '';
                        dataModel.name = '';
                        dataModel.memo = '';

                        dialog.modal({
                            keyboard: false
                        }).on({
                            'shown.bs.modal': function () {
                                new dropDown({
                                    "renderTo": dialog.find("#messageTemplate"),
                                    "url": "${pageContext.request.contextPath}/DropDown/messageTemplate.koala",
                                    "ms$id": "messageTemplateAdd",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control typeSelect",
                                    "name": "messageTemplateId",
                                    "id": "messageTemplateId"
                                });
                            },
                            'hidden.bs.modal': function () {
                                $(this).remove();
                            }
                        }).find('.modal-body').html(html);
                        self.initPage(dialog.find('form'));
                    });
                    dialog.find('#save').on('click', {grid: grid}, function (e) {
                        if (!Validator.Validate(dialog.find('form')[0], 3))return;
                        $.post('${pageContext.request.contextPath}/MessageJob/add.koala', dialog.find('form').serialize()).done(function (result) {
                            if (result.success) {
                                dialog.modal('hide');
                                e.data.grid.data('koala.grid').refresh();
                                e.data.grid.message({
                                    type: 'success',
                                    content: '保存成功'
                                });
                            } else {
                                dialog.find('.modal-content').message({
                                    type: 'error',
                                    content: result.actionError
                                });
                            }
                        });
                    });
                },
                modify: function (id, grid) {
                    var self = this;
                    var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">修改</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button><button type="button" class="btn btn-success" id="save">保存</button></div></div></div></div>');
                    $.get('<%=path%>/MessageJob-update.jsp').done(function (html) {
                        dialog.find('.modal-body').html(html);
                        self.initPage(dialog.find('form'));
                        $.get('${pageContext.request.contextPath}/MessageJob/get/' + id + '.koala').done(function (json) {
                            json = json.data;
                            dataModel.id = json.id;
                            dataModel.version = json.version;
                            dataModel.name = json.name;
                            dataModel.memo = json.memo;
                            dataModel.status = json.status;
                            dataModel.cronExpression = json.cronExpression;
                            dataModel.messageTemplateId = json.messageTemplateId;
//                            var elm;
//                            for (var index in json) {
//                                elm = dialog.find('#' + index + 'ID');
//                                if (elm.hasClass('select')) {
//                                    elm.setValue(json[index]);
//                                } else {
//                                    elm.val(json[index]);
//                                }
//                            }
                        });
                        dialog.modal({
                            keyboard: false
                        }).on({
                            'shown.bs.modal': function () {
                                new dropDown({
                                    "renderTo": dialog.find("#messageTemplate"),
                                    "url": "${pageContext.request.contextPath}/DropDown/messageTemplate.koala",
                                    "ms$id": "messageTemplateModify",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control select",
                                    "msDuplex": "messageTemplateId",
                                    "name": "messageTemplateId",
                                    "id": "messageTemplateId"
                                });
                                $("#statusID").setValue(dataModel.status);
                                form.find("#statusID_").val(dataModel.status);
                            },
                            'hidden.bs.modal': function () {
                                $(this).remove();
                            }
                        });
                        dialog.find('#save').on('click', {grid: grid}, function (e) {
                            if (!Validator.Validate(dialog.find('form')[0], 3))return;
                            $.post('${pageContext.request.contextPath}/MessageJob/update.koala?id=' + id, dialog.find('form').serialize()).done(function (result) {
                                if (result.success) {
                                    dialog.modal('hide');
                                    e.data.grid.data('koala.grid').refresh();
                                    e.data.grid.message({
                                        type: 'success',
                                        content: '保存成功'
                                    });
                                } else {
                                    dialog.find('.modal-content').message({
                                        type: 'error',
                                        content: result.actionError
                                    });
                                }
                            });
                        });
                    });
                },
                initPage: function (form) {
                    form.find('.form_datetime').datetimepicker({
                        language: 'zh-CN',
                        format: "yyyy-mm-dd",
                        autoclose: true,
                        todayBtn: true,
                        minView: 2,
                        pickerPosition: 'bottom-left'
                    }).datetimepicker('setDate', new Date());//加载日期选择器
                    form.find('.select').each(function () {
                        var select = $(this);
                        var idAttr = select.attr('id');
                        form.find('#' + idAttr + '_').val(true);
                        select.select({
                            title: '是',
                            contents: selectItems[idAttr]
                        }).on('change', function () {
                            form.find('#' + idAttr + '_').val($(this).getValue());
                        });

                    });
                },
                remove: function (ids, grid) {
                    var data = [{name: 'ids', value: ids.join(',')}];
                    $.post('${pageContext.request.contextPath}/MessageJob/delete.koala', data).done(function (result) {
                        if (result.success) {
                            grid.data('koala.grid').refresh();
                            grid.message({
                                type: 'success',
                                content: '删除成功'
                            });
                        } else {
                            grid.message({
                                type: 'error',
                                content: result.result
                            });
                        }
                    });
                }
            }
            PageLoader.initSearchPanel();
            PageLoader.initGridPanel();
            form.find('#search').on('click', function () {
                if (!Validator.Validate(document.getElementById("<%=formId%>"), 3))return;
                var params = {};
                form.find('input').each(function () {
                    var $this = $(this);
                    var name = $this.attr('name');
                    if (name) {
                        params[name] = $this.val();
                    }
                });
                grid.getGrid().search(params);
            });
        });

        var openDetailsPage = function (id) {
            var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">查看</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-info" data-dismiss="modal">返回</button></div></div></div></div>');
            $.get('<%=path%>/MessageJob-view.jsp').done(function (html) {
                dialog.find('.modal-body').html(html);
                $.get('${pageContext.request.contextPath}/MessageJob/get/' + id + '.koala').done(function (json) {
                    json = json.data;
                    dataModel.id = json.id;
                    dataModel.version = json.version;
                    dataModel.name = json.name;
                    dataModel.memo = json.memo;
                    dataModel.status = json.status;
                    dataModel.cronExpression = json.cronExpression;
                    dataModel.messageTemplateId = json.messageTemplateId;
                    dataModel.messageTemplateName = json.messageTemplateName;
                });
                dialog.modal({
                    keyboard: false
                }).on({
                    'shown.bs.modal': function () {
                        avalon.scan();
                    },
                    'hidden.bs.modal': function () {
                        $(this).remove();
                    }
                });
            });
        }
    </script>
</head>
<body>
<div style="width:98%;margin-right:auto; margin-left:auto; padding-top: 15px;">
    <!-- search form -->
    <form name=<%=formId%> id=<%=formId%> target="_self" class="form-horizontal">
        <input type="hidden" name="page" value="0">
        <input type="hidden" name="pagesize" value="10">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <div class="form-group">
                        <label class="control-label" style="width:100px;float:left;">任务:&nbsp;</label>
                        <div style="margin-left:15px;float:left;">
                            <input name="name" class="form-control" type="text" style="width:180px;" id="nameID"/>
                        </div>
                        <label class="control-label" style="width:100px;float:left;">启用状态:&nbsp;</label>
                        <div style="margin-left:15px;float:left;">
                            <div class="btn-group select" id="status_SELECT"></div>
                            <input type="hidden" id="statusID_" name="status"/>
                        </div>
                    </div>
                </td>
                <td style="vertical-align: bottom;">
                    <button id="search" type="button" style="position:relative; margin-left:35px; top: -15px"
                            class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询
                    </button>
                </td>
            </tr>
        </table>
    </form>
    <!-- grid -->
    <div id=<%=gridId%>></div>
</div>
</body>
</html>
