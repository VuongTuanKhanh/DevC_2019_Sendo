package dao;

import org.json.JSONArray;
import repository.UserSearchKeywordRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class UserSearchKeywordDao {
    private UserSearchKeywordRepository repository;

    @Inject
    public UserSearchKeywordDao(UserSearchKeywordRepository repository) {
        this.repository = repository;
    }

    public JSONArray getByUserKeyword(String user_id, String keyword) throws SQLException {
        return repository.getByUserKeyword(user_id, keyword);
    }

    public JSONArray getKeywordByUser(String user_id, int offset, int limit) throws SQLException {
        return repository.getKeywordByUser(user_id, offset, limit);
    }

    public int updateTimeCreate(String user_id, String keyword, Long time_create) throws SQLException {
        return repository.updateTimeCreate(user_id, keyword, time_create);
    }
}
