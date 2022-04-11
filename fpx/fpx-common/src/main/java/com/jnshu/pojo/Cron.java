package com.jnshu.pojo;
import javax.persistence.*;
import java.io.Serializable;
/**
 * tb_cron
 * @author L
 */
@Table(name = "tb_cron")
public class Cron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Cron{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}