package com.jnshu.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * tb_permission
 * @author L
 */
@Table(name = "tb_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long sid;

    private String name;

    @Column(name = "`parent_id`")
    private Long parentId;

    private List<Permission> roleChildren;

    private List<PermissionSon> welcomeChildren;


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Permission> getRoleChildren() {
        return roleChildren;
    }

    public void setRoleChildren(List<Permission> roleChildren) {
        this.roleChildren = roleChildren;
    }

    public List<PermissionSon> getWelcomeChildren() {
        return welcomeChildren;
    }

    public void setWelcomeChildren(List<PermissionSon> welcomeChildren) {
        this.welcomeChildren = welcomeChildren;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + sid +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", roleChildren=" + roleChildren +
                ", welcomeChildren=" + welcomeChildren +
                '}';
    }
}