package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.facade.TimeSettingFacade;
import com.kcfy.techservicemarket.facade.dto.TimeSettingDTO;
import org.junit.Assert;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class TimeSettingFacadeTest extends KoalaBaseSpringTestCase {
    @Inject
    private TimeSettingFacade timeSettingFacade;

    @Test
    public void getListByDateRangeTest() {
        List<TimeSettingDTO> list = timeSettingFacade.getListByDateRange("2016-04-01", "2016-04-30");
        Assert.assertEquals("a","a");
    }
}
