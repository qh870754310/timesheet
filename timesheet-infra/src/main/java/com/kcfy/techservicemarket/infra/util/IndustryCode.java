package com.kcfy.techservicemarket.infra.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum IndustryCode {

        IT_NETWORK("IT科技","互联网/电子商务", 1),
        IT_SOFTWARE("IT科技","IT软件与服务", 2),
        IT_HARDWARE("IT科技","IT硬件与设备",3),
        IT_ELECTRONIC("IT科技","电子技术",4),
        IT_COMMUNICATE("IT科技","通信与运营商",5),
        IT_GAME("IT科技","网络游戏",6);


        private String primary;
        private String second;
        private int code;

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private IndustryCode(String primary, String second, Integer code) {
            this.primary = primary;
            this.second = second;
            this.code = code;
        }

        public static IndustryCode valueOf(Integer code) {
            if (code == null) {
                return null;
            } else {
                for (IndustryCode item : values()) {
                    if (item.getCode() == code) {
                        return item;
                    }
                }
                return null;
            }
        }
    public static Map<String, Integer> getAll() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (IndustryCode industryCode : IndustryCode.values()) {
            map.put(industryCode.getPrimary() + "_" + industryCode.getSecond(), industryCode.getCode());
        }
        return map;
    }
    }