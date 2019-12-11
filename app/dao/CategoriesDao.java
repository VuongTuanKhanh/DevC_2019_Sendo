package dao;

import org.json.JSONArray;
import repository.CategoriesRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class CategoriesDao {
    private CategoriesRepository repository;

    @Inject
    public CategoriesDao(CategoriesRepository repository) {
        this.repository = repository;
    }

    public JSONArray getCategoriesLv1() throws SQLException {
        return repository.getCategoriesLv1();
    }

    public JSONArray getCategoriesLv2() throws SQLException {
        return repository.getCategoriesLv2();
    }

    public JSONArray getCategoriesLv3() throws SQLException {
        return repository.getCategoriesLv3();
    }
}
