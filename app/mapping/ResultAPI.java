package mapping;

import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Model;
import org.json.JSONArray;
import org.json.JSONObject;

public class ResultAPI extends Model {
    private boolean success;
    private String message;
    private String data;

    public ResultAPI() {
    }

    public ResultAPI(boolean success, String message, String data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String jsonString(boolean success, String message, JSONObject data){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("success", success);
        jsonObject.put("message", message);
        jsonObject.put("data", data);

        return jsonObject.toString();
    }

    public String jsonString(boolean success, String message){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("success", success);
        jsonObject.put("message", message);
        jsonObject.put("data", "");

        return jsonObject.toString();
    }

    public String jsonString(boolean success, String message, JSONArray data){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("success", success);
        jsonObject.put("message", message);
        jsonObject.put("data", data);

        return jsonObject.toString();
    }

    public String jsonString(boolean success, String message, JsonNode data){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("success", success);
        jsonObject.put("message", message);
        jsonObject.put("data", data);

        return jsonObject.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
