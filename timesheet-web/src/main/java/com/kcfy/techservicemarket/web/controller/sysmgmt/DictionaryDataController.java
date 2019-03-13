package com.kcfy.techservicemarket.web.controller.sysmgmt;

import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDataDTO;
import com.kcfy.techservicemarket.facade.sysmgmt.DictionaryDataFacade;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/DictionaryData")
public class DictionaryDataController {
		
	@Inject
	private DictionaryDataFacade dictionaryDataFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(DictionaryDataDTO dictionaryDataDTO) {
		return dictionaryDataFacade.creatDictionaryData(dictionaryDataDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(DictionaryDataDTO dictionaryDataDTO) {
		return dictionaryDataFacade.updateDictionaryData(dictionaryDataDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page<DictionaryDataDTO> pageJson(DictionaryDataDTO dictionaryDataDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<DictionaryDataDTO> all = dictionaryDataFacade.pageQueryDictionaryData(dictionaryDataDTO, page, pagesize);
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
        return dictionaryDataFacade.removeDictionaryDatas(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return dictionaryDataFacade.getDictionaryData(id);
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
