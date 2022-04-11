package com.jnshu.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * @author L
 */
public class RoleCombine implements Serializable {

    private Long id;

    private String role;

    private List<Permission> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "RoleCombine{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", children=" + children +
                '}';
    }
}
