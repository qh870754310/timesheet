package com.kcfy.techservicemarket.infra.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/27.
 */
public enum TimeSettingDayType {
    WORKDAY("工作日", 1), WEEKEND("非工作日", 2);
    private String name;
    private int value;

    private TimeSettingDayType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue() + "_" + this.getName();
    }

    public static Map<String, Integer> getAllTimeSettingDayTypeMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (TimeSettingDayType dayType : TimeSettingDayType.values()) {
            map.put(dayType.getName(), dayType.getValue());
        }
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
