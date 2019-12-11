package repository;

import main.GlobalVariables;
import mapping.Converter;
import org.json.JSONArray;
import play.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTrendingRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public ProductTrendingRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getAll() throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT product_trending.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product_trending " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product_trending.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product_trending.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product_trending.belong_cate_level3_id " +
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
            String SQLQuery = "SELECT product_trending.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name FROM product_trending " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product_trending.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product_trending.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product_trending.belong_cate_level3_id " +
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
