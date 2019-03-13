<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/common/header.jsp"%><!--引入权限系统该页面需无须引用header.jsp -->
<%@ page import="java.util.Date"%>
<% String formId = "form_" + new Date().getTime();
   String gridId = "grid_" + new Date().getTime();
   String path = request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
%>
<script type="text/javascript">
var grid;
var form;
var _dialog;
$(function (){
    grid = $("#<%=gridId%>");
    form = $("#<%=formId%>");
	PageLoader = {
	   //
	    initSearchPanel:function(){
	        	            	                	            	                	            	                	                     var startTimeVal = form.find('#createDateID_start');
	                     var startTime = startTimeVal.parent();
	                     var endTimeVal = form.find('#createDateID_end');
	                     var endTime = endTimeVal.parent();
	                     startTime.datetimepicker({
	                                        language: 'zh-CN',
	                                        format: "yyyy-mm-dd",
	                                        autoclose: true,
	                                        todayBtn: true,
	                                        minView: 2,
	                                        pickerPosition: 'bottom-left'
	                     }).on('changeDate', function(){
	                                 endTime.datetimepicker('setStartDate', startTimeVal.val());
	                           });//加载日期选择器
	                     var yesterday = new Date();
	                     yesterday.setDate(yesterday.getDate() - 1);
	                     endTime.datetimepicker({
	                             language: 'zh-CN',
	                             format: "yyyy-mm-dd",
	                             autoclose: true,
	                             todayBtn: true,
	                             minView: 2,
	                             pickerPosition: 'bottom-left'
	                     }).on('changeDate', function(ev){
	                                startTime.datetimepicker('setEndDate', endTimeVal.val());
	                           }).datetimepicker('setDate', new Date()).trigger('changeDate');//加载日期选择器
	                     startTime.datetimepicker('setDate', yesterday).trigger('changeDate');
	                	            	                	            	                	            	                	                     var startTimeVal = form.find('#modifyDateID_start');
	                     var startTime = startTimeVal.parent();
	                     var endTimeVal = form.find('#modifyDateID_end');
	                     var endTime = endTimeVal.parent();
	                     startTime.datetimepicker({
	                                        language: 'zh-CN',
	                                        format: "yyyy-mm-dd",
	                                        autoclose: true,
	                                        todayBtn: true,
	                                        minView: 2,
	                                        pickerPosition: 'bottom-left'
	                     }).on('changeDate', function(){
	                                 endTime.datetimepicker('setStartDate', startTimeVal.val());
	                           });//加载日期选择器
	                     var yesterday = new Date();
	                     yesterday.setDate(yesterday.getDate() - 1);
	                     endTime.datetimepicker({
	                             language: 'zh-CN',
	                             format: "yyyy-mm-dd",
	                             autoclose: true,
	                             todayBtn: true,
	                             minView: 2,
	                             pickerPosition: 'bottom-left'
	                     }).on('changeDate', function(ev){
	                                startTime.datetimepicker('setEndDate', endTimeVal.val());
	                           }).datetimepicker('setDate', new Date()).trigger('changeDate');//加载日期选择器
	                     startTime.datetimepicker('setDate', yesterday).trigger('changeDate');
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
	                url:"${pageContext.request.contextPath}/Agency/pageJson.koala",
	                columns: [
	                     	                         	                         { title: 'isAvailable', name: 'isAvailable', width: width},
	                         	                         	                         	                         { title: 'creatorId', name: 'creatorId', width: width},
	                         	                         	                         	                         { title: 'createDate', name: 'createDate', width: width},
	                         	                         	                         	                         { title: 'modifierId', name: 'modifierId', width: width},
	                         	                         	                         	                         { title: 'modifyDate', name: 'modifyDate', width: width},
	                         	                         	                         	                         { title: 'name', name: 'name', width: width},
	                         	                         	                         	                         { title: 'logo', name: 'logo', width: width},
	                         	                         	                         	                         { title: 'code', name: 'code', width: width},
	                         	                         	                         	                         { title: 'type', name: 'type', width: width},
	                         	                         	                         	                         { title: 'industryCode', name: 'industryCode', width: width},
	                         	                         	                         	                         { title: 'regNum', name: 'regNum', width: width},
	                         	                         	                         	                         { title: 'regAddress', name: 'regAddress', width: width},
	                         	                         	                         	                         { title: 'regDate', name: 'regDate', width: width},
	                         	                         	                         	                         { title: 'regTypeCode', name: 'regTypeCode', width: width},
	                         	                         	                         	                         { title: 'typeCode', name: 'typeCode', width: width},
	                         	                         	                         	                         { title: 'regCapital', name: 'regCapital', width: width},
	                         	                         	                         	                         { title: 'regMoneyCode', name: 'regMoneyCode', width: width},
	                         	                         	                         	                         { title: 'businessDomain', name: 'businessDomain', width: width},
	                         	                         	                         	                         { title: 'govAreaCode', name: 'govAreaCode', width: width},
	                         	                         	                         	                         { title: 'statsCode', name: 'statsCode', width: width},
	                         	                         	                         	                         { title: 'mainBusinessDomain', name: 'mainBusinessDomain', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativeName', name: 'legalRepresentativeName', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativeSex', name: 'legalRepresentativeSex', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativePhone', name: 'legalRepresentativePhone', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativePosition', name: 'legalRepresentativePosition', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativeTitle', name: 'legalRepresentativeTitle', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativeFax', name: 'legalRepresentativeFax', width: width},
	                         	                         	                         	                         { title: 'legalRepresentativeIdentify', name: 'legalRepresentativeIdentify', width: width},
	                         	                         	                         	                         { title: 'businessLicense', name: 'businessLicense', width: width},
	                         	                         	                         	                         { title: 'businessTermdate', name: 'businessTermdate', width: width},
	                         	                         	                         	                         { title: 'issueDate', name: 'issueDate', width: width},
	                         	                         	                         	                         { title: 'setupDate', name: 'setupDate', width: width},
	                         	                         	                         	                         { title: 'industry', name: 'industry', width: width},
	                         	                         	                         	                         { title: 'issueAuthority', name: 'issueAuthority', width: width},
	                         	                         	                         	                         { title: 'net', name: 'net', width: width},
	                         	                         	                         	                         { title: 'registerAddress', name: 'registerAddress', width: width},
	                         	                         	                         	                         { title: 'contactAddress', name: 'contactAddress', width: width},
	                         	                         	                         	                         { title: 'techdomain', name: 'techdomain', width: width},
	                         	                         	                         	                         { title: 'property', name: 'property', width: width},
	                         	                         	                         	                         { title: 'istwcompary', name: 'istwcompary', width: width},
	                         	                         	                         	                         { title: 'mainProduct', name: 'mainProduct', width: width},
	                         	                         	                         	                         { title: 'taxAuthority', name: 'taxAuthority', width: width},
	                         	                         	                         	                         { title: 'taxIdentify', name: 'taxIdentify', width: width},
	                         	                         	                         	                         { title: 'taxerDentify', name: 'taxerDentify', width: width},
	                         	                         	                         	                         { title: 'size', name: 'size', width: width},
	                         	                         	                         	                         { title: 'freeTaxIdentify', name: 'freeTaxIdentify', width: width},
	                         	                         	                         	                         { title: 'introduction', name: 'introduction', width: width},
	                         	                         	                         	                         { title: 'postcode', name: 'postcode', width: width},
	                         	                         	                             { title: '操作', width: 120, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     var h = "<a href='javascript:openDetailsPage(" + param + ")'>查看</a> ";
	                                     return h;
	                                 }
	                             }
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
	        $.get('<%=path%>/Agency-add.jsp').done(function(html){
	            dialog.modal({
	                keyboard:false
	            }).on({
	                'hidden.bs.modal': function(){
	                    $(this).remove();
	                }
	            }).find('.modal-body').html(html);
	            self.initPage(dialog.find('form'));
	        });
	        dialog.find('#save').on('click',{grid: grid}, function(e){
	              if(!Validator.Validate(dialog.find('form')[0],3))return;
	              $.post('${pageContext.request.contextPath}/Agency/add.koala', dialog.find('form').serialize()).done(function(result){
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
	        $.get('<%=path%>/Agency-update.jsp').done(function(html){
	               dialog.find('.modal-body').html(html);
	               self.initPage(dialog.find('form'));
	               $.get( '${pageContext.request.contextPath}/Agency/get/' + id + '.koala').done(function(json){
	                       json = json.data;
	                        var elm;
	                        for(var index in json){
	                            elm = dialog.find('#'+ index + 'ID');
	                            if(elm.hasClass('select')){
	                                elm.setValue(json[index]);
	                            }else{
	                                elm.val(json[index]);
	                            }
	                        }
	                });
	                dialog.modal({
	                    keyboard:false
	                }).on({
	                    'hidden.bs.modal': function(){
	                        $(this).remove();
	                    }
	                });
	                dialog.find('#save').on('click',{grid: grid}, function(e){
	                    if(!Validator.Validate(dialog.find('form')[0],3))return;
	                    $.post('${pageContext.request.contextPath}/Agency/update.koala?id='+id, dialog.find('form').serialize()).done(function(result){
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
	    },
	    remove: function(ids, grid){
	    	var data = [{ name: 'ids', value: ids.join(',') }];
	    	$.post('${pageContext.request.contextPath}/Agency/delete.koala', data).done(function(result){
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
            grid.getGrid().search(params);
        });
});

var openDetailsPage = function(id){
        var dialog = $('<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">查看</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div><div class="modal-footer"><button type="button" class="btn btn-info" data-dismiss="modal">返回</button></div></div></div></div>');
        $.get('<%=path%>/Agency-view.jsp').done(function(html){
               dialog.find('.modal-body').html(html);
               $.get( '${pageContext.request.contextPath}/Agency/get/' + id + '.koala').done(function(json){
                       json = json.data;
                        var elm;
                        for(var index in json){
                        if(json[index]+"" == "false"){
                        		dialog.find('#'+ index + 'ID').html("<span style='color:#d2322d' class='glyphicon glyphicon-remove'></span>");
                        	}else if(json[index]+"" == "true"){
                        		dialog.find('#'+ index + 'ID').html("<span style='color:#47a447' class='glyphicon glyphicon-ok'></span>");
                        	}else{
                          		 dialog.find('#'+ index + 'ID').html(json[index]+"");
                        	}
                        }
               });
                dialog.modal({
                    keyboard:false
                }).on({
                    'hidden.bs.modal': function(){
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
          <label class="control-label" style="width:100px;float:left;">isAvailable:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="isAvailable" class="form-control" type="text" style="width:180px;" id="isAvailableID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">creatorId:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="creatorId" class="form-control" type="text" style="width:180px;" id="creatorIdID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">createDate:&nbsp;</label>
           <div style="margin-left:15px;float:left;">
            <div class="input-group date form_datetime" style="width:160px;float:left;" >
                <input type="text" class="form-control" style="width:160px;" name="createDate" id="createDateID_start" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <div style="float:left; width:10px; margin-left:auto; margin-right:auto;">&nbsp;-&nbsp;</div>
            <div class="input-group date form_datetime" style="width:160px;float:left;" >
                <input type="text" class="form-control" style="width:160px;" name="createDateEnd" id="createDateID_end" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
       </div>
                      <label class="control-label" style="width:100px;float:left;">regDate:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regDate" class="form-control" type="text" style="width:180px;" id="regDateID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">typeCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="typeCode" class="form-control" type="text" style="width:180px;" id="typeCodeID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">modifyDate:&nbsp;</label>
           <div style="margin-left:15px;float:left;">
            <div class="input-group date form_datetime" style="width:160px;float:left;" >
                <input type="text" class="form-control" style="width:160px;" name="modifyDate" id="modifyDateID_start" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <div style="float:left; width:10px; margin-left:auto; margin-right:auto;">&nbsp;-&nbsp;</div>
            <div class="input-group date form_datetime" style="width:160px;float:left;" >
                <input type="text" class="form-control" style="width:160px;" name="modifyDateEnd" id="modifyDateID_end" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
       </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">regMoneyCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regMoneyCode" class="form-control" type="text" style="width:180px;" id="regMoneyCodeID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">businessDomain:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="businessDomain" class="form-control" type="text" style="width:180px;" id="businessDomainID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">govAreaCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="govAreaCode" class="form-control" type="text" style="width:180px;" id="govAreaCodeID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">statsCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="statsCode" class="form-control" type="text" style="width:180px;" id="statsCodeID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">mainBusinessDomain:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="mainBusinessDomain" class="form-control" type="text" style="width:180px;" id="mainBusinessDomainID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">legalRepresentativeName:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativeName" class="form-control" type="text" style="width:180px;" id="legalRepresentativeNameID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">legalRepresentativeSex:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativeSex" class="form-control" type="text" style="width:180px;" id="legalRepresentativeSexID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">legalRepresentativePhone:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativePhone" class="form-control" type="text" style="width:180px;" id="legalRepresentativePhoneID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">legalRepresentativePosition:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativePosition" class="form-control" type="text" style="width:180px;" id="legalRepresentativePositionID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">legalRepresentativeTitle:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativeTitle" class="form-control" type="text" style="width:180px;" id="legalRepresentativeTitleID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">legalRepresentativeFax:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativeFax" class="form-control" type="text" style="width:180px;" id="legalRepresentativeFaxID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">legalRepresentativeIdentify:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="legalRepresentativeIdentify" class="form-control" type="text" style="width:180px;" id="legalRepresentativeIdentifyID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">businessLicense:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="businessLicense" class="form-control" type="text" style="width:180px;" id="businessLicenseID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">businessTermdate:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="businessTermdate" class="form-control" type="text" style="width:180px;" id="businessTermdateID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">issueDate:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="issueDate" class="form-control" type="text" style="width:180px;" id="issueDateID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">setupDate:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="setupDate" class="form-control" type="text" style="width:180px;" id="setupDateID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">industry:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="industry" class="form-control" type="text" style="width:180px;" id="industryID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">issueAuthority:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="issueAuthority" class="form-control" type="text" style="width:180px;" id="issueAuthorityID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">net:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="net" class="form-control" type="text" style="width:180px;" id="netID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">registerAddress:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="registerAddress" class="form-control" type="text" style="width:180px;" id="registerAddressID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">contactAddress:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="contactAddress" class="form-control" type="text" style="width:180px;" id="contactAddressID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">techdomain:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="techdomain" class="form-control" type="text" style="width:180px;" id="techdomainID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">property:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="property" class="form-control" type="text" style="width:180px;" id="propertyID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">istwcompary:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="istwcompary" class="form-control" type="text" style="width:180px;" id="istwcomparyID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">mainProduct:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="mainProduct" class="form-control" type="text" style="width:180px;" id="mainProductID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">taxAuthority:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="taxAuthority" class="form-control" type="text" style="width:180px;" id="taxAuthorityID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">taxIdentify:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="taxIdentify" class="form-control" type="text" style="width:180px;" id="taxIdentifyID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">taxerDentify:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="taxerDentify" class="form-control" type="text" style="width:180px;" id="taxerDentifyID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">size:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="size" class="form-control" type="text" style="width:180px;" id="sizeID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">freeTaxIdentify:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="freeTaxIdentify" class="form-control" type="text" style="width:180px;" id="freeTaxIdentifyID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">introduction:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="introduction" class="form-control" type="text" style="width:180px;" id="introductionID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">postcode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="postcode" class="form-control" type="text" style="width:180px;" id="postcodeID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">modifierId:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="modifierId" class="form-control" type="text" style="width:180px;" id="modifierIdID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">code:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="code" class="form-control" type="text" style="width:180px;" id="codeID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">name:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="name" class="form-control" type="text" style="width:180px;" id="nameID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">industryCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="industryCode" class="form-control" type="text" style="width:180px;" id="industryCodeID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">logo:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="logo" class="form-control" type="text" style="width:180px;" id="logoID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">regAddress:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regAddress" class="form-control" type="text" style="width:180px;" id="regAddressID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">type:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="type" class="form-control" type="text" style="width:180px;" id="typeID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">regTypeCode:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regTypeCode" class="form-control" type="text" style="width:180px;" id="regTypeCodeID"  />
        </div>
            </div>
                  <div class="form-group">
          <label class="control-label" style="width:100px;float:left;">regNum:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regNum" class="form-control" type="text" style="width:180px;" id="regNumID"  />
        </div>
                      <label class="control-label" style="width:100px;float:left;">regCapital:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            <input name="regCapital" class="form-control" type="text" style="width:180px;" id="regCapitalID"  />
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
