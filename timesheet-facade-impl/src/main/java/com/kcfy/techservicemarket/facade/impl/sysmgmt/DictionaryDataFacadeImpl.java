package com.kcfy.techservicemarket.facade.impl.sysmgmt;

import com.kcfy.techservicemarket.application.sysmgmt.DictionaryDataApplication;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDataDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt.DictionaryDataAssembler;
import com.kcfy.techservicemarket.facade.sysmgmt.DictionaryDataFacade;
import org.apache.commons.lang.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
public class DictionaryDataFacadeImpl implements DictionaryDataFacade {

	@Inject
	private DictionaryDataApplication application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getDictionaryData(Long id) {
		return InvokeResult.success(DictionaryDataAssembler.toDTO(application.getDictionaryData(id)));
	}
	
	public InvokeResult creatDictionaryData(DictionaryDataDTO dictionaryDataDTO) {
		application.creatDictionaryData(DictionaryDataAssembler.toEntity(dictionaryDataDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateDictionaryData(DictionaryDataDTO dictionaryDataDTO) {
		application.updateDictionaryData(DictionaryDataAssembler.toEntity(dictionaryDataDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeDictionaryData(Long id) {
		application.removeDictionaryData(application.getDictionaryData(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeDictionaryDatas(Long[] ids) {
		Set<DictionaryData> dictionaryDatas= new HashSet<DictionaryData>();
		for (Long id : ids) {
			dictionaryDatas.add(application.getDictionaryData(id));
		}
		application.removeDictionaryDatas(dictionaryDatas);
		return InvokeResult.success();
	}
	
	public List<DictionaryDataDTO> findAllDictionaryData() {
		return DictionaryDataAssembler.toDTOs(application.findAllDictionaryData());
	}
	
	@SuppressWarnings("unchecked")
	public Page<DictionaryDataDTO> pageQueryDictionaryData(DictionaryDataDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _dictionaryData from DictionaryData _dictionaryData   where 1=1 ");
	   	if (queryVo.getId() != null && !"".equals(queryVo.getId())) {
	   		jpql.append(" and _dictionaryData.id = ?");
	   		conditionVals.add(MessageFormat.format("{0}", queryVo.getId()));
	   	}		
	   	if (queryVo.getDictId()!=null) {
	   		jpql.append(" and _dictionaryData.dictionary.id = ?");
	   		conditionVals.add(Long.valueOf(MessageFormat.format("{0}", queryVo.getDictId())));
	   	}		
	   	if (queryVo.getDictdataName() != null && !"".equals(queryVo.getDictdataName())) {
	   		jpql.append(" and _dictionaryData.dictdataName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDictdataName()));
	   	}		
	   	if (queryVo.getDictdataValue() != null && !"".equals(queryVo.getDictdataValue())) {
	   		jpql.append(" and _dictionaryData.dictdataValue like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDictdataValue()));
	   	}		
	   	jpql.append("order by _dictionaryData.dictionary,_dictionaryData.createDate");
        Page<DictionaryData> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<DictionaryDataDTO>(pages.getStart(), pages.getResultCount(),pageSize, DictionaryDataAssembler.toDTOs(pages.getData()));
	}
	
	
}
