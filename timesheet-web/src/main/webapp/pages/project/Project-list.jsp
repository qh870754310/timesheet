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
        var dictionaryDataModel = avalon.define({
            $id: 'dictionaryDataModel',
            id: '',
            name: '',
            memo: '',
            version: '',
            status: '',
            projectTypeId: '',
            projectTypeName: '',
            projectStageId: '',
            projectStageName: '',
            pmId: '',
            pmName: '',
            departmentId: '',
            departmentName: ''
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
                },
                initGridPanel: function () {
                    var self = this;
                    var width = 130;
                    return grid.grid({
                        identity: "id",
                        buttons: [
                            {
                                content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;管理团队</button>',
                                action: 'addUser'
                            },
                            {
                                content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>',
                                action: 'add'
                            },
                            {
                                content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>',
                                action: 'modify'
                            },
                            {
                                content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>',
                                action: 'delete'
                            },
                            {
                                content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>批量导入</button>',
                                action: 'import'
                            }
                        ],
                        url: "${pageContext.request.contextPath}/project/pageJson.koala",
                        columns: [
                            {title: '项目名称', name: 'name', width: width},
                            {title: '项目类型', name: 'projectTypeName', width: width},
                            {title: '项目阶段', name: 'projectStageName', width: width},
                            {title: '项目经理', name: 'pmName', width: width},
                            {title: '所属部门', name: 'departmentName', width: width},
                            {title: '项目状态', name: 'statusName', width: width},
                            {
                                title: '操作', width: 120, render: function (rowdata, name, index) {
                                var param = '"' + rowdata.id + '"';
                                var h = "<a href='javascript:openDetailsPage(" + param + ")'>查看</a>";
                                if(rowdata.status != null && rowdata.status == -1) {
                                    h += "&nbsp;<a href='javascript:openProject(" + param + ")'>恢复项目</a> ";
                                } else {
                                    h += "&nbsp;<a href='javascript:closeProject(" + param + ")'>关闭项目</a>";
                                }
                                return h;
                            }
                            }
                        ]
                    }).on({
                        'addUser' : function(event, data) {
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
                            $.get(contextPath + '/pages/project/project-setTeam.jsp').done(function(data){
                                var dialog  = $(data);
                                dialog.find('.modal-header').find('.modal-title').html('管理项目[' +$(".checked").parent().next().html()+']团队成员');
                                dialog.find('#grantAuthorityToUserButton').on('click',function(){
                                    var grantRolesToUserTableItems = dialog.find('#notGrantAuthoritiesToUserGrid').getGrid().selectedRows();

                                    if(grantRolesToUserTableItems.length == 0){
                                        dialog.find('#notGrantAuthoritiesToUserGrid').message({
                                            type: 'warning',
                                            content: '请选择成员'
                                        });
                                        return;
                                    }
                                    var url = contextPath+'/project/setTeam.koala';
                                    var ids = ""
                                    $.each(grantRolesToUserTableItems,function(index,grantRolesToUserTableItem){
                                        ids +=  grantRolesToUserTableItem.id + ",";
                                    });
                                    ids = ids.substring(0, ids.length - 1);
                                    var data = [{name: 'projectId', value: indexs},{name: 'userIds', value: ids}];
                                    $.post(url, data).done(function(data) {
                                        if(data.success){
                                            dialog.find('#grantAuthorityToUserMessage').message({
                                                type: 'success',
                                                content: '分配成功'
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
                                            content: '请选择成员'
                                        });
                                        return;
                                    }

                                    var ids = "";
                                    $.each(notGrantRolesToUserGridItems,function(index,notGrantRolesToUserGridItem){
                                        ids += (notGrantRolesToUserGridItem.id + ",");
                                    });
                                    ids = ids.substring(0, ids.length-1);
                                    var data = [{name: 'projectId', value: indexs},{name: 'userIds', value: ids}];
                                    $.post(contextPath + '/project/unSetTeam.koala', data).done(function(data) {
                                        if(data.success){
                                            dialog.find('#grantAuthorityToUserMessage').message({
                                                type: 'success',
                                                content: '撤销成功！'
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
                                            title : "姓名",
                                            name : "name",
                                            width : 80
                                        }, {
                                            title : "邮箱",
                                            name : "email",
                                            width : 100
                                        }];

                                        var notGrantRolecolumns = [{
                                            title : "姓名",
                                            name : "name",
                                            width : 80
                                        }, {
                                            title : "邮箱",
                                            name : "email",
                                            width : 100
                                        }];

                                        dialog.find('#notGrantAuthoritiesToUserGrid').grid({
                                            identity: 'id',
                                            columns: notGrantRolecolumns,
                                            url: contextPath + '/project/pagingQueryNotGrantUsers.koala?projectId='+indexs
                                        });

                                        dialog.find('#grantAuthoritiesToUserGrid').grid({
                                            identity: 'id',
                                            columns: hasGrantRoleColumns,
                                            url: contextPath + '/project/pagingQueryGrantUserByProjectId.koala?projectId='+indexs
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
                        'add': function () {
                            self.add($(this));
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
                        },
                        'import' : function(event, data){
                            var thiz 	= $(this);
                            var  mark 	= thiz.attr('mark');
                            mark = openTab('${pageContext.request.contextPath}/pages/project/project-import.jsp', "导入项目", mark);
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
                    $.get('<%=path%>/Project-add.jsp').done(function (html) {
                        dictionaryDataModel.id = '';
                        dictionaryDataModel.name = '';
                        dictionaryDataModel.memo = '';
                        dialog.modal({
                            keyboard: false
                        }).on({
                            'shown.bs.modal': function () {
                                new dropDown({
                                    "renderTo": dialog.find("#projectType"),
                                    "url": "${pageContext.request.contextPath}/ProjectType/list.koala",
                                    "ms$id": "projectTypeAdd",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control typeSelect",
                                    "name": "projectTypeId",
                                    "id" : "projectTypeId",
                                    "onChange" : "changeStages()"
                                });
                                new dropDown({
                                    "renderTo": dialog.find("#pm"),
                                    "url": "${pageContext.request.contextPath}/DropDown/employee.koala",
                                    "ms$id": "pmAdd",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control",
                                    "name": "pmId"
                                });
                                new dropDown({
                                    "renderTo": dialog.find("#department"),
                                    "url": "${pageContext.request.contextPath}/DropDown/department.koala",
                                    "ms$id": "departmentAdd",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control",
                                    "name": "departmentId"
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
                        $.post('${pageContext.request.contextPath}/project/add.koala', dialog.find('form').serialize()).done(function (result) {
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
                    $.get('<%=path%>/Project-update.jsp').done(function (html) {
                        dialog.find('.modal-body').html(html);
                        self.initPage(dialog.find('form'));
                        $.get('${pageContext.request.contextPath}/project/get/' + id + '.koala').done(function (json) {
                            json = json.data;
                            dictionaryDataModel.id = json.id;
                            dictionaryDataModel.version = json.version;
                            dictionaryDataModel.status = json.status;
                            dictionaryDataModel.name = json.name;
                            dictionaryDataModel.memo = json.memo;
                            dictionaryDataModel.projectTypeId = json.projectTypeId;
                            dictionaryDataModel.projectStageId = json.projectStageId;
                            dictionaryDataModel.pmId = json.pmId;
                            dictionaryDataModel.departmentId = json.departmentId;
                        });
                        dialog.modal({
                            keyboard: false
                        }).on({
                            'shown.bs.modal': function () {
                                new dropDown({
                                    "renderTo": dialog.find("#projectType"),
                                    "url": "${pageContext.request.contextPath}/ProjectType/list.koala",
                                    "ms$id": "projectTypeMofidy",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control typeSelect",
                                    "msDuplex": "projectTypeId",
                                    "name": "projectTypeId",
                                    "id" : "projectTypeId",
                                    "onChange" : "changeStages()"
                                });
                                new dropDown({
                                    "renderTo": dialog.find("#projectStage"),
                                    "url": "${pageContext.request.contextPath}/ProjectStage/listByProjectId/"+dictionaryDataModel.projectTypeId+".koala",
                                    "ms$id": "projecStageMofidy",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control",
                                    "msDuplex": "projectStageId",
                                    "name": "projectStageId"
                                });
                                new dropDown({
                                    "renderTo": dialog.find("#pm"),
                                    "url": "${pageContext.request.contextPath}/DropDown/employee.koala",
                                    "ms$id": "pmModify",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control",
                                    "msDuplex": "pmId",
                                    "name": "pmId"
                                });
                                new dropDown({
                                    "renderTo": dialog.find("#department"),
                                    "url": "${pageContext.request.contextPath}/DropDown/department.koala",
                                    "ms$id": "departmentModify",
                                    "cusStyle": "display: inline; width: 94%;margin:0px;",
                                    "cusClass": "form-control",
                                    "msDuplex": "departmentId",
                                    "name": "departmentId"
                                });
                            },
                            'hidden.bs.modal': function () {
                                $(this).remove();
                            }
                        });
                        dialog.find('#save').on('click', {grid: grid}, function (e) {
                            if (!Validator.Validate(dialog.find('form')[0], 3))return;
                            $.post('${pageContext.request.contextPath}/project/update.koala?id=' + id, dialog.find('form').serialize()).done(function (result) {
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
                        select.select({
                            title: '请选择',
                            contents: selectItems[idAttr]
                        }).on('change', function () {
                            form.find('#' + idAttr + '_').val($(this).getValue());
                        });
                    });
                },
                remove: function (ids, grid) {
                    var data = [{name: 'ids', value: ids.join(',')}];
                    $.post('${pageContext.request.contextPath}/project/delete.koala', data).done(function (result) {
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
            $.get('<%=path%>/Project-view.jsp').done(function (html) {
                dialog.find('.modal-body').html(html);
                $.get('${pageContext.request.contextPath}/project/get/' + id + '.koala').done(function (json) {
                    json = json.data;
                    dictionaryDataModel.id = json.id;
                    dictionaryDataModel.version = json.version;
                    dictionaryDataModel.status = json.status;
                    dictionaryDataModel.name = json.name;
                    dictionaryDataModel.memo = json.memo;
                    dictionaryDataModel.projectTypeName = json.projectTypeName;
                    dictionaryDataModel.projectStageName = json.projectStageName;
                    dictionaryDataModel.pmName = json.pmName;
                    dictionaryDataModel.departmentName = json.departmentName;
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
        var closeProject = function (id) {
            var $this = $(this);
            $this.confirm({
                content: '确定要关闭此项目吗?',
                callBack: function(){
                    $.get('${pageContext.request.contextPath}/project/close/'+id+"?t="+new Date().getTime()).done(function (result) {
                        if (result.success) {
                            grid.data('koala.grid').refresh();
                            grid.message({
                                type: 'success',
                                content: '项目关闭成功'
                            });
                        } else {
                            grid.message({
                                type: 'error',
                                content: result.result
                            });
                        }
                    });
                }
            });

        }
        var openProject = function (id) {

            var $this = $(this);
            $this.confirm({
                content: '确定要恢复此项目吗?',
                callBack: function(){
                    $.get('${pageContext.request.contextPath}/project/open/'+id+"?t="+new Date().getTime()).done(function (result) {
                        if (result.success) {
                            grid.data('koala.grid').refresh();
                            grid.message({
                                type: 'success',
                                content: '项目恢复成功'
                            });
                        } else {
                            grid.message({
                                type: 'error',
                                content: result.result
                            });
                        }
                    });
                }
            });

        }
        function initStageDropDown(typeId) {
            new dropDown({
                "renderTo": $("#projectStage"),
                "url": "${pageContext.request.contextPath}/ProjectStage/listByProjectId/"+typeId+".koala",
                "ms$id": "projectStageAdd",
                "cusStyle": "display: inline; width: 94%;margin:0px;",
                "cusClass": "form-control",
                "name": "projectStageId"
            });
        }
        function changeStages(){
            $("#projectStage").html("");
            initStageDropDown($("#projectTypeId").val());
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
                        <label class="control-label" style="width:100px;float:left;">项目名称:&nbsp;</label>
                        <div style="margin-left:15px;float:left;">
                            <input name="name" class="form-control" type="text" style="width:180px;" id="nameID"/>
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
