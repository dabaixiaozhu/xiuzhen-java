package com.jnshu.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author admin
 */
public class UserCourse implements Serializable {

    /**
     * 主键自增
     */
    private Long id;

    /**
     * 课程标题
     */
    private Long userId;
    private String title;

    /**
     * 所属年级
     */
    private String grade;

    /**
     * 科目
     */
    private String subject;

    /**
     * 收费类型0免费 1收费
     */
    private Integer type;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 课程logo
     */
    private String img;

    /**
     * 课程视频
     */
    private String vedio;

    /**
     * 课程简介
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
    private Integer purchased;
    private Integer collect;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getGrade() {
        return grade;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public String getVedio() {
        return vedio;
    }

    public String getContent() {
        return content;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public Long getCreatAt() {
        return creatAt;
    }

    public Long getCreatBy() {
        return creatBy;
    }

    public Integer getPurchased() {
        return purchased;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public void setCreatAt(Long creatAt) {
        this.creatAt = creatAt;
    }

    public void setCreatBy(Long creatBy) {
        this.creatBy = creatBy;
    }

    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCourse that = (UserCourse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(img, that.img) &&
                Objects.equals(vedio, that.vedio) &&
                Objects.equals(content, that.content) &&
                Objects.equals(status, that.status) &&
                Objects.equals(updateAt, that.updateAt) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(creatAt, that.creatAt) &&
                Objects.equals(creatBy, that.creatBy) &&
                Objects.equals(purchased, that.purchased) &&
                Objects.equals(collect, that.collect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, grade, subject, type, price, img, vedio, content, status, updateAt, updateBy, creatAt, creatBy, purchased, collect);
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", grade=" + grade +
                ", subject=" + subject +
                ", type=" + type +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", vedio='" + vedio + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", creatAt=" + creatAt +
                ", creatBy=" + creatBy +
                ", purchased=" + purchased +
                ", collect=" + collect +
                '}';
    }
}
