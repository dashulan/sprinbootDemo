package com.dashulan.demo.chat.utils;

import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.entity.vo.ClientMessage;

import java.sql.Time;

public class MessageFormatUtil {

    public static Message clientMessage2Message(ClientMessage message) {
        Message m = new Message();
        m.setCId(message.getConversationId());
        m.setUId(message.getUid());
        m.setText(message.getText());
        m.setSentTime(TimeUtil.stringToLocalDateTime(message.getSent()));
        return m;
    }

    public static ClientMessage Message2clientMessage(Message message) {
        ClientMessage cm  = new ClientMessage();
        cm.setConversationId(message.getCId());
        cm.setSent(TimeUtil.localDateTimeToString(message.getSentTime()));
        cm.setText(message.getText());
        cm.setUid(message.getUId());
        return cm;
    }
}
