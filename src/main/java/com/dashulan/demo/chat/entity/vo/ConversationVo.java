package com.dashulan.demo.chat.entity.vo;

import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.utils.TimeUtil;
import lombok.Data;

import java.util.List;

@Data
public class ConversationVo {
    private Long cid;
    private String conversationTitle;
    private String lastSentTime;
    private String caption;
    private String avatar;

    public ConversationVo(Conversation conversation) {
        this.cid = conversation.getId();
        this.setLastSentTime(TimeUtil.localDateTimeToString(conversation.getLastChatAt()));
    }


    public ConversationVo() {
    }
}
