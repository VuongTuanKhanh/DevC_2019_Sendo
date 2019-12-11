package dao;

import org.json.JSONArray;
import repository.ProductRepository;
import repository.ProductSaleRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class ProductSaleDao {
    private ProductSaleRepository repository;

    @Inject
    public ProductSaleDao(ProductSaleRepository repository) {
        this.repository = repository;
    }

    public JSONArray getAll() throws SQLException {
        return repository.getAll();
    }

    public JSONArray getOffet(int offset, int limit) throws SQLException {
        return repository.getOffset(offset, limit);
    }
}
