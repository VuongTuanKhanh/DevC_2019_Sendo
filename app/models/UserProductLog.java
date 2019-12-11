package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProductLog extends Model {
    @Id
    private String user_id;
    private int product_id;

    private int belong_cate_level1_id;
    private String belong_cate_level1_name;

    private int belong_cate_level2_id;
    private String belong_cate_level2_name;

    private int belong_cate_level3_id;
    private String belong_cate_level3_name;
    private Long time_create;

    public UserProductLog(String user_id, int product_id, int belong_cate_level1_id, String belong_cate_level1_name, int belong_cate_level2_id, String belong_cate_level2_name, int belong_cate_level3_id, String belong_cate_level3_name, Long time_create) {
        this.user_id = user_id;
        this.belong_cate_level1_id = belong_cate_level1_id;
        this.belong_cate_level1_name = belong_cate_level1_name;
        this.belong_cate_level2_id = belong_cate_level2_id;
        this.belong_cate_level2_name = belong_cate_level2_name;
        this.belong_cate_level3_id = belong_cate_level3_id;
        this.belong_cate_level3_name = belong_cate_level3_name;
        this.time_create = time_create;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getTime_create() {
        return time_create;
    }

    public void setTime_create(Long time_create) {
        this.time_create = time_create;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getBelong_cate_level1_id() {
        return belong_cate_level1_id;
    }

    public void setBelong_cate_level1_id(int belong_cate_level1_id) {
        this.belong_cate_level1_id = belong_cate_level1_id;
    }

    public String getBelong_cate_level1_name() {
        return belong_cate_level1_name;
    }

    public void setBelong_cate_level1_name(String belong_cate_level1_name) {
        this.belong_cate_level1_name = belong_cate_level1_name;
    }

    public int getBelong_cate_level2_id() {
        return belong_cate_level2_id;
    }

    public void setBelong_cate_level2_id(int belong_cate_level2_id) {
        this.belong_cate_level2_id = belong_cate_level2_id;
    }

    public String getBelong_cate_level2_name() {
        return belong_cate_level2_name;
    }

    public void setBelong_cate_level2_name(String belong_cate_level2_name) {
        this.belong_cate_level2_name = belong_cate_level2_name;
    }

    public int getBelong_cate_level3_id() {
        return belong_cate_level3_id;
    }

    public void setBelong_cate_level3_id(int belong_cate_level3_id) {
        this.belong_cate_level3_id = belong_cate_level3_id;
    }

    public String getBelong_cate_level3_name() {
        return belong_cate_level3_name;
    }

    public void setBelong_cate_level3_name(String belong_cate_level3_name) {
        this.belong_cate_level3_name = belong_cate_level3_name;
    }
}
