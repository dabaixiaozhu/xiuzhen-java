package com.jnshu.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tb_user
 * @author L
 */
@Table(name = "tb_user")
public class UserSign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @Column(name = "`sign_status`")
    private Integer signStatus;

    @Column(name = "`sign_day`")
    private Integer signDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    @Override
    public String toString() {
        return "UserSign{" +
                "id=" + id +
                ", score=" + score +
                ", signStatus=" + signStatus +
                ", signDay=" + signDay +
                '}';
    }
}
