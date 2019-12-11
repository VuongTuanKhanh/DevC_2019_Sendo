package controllers;

import Utils.Compress;
import dao.CategoriesDao;
import dao.ProductDao;
import main.GlobalVariables;
import mapping.ResultAPI;
import mapping.ResultCustom;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.mvc.Result;
import thread.GetInfoProductSendo;

import javax.inject.Inject;
import java.io.IOException;

public class CategoriesController extends BaseController {
    private CategoriesDao dao;
    private GlobalVariables gv;
    private ResultCustom resultCustom;
    private WSClient ws;
    private GetInfoProductSendo getInfoProductSendo;
    private int count = 0;
    private HttpExecutionContext httpExecutionContext;


    @Inject
    public CategoriesController(CategoriesDao dao, GlobalVariables gv, ResultCustom resultCustom, WSClient ws, HttpExecutionContext httpExecutionContext) {
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
            JSONArray jsonArray1 = dao.getCategoriesLv1();
            JSONArray jsonArray2 = dao.getCategoriesLv2();
            JSONArray jsonArray3 = dao.getCategoriesLv3();

            for(int i = 0; i < jsonArray1.length(); i++){
                JSONArray jsonArrayLv2 = new JSONArray();
                for(int j = 0; j < jsonArray2.length(); j++){
                    if(jsonArray1.getJSONObject(i).getInt("id_lv1") == jsonArray2.getJSONObject(j).getInt("id_lv1")){
                        jsonArrayLv2.put(jsonArray2.getJSONObject(j));
                    }
                }

                for(int j = 0; j < jsonArrayLv2.length(); j++){
                    JSONArray jsonArrayLv3 = new JSONArray();
                    for(int jj = 0; jj < jsonArray3.length(); jj++){
                        if(jsonArrayLv2.getJSONObject(j).getInt("id_lv2") == jsonArray3.getJSONObject(jj).getInt("id_lv2")){
                            jsonArrayLv3.put(jsonArray3.getJSONObject(jj));
                        }
                    }
                    jsonArrayLv2.getJSONObject(j).put("categories_lv3", jsonArrayLv3);
                }
                jsonArray1.getJSONObject(i).put("categories_lv2", jsonArrayLv2);
            }

            result = resultAPI.jsonString(true, "successful", jsonArray1);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }
}
