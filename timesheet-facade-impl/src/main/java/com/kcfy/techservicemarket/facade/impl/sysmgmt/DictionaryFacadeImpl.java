package com.kcfy.techservicemarket.facade.impl.sysmgmt;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.kcfy.techservicemarket.application.sysmgmt.DictionaryApplication;
import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt.DictionaryAssembler;
import com.kcfy.techservicemarket.facade.sysmgmt.DictionaryFacade;

@Named
public class DictionaryFacadeImpl implements DictionaryFacade {

	@Inject
	private DictionaryApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getDictionary(Long id) {
		return InvokeResult.success(DictionaryAssembler.toDTO(application.getDictionary(id)));
	}
	
	public InvokeResult creatDictionary(DictionaryDTO dictionaryDTO) {
		application.creatDictionary(DictionaryAssembler.toEntity(dictionaryDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateDictionary(DictionaryDTO dictionaryDTO) {
		application.updateDictionary(DictionaryAssembler.toEntity(dictionaryDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeDictionary(Long id) {
		application.removeDictionary(application.getDictionary(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeDictionarys(Long[] ids) {
		Set<Dictionary> dictionarys= new HashSet<Dictionary>();
		for (Long id : ids) {
			dictionarys.add(application.getDictionary(id));
		}
		application.removeDictionarys(dictionarys);
		return InvokeResult.success();
	}
	
	public List<DictionaryDTO> findAllDictionary() {
		return DictionaryAssembler.toDTOs(application.findAllDictionary());
	}
	
	@SuppressWarnings("unchecked")
	public Page<DictionaryDTO> pageQueryDictionary(DictionaryDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _dictionary from Dictionary _dictionary   where 1=1 ");
	   	if (queryVo.getId() != null && !"".equals(queryVo.getId())) {
	   		jpql.append(" and _dictionary.id like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getId()));
	   	}		
	   	if (queryVo.getDictName() != null && !"".equals(queryVo.getDictName())) {
	   		jpql.append(" and _dictionary.dictName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDictName()));
	   	}		
        Page<Dictionary> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<DictionaryDTO>(pages.getStart(), pages.getResultCount(),pageSize, DictionaryAssembler.toDTOs(pages.getData()));
	}
	
	
}
