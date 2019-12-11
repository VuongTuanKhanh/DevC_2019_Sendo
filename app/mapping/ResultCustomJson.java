package mapping;

import io.ebean.Model;
import org.json.JSONObject;

public class ResultCustomJson extends Model {
    private boolean success;
    private String message;
    private JSONObject data;

    public ResultCustomJson() {
    }

    public ResultCustomJson(boolean success, String message, JSONObject data) {
        this.success = success;
        this.message = message;
        this.data = data;
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

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
