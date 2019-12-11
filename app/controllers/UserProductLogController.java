package controllers;

import Utils.Compress;
import com.fasterxml.jackson.databind.JsonNode;
import dao.ProductDao;
import dao.UserProductLogDao;
import mapping.Converter;
import mapping.ResultAPI;
import mapping.ResultCustom;
import models.UserProductLog;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

//@SubjectPresent
public class UserProductLogController extends BaseController {
    private UserProductLogDao dao;
    private ProductDao productDao;
    private ResultCustom resultCustom;
    private Converter converter;

    @Inject
    public UserProductLogController(UserProductLogDao dao, ProductDao productDao, Converter converter) {
        this.dao = dao;
        this.productDao = productDao;
        this.converter = converter;
    }

    public Result insert() throws SQLException, IOException {
        JsonNode json = request().body().asJson();
        String result = "";
        ResultAPI resultAPI = new ResultAPI();
        if (json == null) {
            Logger.error("user json body is null");
            result = resultAPI.jsonString(false, "user json body is null", new JSONObject());
            setHeaderResponse(result.length());
            return (ok(Compress.compress(result)).as("application/json"));
        }

        try {
            final UserProductLog resource = Json.fromJson(json, UserProductLog.class);
            resource.setTime_create(new Date().getTime());
            resource.insert();
            result = resultAPI.jsonString(true, "successful", new JSONObject());
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", new JSONObject());
        }

        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getLog(String user_id, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getLog(user_id, offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

}
