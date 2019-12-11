package dao;

import org.json.JSONArray;
import repository.UserProductLogRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class UserProductLogDao {
    private UserProductLogRepository repository;

    @Inject
    public UserProductLogDao(UserProductLogRepository repository) {
        this.repository = repository;
    }

    public JSONArray getLog(String user_id, int offset, int limit) throws SQLException {
        return repository.getLog(user_id, offset, limit);
    }
}
