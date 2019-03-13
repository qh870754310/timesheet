<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="java.util.Date"%>
<% String formId = "form_" + new Date().getTime();
   String gridId = "grid_" + new Date().getTime();
   String path = request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
   String contextPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=contextPath %>/js/dropDown.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/dropDowns.js"></script>
<script type="text/javascript">
var dictionaryDataModel = avalon.define({
	$id : 'dictionaryDataModel',
	id : '',
	dictdataName : '',
	dictdataValue : '',
	version : '',
	dictId : '',
	dictName : ''

})
var grid;
var form;
var _dialog;
$(function (){
    grid = $("#<%=gridId%>");
    form = $("#<%=formId%>");
	PageLoader = {
	   //
	    initSearchPanel:function(){
	        	            	                	            	                	            	                	            	        	     },
	    initGridPanel: function(){
	         var self = this;
	         var width = 180;
	         return grid.grid({
	                identity:"id",
	                buttons: [
	                        {content: '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>', action: 'add'},
	                        {content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>', action: 'modify'},
	                        {content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>', action: 'delete'}
	                    ],
	                url:"${pageContext.request.contextPath}/DictionaryData/pageJson.koala",
	                columns: [
	                	{ title: '字典名称', name: 'dictionaryDTO', width: width,render:function (rowdata, name, index){
	                		return rowdata.dictName ? rowdata.dictName : "";
	                	}},
	               		{ title: '键值', name: 'dictdataValue', width: width},
	     	        	{ title: '值', name: 'dictdataName', width: width},
	                	{ title: '操作', width: 120, render: function (rowdata, name, index){
                            var param = '"' + rowdata.id + '"';
                            var h = "<a href='javascript:void(0);' onclick='PageLoader.openDetailsPage(event," + param + ")'>查看</a> "
                            ;
                            return h;
                        }}
	                ]
	         }).on({
	                   'add': function(){
	                       self.add($(this));
	                   },
	                   'modify': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length == 0){
	                            $this.message({
	                                type: 'warning',
	                                content: '请选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                        if(indexs.length > 1){
	                            $this.message({
	                                type: 'warning',
	                                content: '只能选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                       self.modify(indexs[0], $this);
	                    },
	                   'delete': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length == 0){
	                            $this.message({
	                                   type: 'warning',
	                                   content: '请选择要删除的记录'
	                            });
	                            return;
	                        }
	                        var remove = function(){
	                            self.remove(indexs, $this);
	                        };
	                        $this.confirm({
	                            content: '确定要删除所选记录吗?',
	                            callBack: remove
	                        });
	                   }
	         });
	    },
	    add: function(grid){
	        var self = this;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog">'
	        	+'<div class="modal-content"><div class="modal-header"><button type="button" class="close" '
	        	+'data-dismiss="modal" aria-hidden="true">&times;</button>'
	        	+'<h4 class="modal-title">新增</h4></div><div class="modal-body">'
	        	+'<p>One fine body&hellip;</p></div><div class="modal-footer">'
	        	+'<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>'
	        	+'<button type="button" class="btn btn-success" id="save">保存</button></div></div>'
	        	+'</div></div>');
	        $.get('<%=path%>/DictionaryData-add.jsp').done(function(html){
           		dictionaryDataModel.dictId = '';
	        	dictionaryDataModel.dictName = '';
	            dialog.modal({
	                keyboard:false
	            }).on({
	            	'shown.bs.modal':function(){
	            		new dropDown({
                			"renderTo":dialog.find("#add-dictionaryId"),
                			"url":"${pageContext.request.contextPath}/DropDown/dic.koala",
                			"ms$id":"dictionaryDataModify",
                			"cusStyle":"display: inline; width: 94%;margin:0px;",
                			"cusClass":"form-control",
                			"name":"dictId"
                		});
	            	},
	                'hidden.bs.modal': function(){
	                    $(this).remove();
	                }
	            }).find('.modal-body').html(html);
	            self.initPage(dialog.find('form'));
	        });
	        dialog.find('#save').on('click',{grid: grid}, function(e){
	              if(!Validator.Validate(dialog.find('form')[0],3))return;
	              $.post('${pageContext.request.contextPath}/DictionaryData/add.koala', dialog.find('form').serialize()).done(function(result){
	                   if(result.success ){
	                        dialog.modal('hide');
	                        e.data.grid.data('koala.grid').refresh();
	                        e.data.grid.message({
	                            type: 'success',
	                            content: '保存成功'
	                         });
	                    }else{
	                        dialog.find('.modal-content').message({
	                            type: 'error',
	                            content: result.actionError
	                        });
	                     }
	              });
	        });
	    },
	    modify: function(id, grid){
	        var self = this;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">修改</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button><button type="button" class="btn btn-success" id="save">保存</button></div></div></div></div>');
	        $.get('<%=path%>/DictionaryData-update.jsp').done(function(html){
	               dialog.find('.modal-body').html(html);
	               self.initPage(dialog.find('form'));
	               $.get('${pageContext.request.contextPath}/DictionaryData/get/' + id + '.koala').done(function(json){
	                    json = json.data;
	                    dictionaryDataModel.id = json.id;
	                    dictionaryDataModel.version = json.version
	                    dictionaryDataModel.dictdataName = json.dictdataName;
	               		dictionaryDataModel.dictdataValue = json.dictdataValue;
	               		dictionaryDataModel.dictId = json.dictId;
	               		dictionaryDataModel.dictName = json.dictName;
	                });
	                dialog.modal({
	                    keyboard:false
	                }).on({
	                	'shown.bs.modal':function(){
	                		console.info("test",
	                		new dropDown({
	                			"renderTo":dialog.find("#add-dictionaryId"),
	                			"url":"${pageContext.request.contextPath}/DropDown/dic.koala",
	                			"ms$id":"dictionaryDataModify",
	                			"cusStyle":"display: inline; width: 94%;margin:0px;",
	                			"cusClass":"form-control",
	                			"msDuplex":"dictId",
	                			"name":"dictId"
	                		}));
		            	},
	                    'hidden.bs.modal': function(){
	                        $(this).remove();
	                    }
	                });
	                dialog.find('#save').on('click',{grid: grid}, function(e){
	                    if(!Validator.Validate(dialog.find('form')[0],3))return;
	                    $.post('${pageContext.request.contextPath}/DictionaryData/update.koala?', dialog.find('form').serialize()).done(function(result){
	                        if(result.success){
	                            dialog.modal('hide');
	                            e.data.grid.data('koala.grid').refresh();
	                            e.data.grid.message({
	                            type: 'success',
	                            content: '保存成功'
	                            });
	                        }else{
	                            dialog.find('.modal-content').message({
	                            type: 'error',
	                            content: result.actionError
	                            });
	                        }
	                    });
	                });
	        });
	    },
	    initPage: function(form){
	              form.find('.form_datetime').datetimepicker({
	                   language: 'zh-CN',
	                   format: "yyyy-mm-dd",
	                   autoclose: true,
	                   todayBtn: true,
	                   minView: 2,
	                   pickerPosition: 'bottom-left'
	               }).datetimepicker('setDate', new Date());//加载日期选择器
	               form.find('.select').each(function(){
	                    var select = $(this);
	                    var idAttr = select.attr('id');
	                    select.select({
	                        title: '请选择',
	                        contents: selectItems[idAttr]
	                    }).on('change', function(){
	                        form.find('#'+ idAttr + '_').val($(this).getValue());
	                    });
	               });
	               /* $.get('${pageContext.request.contextPath}/pages/templete/dropDown/dictionary.jsp').done(function(html){
	            	   var select = $(html);
	            	   form.find('#dicDTO').html(select)
	               }) */

	    },
	    remove: function(ids, grid){
	    	var data = [{ name: 'ids', value: ids.join(',') }];
	    	$.post('${pageContext.request.contextPath}/DictionaryData/delete.koala', data).done(function(result){
	                        if(result.success){
	                            grid.data('koala.grid').refresh();
	                            grid.message({
	                                type: 'success',
	                                content: '删除成功'
	                            });
	                        }else{
	                            grid.message({
	                                type: 'error',
	                                content: result.result
	                            });
	                        }
	    	});
	    },
	    openDetailsPage : function(event,id){
	       	event.cancelBubble = true;
	        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">查看</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-info" data-dismiss="modal">返回</button></div></div></div></div>');
	        $.get('<%=path%>/DictionaryData-view.jsp').done(function(html){
	               dialog.find('.modal-body').html(html);
	               $.get('${pageContext.request.contextPath}/DictionaryData/get/' + id + '.koala').done(function(json){
	               			json = json.data;
	               			dictionaryDataModel.dictdataName = json.dictdataName;
	               			dictionaryDataModel.dictdataValue = json.dictdataValue;
	               			dictionaryDataModel.dictName = json.dictName;
	               });
	                dialog.modal({
	                    keyboard:false
	                }).on({
	                	'shown.bs.modal':function(){
	               	        avalon.scan();
	                	},
	                    'hidden.bs.modal': function(){
	                        $(this).remove();
	                    }
	                });
	        });
		}
	}
	PageLoader.initSearchPanel();
	PageLoader.initGridPanel();
	form.find('#search').on('click', function(){
            if(!Validator.Validate(document.getElementById("<%=formId%>"),3))return;
            var params = {};
            form.find('input').each(function(){
                var $this = $(this);
                var name = $this.attr('name');
                if(name){
                    params[name] = $this.val();
                }
            });
            form.find('select').each(function(){
                var $this = $(this);
                var name = $this.attr('name');
                if(name){
                    params[name] = $this.val();
                }
            });
            console.info(form.find('select'))
            grid.getGrid().search(params);
        });
	new dropDown({
		"renderTo":"dictDropDown",
		"url":"${pageContext.request.contextPath}/DropDown/dic.koala",
		"ms$id":"dictionarySearch",
		"cusStyle":"width:180px;margin:0px;",
		"cusClass":"form-control",
		"name":"dictId"
	});
});
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
			<label class="control-label" style="width: 100px; float: left;">字典名称:&nbsp;</label>
			<div style="margin-left: 15px; float: left;" id="dictDropDown">
			</div>
		</div>
		</td>
       <td style="vertical-align: bottom;"><button id="search" type="button" style="position:relative; margin-left:35px; top: -15px" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button></td>
  </tr>
</table>
</form>
<!-- grid -->
<div id=<%=gridId%>></div>
</div>
</body>
</html>
