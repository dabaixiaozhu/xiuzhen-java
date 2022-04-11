package com.jnshu.pojo;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * 创建人 G
 * tb_notice
 * @author 
 */
public class Notice implements Serializable {
    /**
     * 主键自增
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 状态0下架 1上架
     */
    private Integer status;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新者的id
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private Long creatAt;

    /**
     * 创建者的id
     */
    private Long creatBy;
    private String creatName;
    private String updateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Long creatAt) {
        this.creatAt = creatAt;
    }

    public Long getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(Long creatBy) {
        this.creatBy = creatBy;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notice notice = (Notice) o;
        return Objects.equals(id, notice.id) &&
                Objects.equals(title, notice.title) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(status, notice.status) &&
                Objects.equals(updateAt, notice.updateAt) &&
                Objects.equals(updateBy, notice.updateBy) &&
                Objects.equals(creatAt, notice.creatAt) &&
                Objects.equals(creatBy, notice.creatBy) &&
                Objects.equals(creatName, notice.creatName) &&
                Objects.equals(updateName, notice.updateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status, updateAt, updateBy, creatAt, creatBy, creatName, updateName);
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", creatAt=" + creatAt +
                ", creatBy=" + creatBy +
                ", creatName='" + creatName + '\'' +
                ", updateName='" + updateName + '\'' +
                '}';
    }
}