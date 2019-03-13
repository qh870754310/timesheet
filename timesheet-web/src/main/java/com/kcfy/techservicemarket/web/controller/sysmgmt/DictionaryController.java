package com.kcfy.techservicemarket.web.controller.sysmgmt;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDTO;
import com.kcfy.techservicemarket.facade.sysmgmt.DictionaryFacade;

@Controller
@RequestMapping("/Dictionary")
public class DictionaryController {
		
	@Inject
	private DictionaryFacade dictionaryFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(DictionaryDTO dictionaryDTO) {
		return dictionaryFacade.creatDictionary(dictionaryDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(DictionaryDTO dictionaryDTO) {
		return dictionaryFacade.updateDictionary(dictionaryDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page<DictionaryDTO> pageJson(DictionaryDTO dictionaryDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<DictionaryDTO> all = dictionaryFacade.pageQueryDictionary(dictionaryDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        					idArrs[i] = Long.valueOf(value[i]);
			        				        }
        return dictionaryFacade.removeDictionarys(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return dictionaryFacade.getDictionary(id);
	}
	
		
    @InitBinder    
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
	
}
