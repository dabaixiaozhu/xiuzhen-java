package com.jnshu.pojo;
import javax.persistence.*;
import java.io.Serializable;
/**
 * tb_role
 * @author L
 */
@Table(name = "tb_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "`update_at`")
    private Long updateAt;

    @Column(name = "`update_by`")
    private Long updateBy;

    @Column(name = "`creat_at`")
    private Long creatAt;

    @Column(name = "`creat_by`")
    private Long creatBy;

    @Transient
    private String creatName;

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", creatAt=" + creatAt +
                ", creatBy=" + creatBy +
                ", creatName='" + creatName + '\'' +
                '}';
    }
}