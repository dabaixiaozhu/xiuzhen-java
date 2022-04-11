package com.jnshu.pojo;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * 创建人 G
 * tb_banner
 * @author 
 */
public class Banner implements Serializable {
    /**
     * 主键自增
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * banner图链接
     */
    private String url;

    private Long courseId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id) &&
                Objects.equals(title, banner.title) &&
                Objects.equals(url, banner.url) &&
                Objects.equals(courseId, banner.courseId) &&
                Objects.equals(status, banner.status) &&
                Objects.equals(updateAt, banner.updateAt) &&
                Objects.equals(updateBy, banner.updateBy) &&
                Objects.equals(creatAt, banner.creatAt) &&
                Objects.equals(creatBy, banner.creatBy) &&
                Objects.equals(creatName, banner.creatName) &&
                Objects.equals(updateName, banner.updateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url, courseId, status, updateAt, updateBy, creatAt, creatBy, creatName, updateName);
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", courseId=" + courseId +
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