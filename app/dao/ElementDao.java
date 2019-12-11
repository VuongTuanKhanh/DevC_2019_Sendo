package dao;

import org.json.JSONArray;
import repository.ElementRepository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ElementDao {

    private ElementRepository repository;

    @Inject
    public ElementDao(ElementRepository repository) {
        this.repository = repository;
    }


    public JSONArray select(String element) throws SQLException {
        return repository.select(element);
    }
}
