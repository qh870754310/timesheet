package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.TimeSettingDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface TimeSettingFacade {

	public InvokeResult getTimeSetting(String id);

	public InvokeResult getTimeSettingByDate(String date);

	public InvokeResult createTimeSetting(TimeSettingDTO timeSetting);
	
	public InvokeResult updateTimeSetting(TimeSettingDTO timeSetting);
	
	public InvokeResult removeTimeSetting(String id);
	
	public InvokeResult removeTimeSettings(String[] ids);
	
	public List<TimeSettingDTO> findAllTimeSetting();
	
	public Page<TimeSettingDTO> pageQueryTimeSetting(TimeSettingDTO timeSetting, int currentPage, int pageSize);


	public List<TimeSettingDTO> getCurrentPageData(String date);

	public List<TimeSettingDTO> getListByDateRange(String dateStart, String dateEnd);
}

