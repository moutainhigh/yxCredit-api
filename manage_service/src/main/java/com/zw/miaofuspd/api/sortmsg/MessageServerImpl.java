package com.zw.miaofuspd.api.sortmsg;

import com.alibaba.fastjson.JSONObject;
import com.api.model.BYXSettings;
import com.api.model.sortmsg.MsgRequest;
import com.api.model.sortmsg.MsgSettings;
import com.api.service.sortmsg.IMessageServer;
import com.base.util.TemplateUtils;
import com.zw.api.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务实现
 * @author 陈清玉
 */
@Service(IMessageServer.BEAN_KEY)
public class MessageServerImpl implements IMessageServer {

    @Autowired
    private MsgSettings msgSettings;

    @Autowired
    private BYXSettings byxSettings;

    @Override
    public String sendSms(MsgRequest request, Map<String,String> smsParam) throws Exception {
        Map<String,Object> parameter = new HashMap<>(5);
        parameter.put("phone",request.getPhone());
        parameter.put("type",msgSettings.getType());
        parameter.put("channelUniqId",msgSettings.getChannelUniqId());
        final String content = TemplateUtils.getContent(msgSettings.getContent(), smsParam);
        parameter.put("content",content);
        return HttpClientUtil.post(msgSettings.getRequestUrl(),JSONObject.toJSONString(parameter),byxSettings.getHeadRequest());

    }

    /**
     * 根据模板生成短信内容
     * @return
     */
    private String getContent(){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("company","碧友信");
        parameters.put("smsCode","1234");
        return TemplateUtils.getContent(msgSettings.getContent(),parameters);
    }

}
