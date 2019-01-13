package com.installertrackingws.installertrackingws.bean.communication;

import java.util.Date;

public class ConversationBn {

    // hello
    public int id;
    public int conversationId;
    public String speech;
    public int sender;
    public int receiver;
    public int isSeen = 0;
    public Date sendDate;
    public Date seenDate;
    public String ip;

    public ConversationBn() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(int isSeen) {
        this.isSeen = isSeen;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
