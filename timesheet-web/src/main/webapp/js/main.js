$.ajaxSetup({ 
	contentType : "application/x-www-form-urlencoded;charset=utf-8", 
	error : function(XMLHttpRequest, textStatus) { 
		if(XMLHttpRequest.status == 499){
			window.location.href = contextPath+"/j_spring_security_logout";
		}
	} 
}); 

/*获取i位uuid*/
function uuid(i){
	var uuid = "",len = i ? i : 32;
	for(var i = 0; i <= len; i++){
		uuid += Math.floor(Math.random() * 16.0).toString(16);
	}
	return uuid;
}

$(function(){
	/*
	 重置菜单栏容器默认高度
	 */
	var self = $(this);

	loadContent($('#home'), '/pages/welcome.jsp');
	/*
	* 菜单收缩样式变化
	 */
    var firstLevelMenu = $('.first-level-menu');
    firstLevelMenu.find('[data-toggle="collapse"]').each(function(){
        var $this = $(this);
        firstLevelMenu.find($(this).attr('href')).on({
            'shown.bs.collapse': function(e){
                $this.find('i:last').addClass('glyphicon-chevron-left').removeClass('glyphicon-chevron-right');
            },
            'hidden.bs.collapse': function(e){
                $this.find('i:last').removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-right');
            }
        });
    });
    
	/*
	 *菜单点击事件
	 */
	var $submenu = $('.first-level-menu').find('li');
	$submenu.on('click', function(){
		var $this = $(this);
		if($this.hasClass('active')) {
			return;
		}
		clearMenuEffect();
		$this.addClass('active').parent().closest('li').addClass('active').parent().closest('li').addClass('active');
		var target = $this.data('target');
		var title = $this.data('title');
		var mark = $this.data('mark');
		if(target && title && mark ){
			openTab(target, title, mark);
		}
	});

	/*
	 点击主页tab事件
	 */
	$('a[href="#home"]').on('click', function(){
		clearMenuEffect();
		$('.g-sidec').find('li[data-mark="home"]').click();
	});
	/*
	 修改密码
	 */
	self.on('modifyPwd',function(){
		$('body').modifyPassword({
			service: contextPath + '/auth/currentUser/updatePassword.koala'
		});
	});

	/*
	注销
	*/
	self.on('loginOut', function(){
		var logOut = contextPath+"/logout.koala";
		$.post(logOut,function(data){
			if(data.success){
				window.location.href = contextPath+"/login.koala";
			}
		});
	});
	
	$('#userManager').find('li').on('click', function(){
		self.trigger($(this).data('target'));
	});
	
	$('#allRolesId').find('li').on('click', function(){
		self.trigger($(this).data('target'));
	});
	
	 $('body').keydown(function(e) {
	     if (e.keyCode == 13) {
	         e.preventDefault();
	         e.stopPropagation();
	     }
	 });
	 
	 
});
/*
 *打开一个Tab
 */
var openTab = function(target, title, mark, id, param){
	var mainc =   $('.g-mainc');
    var tabs = mainc.find('#navTabs');
    var contents =  mainc.find('#tabContent');
    var content = contents.find('#'+mark);
    if(content.length > 0){
        content.attr('data-value', id);
        loadContent(content, target);
        tabs.find('a[href="#'+mark+'"]').tab('show');
        tabs.find('a[href="#'+mark+'"]').find('span').html(title);
        return;
    }
    content = $('<div id="'+mark+'" class="tab-pane" data-value="'+id+'"></div>');
    content.data(param);
    loadContent(content, target);
    contents.append(content);
    var tab =  $('<li>');
    tab.append($('<a href="#'+mark+'" data-toggle="tab"></a>')).find('a').html('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><span>'+title+'<span>');
    var closeBtn = tab.appendTo(tabs).on('click',function(){
        var $this = $(this);
        if($this.hasClass('active')){
            return;
        }
        $this.find('a:first').tab('show');
   		clearMenuEffect();
   		var $li = $('.g-sidec').find('li[data-mark="'+mark+'"]').addClass('active');
        if($li.parent().hasClass('collapse')){
        	var a = $li.parent().prev('a');
            a.hasClass('collapsed') && a.click();
        }
    }).find('a:first')
        .tab('show')
        .find('.close');
    closeBtn.css({position: 'absolute', right: (closeBtn.width()-10) + 'px', top: -1 + 'px'})
        .on('click',function(){
            var prev =  tab.prev('li').find('a:first');
            content.remove() && tab.remove();
            var herf = prev.tab('show').attr('href').replace("#", '');
            var $menuLi =  $('.g-sidec').find('li[data-mark="'+herf+'"]');
            if($menuLi.length){
                $menuLi.click();
            }else{
                clearMenuEffect();
            }
        });
};

/*
 加载DIV内容
 */
var loadContent = function(obj, url){
	$.get(contextPath + url).done(function(data, status, objXMLHttp){
    	var headers = objXMLHttp.getAllResponseHeaders();
    	if (headers.indexOf("login: login") >= 0 && window.location.pathname.indexOf("/login.koala") < 0) {
    		window.location.href = contextPath + "/login.koala";
    	} else {
    		obj.html(data);
            $('#tabContent').trigger('loadContentCompalte', obj);
    	}
    }).fail(function(){
            throw new Error('加载失败');
    }).always(function(){
        changeHeight();
    });
};

/*
 * 清除菜单效果
 */
var clearMenuEffect = function(){
    $('.first-level-menu').find('li').each(function(){
        var $menuLi = $(this);
        $menuLi.hasClass('active') && $menuLi.removeClass('active').parent().parent().removeClass('active');
    });
};

/**
 * 根据内容改变高度
 */
var changeHeight = function(){
    var sidebar = $('.g-sidec');
    var sidebarHeight = sidebar.outerHeight();
    var headerHeight = $('.g-head').outerHeight();
    var windowHeight = $(window).height();
    var bodyHeight = $(document).height();
    if(bodyHeight >  windowHeight){
        windowHeight =  bodyHeight;
    }
    var footHeight = $('#footer').outerHeight();
    var height =  windowHeight - headerHeight - footHeight;
    sidebarHeight < height && sidebar.css('height', height);
   // $('.g-sidec').css('min-height',height);
    $('.g-mainc').css('min-height', height);
};

var refreshToken = function($element){
	$.get('koala.token').done(function(data){
		$element.val(data);
	});
};

/***************************** mine start *********************************/

/**
 * 根据key值获得json中的对应的字，支持对象_属性
 * json中包含B对象则可以 val a = json_Value(json,'B_id')
 */
function json_Value(json,key){
	var keys = key.split("_");
	var length = keys.length-1;
	var value = '';
	if(length > 0){
		value = json[keys[0]];
		console.info(keys[0],value);
		for(var i = 1; i < length; i++){
			if(isEmpty(value)){
				value = '';
				break;
			}else{
				value = value[keys[i]];
			}
		}
	}
	return value;
}
/**
 * 空验证 	
 */
function isEmpty(v,allowBlank){
	return v===null||v===undefined||(!allowBlank?v==="":false)
}
/**
 * 序列化为json的方法
 * //将从form中通过$('#form').serialize()获取的值转成json
 */
var DataDeal = {
    formToJson: function (data) {
	       data=data.replace(/&/g,"\",\"");
	   data=data.replace(/=/g,"\":\"");
	   data="{\""+data+"\"}";
	   return data;
	},
};
/**
 * 扩展Date类的格式化方法
 */
$(function(){
	Date.prototype.customFormat = function(formatString){
		  var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
		  YY = ((YYYY=this.getFullYear())+"").slice(-2);
		  MM = (M=this.getMonth()+1)<10?('0'+M):M;
		  MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
		  DD = (D=this.getDate())<10?('0'+D):D;
		  DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
		  th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
		  formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
		  h=(hhh=this.getHours());
		  if (h==0) h=24;
		  if (h>12) h-=12;
		  hh = h<10?('0'+h):h;
		  hhhh = h<10?('0'+hhh):hhh;
		  AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
		  mm=(m=this.getMinutes())<10?('0'+m):m;
		  ss=(s=this.getSeconds())<10?('0'+s):s;
		  return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
		};
})

/**
 * 模态对话框
 * @param $ jquery对象
 */
+function($) {
	var _setZIndex = function(){
		var modalBackdrop = $(".modal-backdrop");
		var modal = $(".modal")
		var length = modalBackdrop.length;
		for(var i = 0; i < length; i++){
			var modalBackdropZindex = parseInt($(modalBackdrop[i]).css("zIndex"))+i*15;
			var modalZindex =parseInt($(modal[i]).css("zIndex"))+i*15;
			$(modalBackdrop[i]).css("zIndex",modalBackdropZindex);
			$(modal[i]).css("zIndex",modalZindex);
		}
	};
	$.userDefined = {
		Msg : function(title,msg,autoClose){
			$.userDefined.Dialog({
			    titleHtml : title ? title : '提示',
				bodyHtml : msg ? msg : '内容',
				closeBtnTxt : '确认',
				submitBtnShow : false,
				initDataFunc : function(obj){
					if(autoClose === undefined){
						autoClose = true;
					}
					if(autoClose){
						setTimeout(function(){
							obj.dialog.modal('hide');
						},1000)
					}
				}
			});
		},
		Dialog : function(config) {
			var _defaultConfig = {
				bodyHtml : '<p>One fine body&hellip;</p>',
				titleHtml : 'Modal title',
				closeBtnTxt : '关闭',
				submitBtnTxt : '提交',
				closeBtnShow : true,
				submitBtnShow : true,
				submitFunc : null,
				submitAndClose : true,
				size : '',//modal_lg,modal_sm
				initDataFunc : null
			};
			$.extend(_defaultConfig,config);
			var dialog = $('<div class="modal fade"><div class="modal-dialog '+_defaultConfig.size+'"><div class="modal-content"><div class="modal-header">'+
					'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>'+
					'</button><h4 class="modal-title">Modal title</h4></div><div class="modal-body"><p>One fine body&hellip;</p></div>'+
					'<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
					'<button type="button" class="btn btn-primary">Save changes</button></div></div></div></div>');
			$.userDefined._setTitle(_defaultConfig.titleHtml,dialog);
			$.userDefined._setContent(_defaultConfig.bodyHtml,dialog);
			$.userDefined._setCloseBtn(_defaultConfig.closeBtnTxt,_defaultConfig.closeBtnShow,dialog);
			$.userDefined._setSubmitBtn(_defaultConfig.submitBtnTxt,_defaultConfig.submitBtnShow,_defaultConfig.submitFunc,_defaultConfig.submitAndClose,dialog);
			dialog.modal({
				keyboard : false,
				backdrop : 'static'
			}).on('hidden.bs.modal', function() {
				dialog.remove();
				if($(".modal").length>0){
					$(document.body).addClass('modal-open');
				}
			}).on('shown.bs.modal', function(){
				$(document.body).addClass('modal-open');
				_setZIndex();
				if(typeof _defaultConfig.initDataFunc === "function"){
					var obj = {"dialog":dialog};
					_defaultConfig.initDataFunc.call(this,obj);
				}
			})
		},
		_setContent : function(bodyHtml,dialog){
			var body = dialog.find('.modal-body').html("body");
			body.html(bodyHtml);
		},
		_setTitle : function(titleHtml,dialog){
			var title = dialog.find('.modal-title').html("title");
			title.html(titleHtml);
		},
		_setCloseBtn : function(txt,closeBtnShow,dialog){
			var closeBtn = $(dialog.find('.modal-footer button')[0]);
			if(closeBtnShow){
				closeBtn.text(txt);
			}else{
				closeBtn.remove();
			}
		},
		_setSubmitBtn : function(txt,submitBtnShow,submitFunc,submitAndClose,dialog){
			var submitBtn = $(dialog.find('.modal-footer button')[1]);
			if(submitBtnShow){
				submitBtn.text(txt);
				if(typeof submitFunc === "function"){
					submitBtn.on("click",function(){
						var obj = {"dialog":dialog};
						submitFunc.call(this,obj);
					});
				}
				if(submitAndClose){
					submitBtn.on("click",function(){
						dialog.modal('hide');
					});
				}
			}else{
				submitBtn.remove();
			}
		}
	}
}(window.jQuery)
+function($){
	$.uploadfile = function(callback,path){
		$.get(contextPath + '/pages/common/upload.jsp', function(data){
			$.userDefined.Dialog({
			  	titleHtml : '上传',
				bodyHtml : data,
				submitFunc : function(obj){
					var form = $(obj.dialog.find("#fileUploadId"));
					var ajax_option = {
						url : contextPath+"/upload/img.koala",
						data : {path : isEmpty(path) ? '':path},
						success : function(res){
							if($.isFunction(callback)){
								callback.call(this,res)
							}
						},
						error : function(){
							$.userDefined.Msg("提示","附件过大")
						}
					}
					form.ajaxSubmit(ajax_option);
				}
			});
		});
	}
	
}(window.jQuery)
/**
 * liux
 * @param $
 */
+function($){
	$.uploadImg = function(callback,el){
		$.get(contextPath + '/pages/common/uploadImg.jsp', function(data){
			$.userDefined.Dialog({
			  	titleHtml : '上传',
				bodyHtml : data,
				submitFunc : function(obj){
					console.info('obj',obj)
					var form = $(obj.dialog.find("#fileUploadId"));
					var ajax_option = {
						url : contextPath+"/upload/img.koala",
						success : function(res){
							if($.isFunction(callback)){
								callback.call(el,res)
							}
							$(el).attr("src",contextPath+"/upload"+res.data.showImage);
						},
						error : function(){
							$.userDefined.Msg("提示","附件过大")
						}
					}
					form.ajaxSubmit(ajax_option);
				}
			});
		});
	}
	
}(window.jQuery)
/***************************** mine end *********************************/