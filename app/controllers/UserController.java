package controllers;

import Utils.Compress;
import be.objectify.deadbolt.java.actions.Dynamic;
import com.fasterxml.jackson.databind.JsonNode;
import dao.UserDao;
import mapping.Converter;
import mapping.ResultAPI;
import mapping.ResultCustom;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;

//@SubjectPresent
public class UserController extends BaseController {
    private UserDao dao;
    private ResultCustom resultCustom;
    private Converter converter;

    @Inject
    public UserController(UserDao dao, Converter converter) {
        this.dao = dao;
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
            final User resource = Json.fromJson(json, User.class);
            JSONArray jsonArrayUserId = dao.getById(resource.getUser_id());

            if(jsonArrayUserId.length() > 0){
                result = resultAPI.jsonString(false, "Account already exists!", new JSONObject());
            }else {
                resource.insert();
                result = resultAPI.jsonString(true, "successful", new JSONObject());
            }
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", new JSONObject());
        }

        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }
}
