package com.kcfy.techservicemarket.infra.wso2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openkoala.koala.commons.InvokeResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouxc on 2016/5/9.
 */
public class WSO2Util {
    private static final Log LOG = LogFactory.getLog(WSO2Util.class);

    private static String dataServer;
    private static String wso2key;
    private static String wso2value;
    private static Map<String, String> wso2tokenMap = new HashMap<>();

    private static String getDataServer() {
        if (StringUtils.isEmpty(dataServer)) {
            dataServer = "http://192.168.21.96:8280/wei/1.0/IWork/message/send";
        }
        return dataServer;
    }

    private static String getWso2Info() {
        if (StringUtils.isEmpty(wso2key)) {
            wso2key = "wso2";
        }
        if (StringUtils.isEmpty(wso2value)) {
            wso2value = "1qaz2wsx";
        }
        return wso2key + "=" + wso2value;
    }

    private static Map getWso2Token() {
        //"Authorization", "Bearer " + "567915788025607c52df9c288a624061"
        if (wso2tokenMap.get("Authorization") == null) {
//            String wso2token = TokenGen.getToken();
//            LOG.info("wso2 token:" + wso2token);
            wso2tokenMap.put("Authorization", "Bearer " + "81ff4e92da4f1b9e615deaf432fcbb68");
        }
        return wso2tokenMap;
    }

    public static InvokeResult sendMsg(String userId, String content, String url) {
        String result = HttpRequestUtil.executeRquest(getDataServer(),
                getWso2Info() + "&userId=" + userId + "&content=" + content + "&url=" + url,
                HttpRequestUtil.POST, getWso2Token(), null, "utf-8", null, null, null);
        InvokeResult invokeResult = null;
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        if (jsonObject.get("errcode") != null && "0".equals(jsonObject.get("errcode").toString())) {
            invokeResult = InvokeResult.success();
        } else {
            invokeResult = InvokeResult.failure(jsonObject.get("errmsg") != null ? jsonObject.get("errmsg").toString() : "通知发送失败");
        }
        return invokeResult;
    }

}
