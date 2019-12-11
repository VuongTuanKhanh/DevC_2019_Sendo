package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserSearchKeyword extends Model {
    @Id
    private String user_id;
    private String keyword;
    private Long time_create;

    public UserSearchKeyword(String user_id, String keyword, Long time_create) {
        this.user_id = user_id;
        this.keyword = keyword;
        this.time_create = time_create;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getTime_create() {
        return time_create;
    }

    public void setTime_create(Long time_create) {
        this.time_create = time_create;
    }
}
