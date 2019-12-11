package repository;

import io.ebean.DataIntegrityException;
import main.GlobalVariables;
import mapping.Converter;
import org.json.JSONArray;
import play.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public ProductRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getAll() throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "LIMIT 100 OFFSET 1";

            preparedStatement = conn.prepareStatement(SQLQuery);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getOffset(int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getCategories(int categories_lv1, int categories_lv2, int categories_lv3, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        String query_plus = " WHERE 1 ";
        if (categories_lv1 != -1) {
            query_plus += " AND categories_lv1.id_lv1 = " + categories_lv1 + " ";
        }
        if (categories_lv2 != -1) {
            query_plus += " AND categories_lv2.id_lv2 = " + categories_lv2 + " ";
        }
        if (categories_lv3 != -1) {
            query_plus += " AND categories_lv3.id_lv3 = " + categories_lv3 + " ";
        }
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    query_plus +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getBestCategories(int categories_lv1, int categories_lv2, int categories_lv3, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        String query_plus = " WHERE 1 ";
        if (categories_lv1 != -1) {
            query_plus += " AND categories_lv1.id_lv1 = " + categories_lv1 + " ";
        }
        if (categories_lv2 != -1) {
            query_plus += " AND categories_lv2.id_lv2 = " + categories_lv2 + " ";
        }
        if (categories_lv3 != -1) {
            query_plus += " AND categories_lv3.id_lv3 = " + categories_lv3 + " ";
        }
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    query_plus +
                    "AND RAND() < 0.3" +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getRecommend(int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "AND RAND() < 0.3 " +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public int updateProduct(int product_id, int product_id_update, String name, int price, String image, String image_mob, String cat_path, int shop_id, String shop_name, String category_id, int is_fail, int is_update) throws SQLException {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            String SQLQuery = "UPDATE product SET product_id_update = ?, name = ?, price = ?, image = ?, image_mob = ?, cat_path = ?, shop_id = ?, shop_name = ?, category_id = ?, is_fail = ?, is_update = ? WHERE product_id = ?";
            preparedStatement = conn.prepareStatement(SQLQuery);

            preparedStatement.setInt(1, product_id_update);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, image);
            preparedStatement.setString(5, image_mob);
            preparedStatement.setString(6, cat_path);
            preparedStatement.setInt(7, shop_id);
            preparedStatement.setString(8, shop_name);
            preparedStatement.setString(9, category_id);
            preparedStatement.setInt(10, is_fail);
            preparedStatement.setInt(11, is_update);
            preparedStatement.setInt(12, product_id);

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            preparedStatement.close();
        }

        return result;
    }

    public int updateProductCancel(int product_id) throws SQLException {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            String SQLQuery = "UPDATE product SET is_cancel = 1 WHERE product_id = ?";
            preparedStatement = conn.prepareStatement(SQLQuery);

            preparedStatement.setInt(1, product_id);

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            preparedStatement.close();
        }

        return result;
    }

    public JSONArray getSearchOffset(String keyword, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "WHERE  product.name like ? " +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            preparedStatement.setString(1, "%" + keyword + "%");

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getById(int product_id) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "WHERE product.product_id = ? ";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, product_id);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getByMonth(int month, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        offset = month * 100 + offset;
        try {
            String SQLQuery = "SELECT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }
}
