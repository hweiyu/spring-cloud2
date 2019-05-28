package com.imin.adminweb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imin.infrastructure.common.utils.DateUtil;
import com.imin.infrastructure.common.utils.HttpUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/3/6 11:19
 **/
@Slf4j
public class WechatUtil {

    private static final String APP_ID = "wx005a856fc67da4a1";

    private static final String SECRET = "01ed26c5fef0841d097306a8fda92712";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APP_ID&secret=SECRET";

    private static final String MESSAGE_CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    private static final String TEMPLATE_MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public static String getAccessToken() {
        String response = HttpUtil.get(ACCESS_TOKEN_URL.replace("APP_ID", APP_ID).replace("SECRET", SECRET));
        log.info("wechat获取accessToken, response:{}", response);
        if (null != response) {
            JSONObject json = JSON.parseObject(response);
            return json.getString("access_token");
        }
        return null;
    }

    /**
     * 客户消息接口，需要当前openId对应的账号跟公众号在48小时内有交互，才能发送成功
     * @param openId
     * @param context
     * @param accessToken
     */
    public static void sendMessage(String openId, String context, String accessToken) {
        if (StringUtil.isEmptyOrNull(openId) || StringUtil.isEmptyOrNull(context) || StringUtil.isEmptyOrNull(accessToken)) {
            log.error("必填参数为空，openId:{}, context:{}, accessToken:{}", openId, context, accessToken);
            return;
        }
        log.info("当前openId:{}, 内容:{}", openId, context);
        JSONObject json = createObject(
                "touser", openId,
                "msgtype", "text",
                "text", createObject("content", context));
        String response = HttpUtil.post(MESSAGE_CUSTOM_SEND_URL.replace("ACCESS_TOKEN", accessToken), json.toJSONString());
        log.info("发送微信消息，返回信息:{}", response);
    }

    public static void sendMessageByTemplate(String openId, String person, String accessToken, String templateId) {
        if (StringUtil.isEmptyOrNull(openId) || StringUtil.isEmptyOrNull(templateId)
                || StringUtil.isEmptyOrNull(accessToken)) {
            log.error("必填参数为空，openId:{}, templateId:{}, accessToken:{}",
                    openId, templateId, accessToken);
            return;
        }
        log.info("当前openId:{}, templateId:{}", openId, templateId);
        JSONObject json = createObject(
                "touser", openId,
                "template_id", templateId,
                "data", createObject(
                        "first", createObject(
                                "value", "您有一个待办事宜，请尽快处理：",
                                "color", "#173177"),
                        "keyword1", createObject(
                                "value", "iMin云平台",
                                "color", "#173177"),
                        "keyword2", createObject(
                                "value", "代理商 " + person + " 提交了应用定制申请",
                                "color", "#173177"),
                        "keyword3", createObject(
                                "value", DateUtil.getDateTime(),
                                "color", "#173177"),
                        "keyword4", createObject(
                                "value", "-",
                                "color", "#173177"),
                        "remark", createObject(
                                "value", "请尽快登陆系统处理",
                                "color", "#173177")
                ));
        String response = HttpUtil.post(TEMPLATE_MESSAGE_SEND_URL.replace("ACCESS_TOKEN", accessToken), json.toJSONString());
        log.info("发送微信模板消息，返回信息:{}", response);
    }

    private static JSONObject createObject(String k, Object v) {
        JSONObject json = new JSONObject();
        json.put(k, v);
        return json;
    }

    private static JSONObject createObject(String k, Object v, String k2, Object v2) {
        JSONObject json = createObject(k, v);
        json.put(k2, v2);
        return json;
    }

    private static JSONObject createObject(String k, Object v, String k2, Object v2, String k3, Object v3) {
        JSONObject json = createObject(k, v, k2, v2);
        json.put(k3, v3);
        return json;
    }

    private static JSONObject createObject(String k, Object v, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        JSONObject json = createObject(k, v, k2, v2, k3, v3);
        json.put(k4, v4);
        return json;
    }

    private static JSONObject createObject(String k, Object v, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5) {
        JSONObject json = createObject(k, v, k2, v2, k3, v3, k4, v4);
        json.put(k5, v5);
        return json;
    }

    private static JSONObject createObject(String k, Object v, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6) {
        JSONObject json = createObject(k, v, k2, v2, k3, v3, k4, v4, k5, v5);
        json.put(k6, v6);
        return json;
    }

}
