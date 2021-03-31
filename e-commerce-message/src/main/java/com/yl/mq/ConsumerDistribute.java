package com.yl.mq;

import com.alibaba.fastjson.JSONObject;
import com.yl.adapter.MessageAdapter;
import com.yl.constants.Constants;
import com.yl.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerDistribute {

  @Autowired
  private EmailService emailService;
  private MessageAdapter messageAdapter;

  @JmsListener(destination = "message_queue")
  public void distribute(String json) {
    log.info("#####消息服务平台接受消息内容:{}#####", json);
    if (StringUtils.isEmpty(json))
      return;
    JSONObject rootJson = JSONObject.parseObject(json);
    JSONObject header = rootJson.getJSONObject(Constants.MSG_HEADER);
    String interfaceType = header.getString("interfaceType");

    if (StringUtils.isEmpty(interfaceType))
      return;
    //判断接口类型是否为发邮件
    if (Constants.MSG_EMAIL.equals(interfaceType)) {
      messageAdapter = emailService;
    }
    if (messageAdapter == null)
      return;

    JSONObject contentJson = rootJson.getJSONObject(Constants.MSG_CONTENT);
    messageAdapter.sendMsg(contentJson);
  }

}
