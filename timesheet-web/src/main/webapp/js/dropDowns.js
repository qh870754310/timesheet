/**
 * 级联下拉
 */	

/**
	@usage:
	new dropDowns({
		"renderTo":"modify-dictionaryId",
		"url":"${pageContext.request.contextPath}/DropDown/dic.koala",
		"ms$id":"dictionaryDataModify",
		"cusStyle":"width: 180px;",
		"cusClass":"form-control",
		"msDuplex":"dictionaryDTO.id",
		"name":"dictId"
	});
 */
var dropDowns = function(config) {
	this.config = {
			renderTo : '',
			url : '',
			ms$id : ''
		}
		/*console.info(config)*/
		if(config){
			$.extend( true, this.config, config);
			/*console.info(config)*/
		}else{
			throw "创建下拉框错误，请传入正确的参数"
		}
		for(var item in this.config){
			if(isEmpty(config[item])){
				throw "参数中的"+item+"字段不能为空";
			}
		}
		this.initModel(this.config);
		this.initData(this.config);
		this.initTPL(this.config);
		return this;
}

dropDowns.prototype.initModel = function(config){
	var map = new Map();
	var msModel = avalon.define({
		$id : config.ms$id,
		searchForm : [],
		lastValue : '',
        setSearchForm : function(index) {
        	
        	//删除第index后的元素
        	msModel.searchForm.removeAll(function(arr, i) {
                 if (i > index)
                     return true;
                 return false;
            });
            var currNode = this.value;
            map.put(index,currNode);
            //不等于-1则查询子集合
            if(currNode != -1){
            	msModel.getChildOrg(currNode);
            	msModel.lastValue = currNode;
            }else{
            	//等于-1且不是第一个节点时则选择上一个节点的值，是第一个节点则值设置为null
            	if(index != 0){
            		msModel.lastValue = map.get(index-1);
            	}else{
            		msModel.lastValue = null;
            	}
            }
            console.info(msModel.lastValue)
        }, 
        
        getChildOrg : function(id) {
        	$.get(config.url+"?parentId="+id).done(function(data){
            if(!isEmpty(data) && data.length > 0){
            	msModel.searchForm.push(data);
            }
        	});
         }
	})
	this.msModel = msModel;
}

dropDowns.prototype.initData = function(config){
	var thiz = this;
	$.ajax({
		url : config.url,
		success : function(result) {
			thiz.msModel.searchForm.push(result);
			avalon.scan();
		}
	})
}

dropDowns.prototype.initTPL = function(config){
	var select = $("<select ms-change=\"setSearchForm($index)\" ms-repeat-form=\"searchForm\"></select>");
	var hidden = $("<input type=\"hidden\" ms-attr-value=\"lastValue\"></input>")
	if(!isEmpty(config.name))
		hidden.attr("name",config.name);
	if(!isEmpty(config.msDuplex))
		select.attr("ms-duplex",config.msDuplex);
	if(!isEmpty(config.cusClass))
		select.addClass(config.cusClass)
	if(!isEmpty(config.cusStyle))
		select.attr("style",config.cusStyle);
	var option = $("<option value=\"-1\">请选择</option><option ms-repeat-el=\"form\" ms-attr-value=\"el.id\">{{el.val}}</option>");
	select.attr("ms-controller",config.ms$id)
	hidden.attr("ms-controller",config.ms$id)
	
	select.append(option);
	
	if(config.renderTo instanceof jQuery){
		/*config.addClass("ms-controller");*/
		config.renderTo.append(select)
		config.renderTo.append(hidden)
	}else{
		/*$("#"+config.renderTo).addClass("ms-controller");*/
		$("#"+config.renderTo).append(select)
		$("#"+config.renderTo).append(hidden)
	}
}




