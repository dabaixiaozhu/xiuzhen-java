package com.jnshu.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author admin
 */
public class CourseUser implements Serializable {

    private Long id;
    private Long userId;
    private Long courseId;
    private Integer purchased;
    private Integer collect;
    private Long creatAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Integer getPurchased() {
        return purchased;
    }

    public Integer getCollect() {
        return collect;
    }

    public Long getCreatAt() {
        return creatAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public void setCreatAt(Long creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseUser that = (CourseUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(purchased, that.purchased) &&
                Objects.equals(collect, that.collect) &&
                Objects.equals(creatAt, that.creatAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, courseId, purchased, collect, creatAt);
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", purchased=" + purchased +
                ", collect=" + collect +
                ", creatAt=" + creatAt +
                '}';
    }
}
