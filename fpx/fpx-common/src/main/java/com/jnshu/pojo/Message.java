package com.jnshu.pojo;
import javax.persistence.*;
import java.io.Serializable;
/**
 * tb_message
 * @author L
 */
@Table(name = "tb_message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`push_name`")
    private String pushName;

    @Column(name = "`push_style`")
    private Integer pushStyle;

    @Column(name = "`push_time`")
    private Long pushTime;

    @Column(name = "`push_class`")
    private Integer pushClass;

    private Integer status;

    @Column(name = "`course_id`")
    private Long courseId;

    private String content;

    @Column(name = "`update_at`")
    private Long updateAt;

    @Column(name = "`update_by`")
    private Long updateBy;

    @Column(name = "`creat_at`")
    private Long creatAt;

    @Column(name = "`creat_by`")
    private Long creatBy;

    @Transient
    private Integer read;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }

    public Integer getPushStyle() {
        return pushStyle;
    }

    public void setPushStyle(Integer pushStyle) {
        this.pushStyle = pushStyle;
    }

    public Long getPushTime() {
        return pushTime;
    }

    public void setPushTime(Long pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPushClass() {
        return pushClass;
    }

    public void setPushClass(Integer pushClass) {
        this.pushClass = pushClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pushName='" + pushName + '\'' +
                ", pushStyle=" + pushStyle +
                ", pushTime=" + pushTime +
                ", pushClass=" + pushClass +
                ", status=" + status +
                ", courseId=" + courseId +
                ", content='" + content + '\'' +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", creatAt=" + creatAt +
                ", creatBy=" + creatBy +
                ", read=" + read +
                '}';
    }
}