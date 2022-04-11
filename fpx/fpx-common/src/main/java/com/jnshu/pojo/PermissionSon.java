package com.jnshu.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tb_role
 * @author L
 */
@Table(name = "tb_permission")
public class PermissionSon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String route;

    @Column(name = "`parent_id`")
    private Long parentId;

    @Transient
    private String sid;

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "PermissionSon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", route='" + route + '\'' +
                ", parentId=" + parentId +
                ", sonid='" + sid + '\'' +
                '}';
    }
}
