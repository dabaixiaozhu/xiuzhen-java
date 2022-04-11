package com.jnshu.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 创建人 G
 * tb_course
 * @author 
 */
@Table(name = "tb_course")
public class Course implements Serializable {
    /**
     * 主键自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课程标题
     */
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
    @Column(name = "`type`")
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
    @Column(name = "`status`")
    private Integer status;

    /**
     * 更新时间
     */
    @Column(name = "`update_at`")
    private Long updateAt;

    /**
     * 更新者的id
     */
    @Column(name = "`update_by`")
    private Long updateBy;

    /**
     * 创建时间
     */
    @Column(name = "`creat_at`")
    private Long creatAt;

    /**
     * 创建者的id
     */
    @Column(name = "`creat_by`")
    private Long creatBy;
    @Column(name = "`creat_name`")
    private String creatName;
    @Column(name = "`update_name`")
    private String updateName;

    private static final long serialVersionUID = 1L;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
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

    public String getUpdateName() {
        return updateName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getVedio() == null ? other.getVedio() == null : this.getVedio().equals(other.getVedio()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getCreatAt() == null ? other.getCreatAt() == null : this.getCreatAt().equals(other.getCreatAt()))
            && (this.getCreatBy() == null ? other.getCreatBy() == null : this.getCreatBy().equals(other.getCreatBy()))
            && (this.getCreatName() == null ? other.getCreatName() == null : this.getCreatName().equals(other.getCreatName()))
            && (this.getUpdateName() == null ? other.getUpdateName() == null : this.getUpdateName().equals(other.getUpdateName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getVedio() == null) ? 0 : getVedio().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreatAt() == null) ? 0 : getCreatAt().hashCode());
        result = prime * result + ((getCreatBy() == null) ? 0 : getCreatBy().hashCode());
        result = prime * result + ((getCreatName() == null) ? 0 : getCreatName().hashCode());
        result = prime * result + ((getUpdateName() == null) ? 0 : getUpdateName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", grade=").append(grade);
        sb.append(", subject=").append(subject);
        sb.append(", type=").append(type);
        sb.append(", price=").append(price);
        sb.append(", img=").append(img);
        sb.append(", vedio=").append(vedio);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", creatAt=").append(creatAt);
        sb.append(", creatBy=").append(creatBy);
        sb.append(", creatName=").append(creatName);
        sb.append(", updateName=").append(updateName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}