package mapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Converter {
    private ObjectMapper mapper = new ObjectMapper();
    Converter(){
        ObjectMapper mapper = new ObjectMapper();
    }

    public static JSONArray convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                // if value in DB is null, then we set it to default value
                if (columnValue == null){
                    columnValue = "null";
                }
                /*
                Next if block is a hack. In case when in db we have values like price and price1 there's a bug in jdbc -
                both this names are getting stored as price in ResulSet. Therefore when we store second column value,
                we overwrite original value of price. To avoid that, i simply add 1 to be consistent with DB.
                 */
                if (obj.has(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static int converBooleanIntoInt(boolean bool){
        if (bool) return 1;
        else return 0;
    }

    public static int convertBooleanStringIntoInt(String bool){
        if (bool.equals("false")) return 0;
        else if (bool.equals("true")) return 1;
        else {
            throw new IllegalArgumentException("wrong value is passed to the method. Value is "+bool);
        }
    }

    public static double getDoubleOutOfString(String value, String format, Locale locale){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat(format,otherSymbols);
        String formattedValue = f.format(Double.parseDouble(value));
        double number = Double.parseDouble(formattedValue);
        return  Math.round(number * 100.0) / 100.0;
    }

    public String listObjectToString(List<Object> listObject){
        Gson gson = new Gson();
        String result = gson.toJson(listObject);
        gson = null;
        return result;
    }

    public JSONArray listObjectToJSONArray(List<Object> listObject){
        Gson gson = new Gson();
        String result = gson.toJson(listObject);
        gson = null;
        return new JSONArray(result);
    }

    public List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

    public <T> List<T> resultSetToList(Class<T> model, ResultSet resultSet) throws SQLException, IOException {
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                // if value in DB is null, then we set it to default value
                if (columnValue == null){
                    columnValue = "null";
                }
                if (obj.has(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            //convert T to model Object
            T myObjects = mapper.readValue(obj.toString(), model);
            //add to List
            list.add(myObjects);
        }

        return list;
    }
}
