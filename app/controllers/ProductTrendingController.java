package controllers;

import Utils.Compress;
import dao.ProductTrendingDao;
import main.GlobalVariables;
import mapping.ResultAPI;
import mapping.ResultCustom;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;

public class ProductTrendingController extends BaseController {
    private ProductTrendingDao dao;
    private GlobalVariables gv;
    private ResultCustom resultCustom;
    private WSClient ws;
    private HttpExecutionContext httpExecutionContext;

    @Inject
    public ProductTrendingController(ProductTrendingDao dao, GlobalVariables gv, ResultCustom resultCustom, WSClient ws, HttpExecutionContext httpExecutionContext) {
        this.dao = dao;
        this.gv = gv;
        this.resultCustom = resultCustom;
        this.ws = ws;
        this.httpExecutionContext = httpExecutionContext;
    }

    public Result getAll() throws IOException {
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getAll();
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getOffset(int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getOffet(offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }
}
