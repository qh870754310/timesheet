/**
	@usage:
	new dropDown({
		"renderTo":"modify-dictionaryId",
		"url":"${pageContext.request.contextPath}/DropDown/dic.koala",
		"ms$id":"dictionaryDataModify",
		"cusStyle":"width: 180px;",
		"cusClass":"form-control",
		"msDuplex":"dictionaryDTO.id",
		"name":"dictId"
	});
 */
var dropDown = function(config) {
	//name : '',//需要提交表单时必填
	//msDuplex : ''//需要绑定值时必填
	this.config = {
		renderTo : '',
		url : '',
		ms$id : ''
	}
	if(config){
		$.extend( true, this.config, config);
	}else{
		throw "创建字典下拉框错误，请传入正确的参数"
	}
	for(var item in this.config){
		if(isEmpty(config[item])){
			throw "参数中的"+item+"字段不能为空";
		}
	}
	this.initModel(this.config);
	this.initTPL(this.config);
	this.initData(this.config);
	return this;
}
dropDown.prototype.initModel = function(config) {
	this.msModel = avalon.define({
		$id : config.ms$id,
		data : []
	})
}
dropDown.prototype.initTPL = function(config) {
	var select = $("<select class=\"ms-controller\"></select>");
    if(!isEmpty(config.id))
        select.attr("id",config.id);
    if(!isEmpty(config.onChange))
        select.attr("onChange",config.onChange);
	if(!isEmpty(config.name))
		select.attr("name",config.name);
	if(!isEmpty(config.msDuplex))
		select.attr("ms-duplex",config.msDuplex);
	if(!isEmpty(config.cusClass))
		select.addClass(config.cusClass)
	if(!isEmpty(config.cusStyle))
		select.attr("style",config.cusStyle);
	select.attr("ms-controller",config.ms$id);
	
	var option = $("<option ms-repeat-el=\"data\" ms-attr-value=\"el.id\">{{el.val}}</option>")
	//初始默认值
	if(!isEmpty(config.defVal))
		option.attr("ms-attr-selected","el.id == "+config.defVal);
	select.append(option);
	if(config.renderTo instanceof jQuery){
		config.renderTo.append(select)
	}else{
		$("#"+config.renderTo).append(select);
	}
}
dropDown.prototype.initData = function(config) {
	var thiz = this;
	$.ajax({
		url : config.url,
		success : function(result) {
			thiz.msModel.data = [];
			thiz.msModel.data = thiz.msModel.data.concat(result);
			avalon.scan();
            if(config.ms$id == "projectTypeAdd") {
                initStageDropDown(result[0].id);
            }
		}
	})
}

