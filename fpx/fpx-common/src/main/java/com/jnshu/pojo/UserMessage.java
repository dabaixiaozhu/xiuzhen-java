package com.jnshu.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tb_user_message
 * @author L
 */
@Table(name = "tb_user_message")
public class UserMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`userId`")
    private Long userId;
    @Column(name = "`messageId`")
    private Long messageId;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", messageId=" + messageId +
                ", status=" + status +
                '}';
    }
}
