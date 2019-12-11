package dao;

import org.json.JSONArray;
import repository.ProductTrendingRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class ProductTrendingDao {
    private ProductTrendingRepository repository;

    @Inject
    public ProductTrendingDao(ProductTrendingRepository repository) {
        this.repository = repository;
    }

    public JSONArray getAll() throws SQLException {
        return repository.getAll();
    }

    public JSONArray getOffet(int offset, int limit) throws SQLException {
        return repository.getOffset(offset, limit);
    }
}
