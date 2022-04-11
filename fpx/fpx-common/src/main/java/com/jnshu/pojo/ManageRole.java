package com.jnshu.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tb_manage_role
 * @author L
 */
@Table(name = "tb_manage_role")
public class ManageRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`manageId`")
    private Long manageId;
    @Column(name = "`roleId`")
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageId) {
        this.manageId = manageId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ManageRole{" +
                "id=" + id +
                ", manageId=" + manageId +
                ", roleId=" + roleId +
                '}';
    }
}
