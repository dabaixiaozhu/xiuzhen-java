package com.jnshu.pojo;
import javax.persistence.*;
import java.io.Serializable;
/**
 * tb_user
 * @author L
 */
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String headimgurl;

    private Integer status;

    private Integer old;

    private String openid;

    private Integer score;

    @Column(name = "`login_status`")
    private Integer loginStatus;

    @Column(name = "`sign_status`")
    private Integer signStatus;

    @Column(name = "`sign_day`")
    private Integer signDay;

    @Column(name = "`userClass`")
    private Integer userClass;

    @Column(name = "`update_at`")
    private Long updateAt;

    @Column(name = "`update_by`")
    private Long updateBy;

    @Column(name = "`creat_at`")
    private Long creatAt;

    @Column(name = "`creat_by`")
    private Long creatBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    public Integer getSignDay() {
        return signDay;
    }

    public void setSignDay(Integer signDay) {
        this.signDay = signDay;
    }

    public Integer getUserClass() {
        return userClass;
    }

    public void setUserClass(Integer userClass) {
        this.userClass = userClass;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", status=" + status +
                ", old=" + old +
                ", openid='" + openid + '\'' +
                ", score=" + score +
                ", loginStatus=" + loginStatus +
                ", signStatus=" + signStatus +
                ", signDay=" + signDay +
                ", userClass=" + userClass +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", creatAt=" + creatAt +
                ", creatBy=" + creatBy +
                '}';
    }
}