package dao;

import org.json.JSONArray;
import repository.ProductRepository;
import repository.UserRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class UserDao {
    private UserRepository repository;

    @Inject
    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public JSONArray getById(Long user_id) throws SQLException {
        return repository.getById(user_id);
    }
}
