package controllers;

import Utils.Compress;
import access.UserSearchKeywordAccess;
import com.fasterxml.jackson.databind.JsonNode;
import dao.ProductDao;
import dao.UserSearchKeywordDao;
import mapping.Converter;
import mapping.ResultAPI;
import mapping.ResultCustom;
import models.UserSearchKeyword;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;

//@SubjectPresent
public class UserSearchKeywordController extends BaseController {
    private UserSearchKeywordAccess access;
    private UserSearchKeywordDao dao;
    private ProductDao productDao;
    private ResultCustom resultCustom;
    private Converter converter;

    @Inject
    public UserSearchKeywordController(UserSearchKeywordAccess access, UserSearchKeywordDao dao, ProductDao productDao, Converter converter) {
        this.access = access;
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
            final UserSearchKeyword resource = Json.fromJson(json, UserSearchKeyword.class);
            access.insertUserSearchKeyword(resource.getUser_id(), resource.getKeyword());
            result = resultAPI.jsonString(true, "successful", new JSONObject());
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", new JSONObject());
        }

        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getSearch(String user_id, String keyword, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = productDao.getSearchOffset(keyword, offset, number);
            access.insertUserSearchKeyword(user_id, keyword);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getKeywordByUser(String user_id, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getKeywordByUser(user_id, offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }
}
