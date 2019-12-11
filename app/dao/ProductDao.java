package dao;

import org.json.JSONArray;
import repository.ProductRepository;

import javax.inject.Inject;
import java.sql.SQLException;

public class ProductDao {
    private ProductRepository repository;

    @Inject
    public ProductDao(ProductRepository repository) {
        this.repository = repository;
    }

    public JSONArray getAll() throws SQLException {
        return repository.getAll();
    }

    public JSONArray getOffet(int offset, int limit) throws SQLException {
        return repository.getOffset(offset, limit);
    }

    public JSONArray getCategories(int categories_lv1, int categories_lv2, int categories_lv3, int offset, int limit) throws SQLException {
        return repository.getCategories(categories_lv1, categories_lv2, categories_lv3, offset, limit);
    }

    public JSONArray getRecommend(int offset, int limit) throws SQLException {
        return repository.getRecommend(offset, limit);
    }

    public JSONArray getBestCategories(int categories_lv1, int categories_lv2, int categories_lv3, int offset, int limit) throws SQLException {
        return repository.getBestCategories(categories_lv1, categories_lv2, categories_lv3, offset, limit);
    }

    public JSONArray getSearchOffset(String keyword, int offset, int limit) throws SQLException {
        return repository.getSearchOffset(keyword, offset, limit);
    }

    public int updateProduct(int product_id, int product_id_update, String name, int price, String image, String image_mob, String cat_path, int shop_id, String shop_name, String category_id, int is_fail, int is_update) throws SQLException {
        return repository.updateProduct(product_id, product_id_update, name, price, image, image_mob, cat_path, shop_id, shop_name, category_id, is_fail, is_update);
    }

    public int updateProductCancel(int product_id) throws SQLException {
        return repository.updateProductCancel(product_id);
    }

    public JSONArray getById(int product_id) throws SQLException {
        return repository.getById(product_id);
    }

    public JSONArray getByMonth(int month, int offset, int limit) throws SQLException {
        return repository.getByMonth(month, offset, limit);
    }
}
