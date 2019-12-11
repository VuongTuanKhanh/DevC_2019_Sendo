package controllers;

import Utils.Compress;
import dao.ProductDao;
import main.GlobalVariables;
import mapping.ResultAPI;
import mapping.ResultCustom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import play.Logger;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import thread.GetInfoProductSendo;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class ProductController extends BaseController {
    private ProductDao dao;
    private GlobalVariables gv;
    private ResultCustom resultCustom;
    private WSClient ws;
    private GetInfoProductSendo getInfoProductSendo;
    private int count = 0;
    private HttpExecutionContext httpExecutionContext;


    @Inject
    public ProductController(ProductDao dao, GlobalVariables gv, ResultCustom resultCustom, WSClient ws, HttpExecutionContext httpExecutionContext) {
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

    public Result getCategories(int categories_lv1, int categories_lv2, int categories_lv3, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getCategories(categories_lv1, categories_lv2, categories_lv3, offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getBestCategories(int categories_lv1, int categories_lv2, int categories_lv3, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getBestCategories(categories_lv1, categories_lv2, categories_lv3, offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getRecommend(int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getRecommend(offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getRelated(int product_id, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getById(product_id);
            JSONArray jsonArrayRelate = new JSONArray();
            if(jsonArray.length() > 0){
                int belong_cate_level3_id = jsonArray.getJSONObject(0).getInt("belong_cate_level3_id");
                jsonArrayRelate = dao.getBestCategories(-1, -1, belong_cate_level3_id, offset, number);
            }
            result = resultAPI.jsonString(true, "successful", jsonArrayRelate);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

    public Result getByMonth(int month, int page, int number) throws IOException {
        int offset = (page - 1) * number;
        ResultAPI resultAPI = new ResultAPI();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = dao.getByMonth(month, offset, number);
            result = resultAPI.jsonString(true, "successful", jsonArray);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            result = resultAPI.jsonString(false, "unsuccessful", jsonObject);
        }
        setHeaderResponse(result.length());
        return (ok(Compress.compress(result)).as("application/json"));
    }

//    private String getInfoSendo(int product_id, String path) throws JSONException {
//        String url = "https://www.sendo.vn/m/wap_v2/full/san-pham/" + path;
//        CompletionStage<String> article = ws.url(url).setContentType("application/json").get().thenApply((WSResponse r) -> {
//            JSONObject body = new JSONObject(r.getBody());
//            int code = body.getJSONObject("status").getInt("code");
//            if(code == 404){
//                String urlSearch = "https://www.sendo.vn/m/wap_v2/search/product?p=1&platform=web&q=" + path + "&s=60&search_algo=algo6&sortType=rank";
//                CompletionStage<String> articleSearch = ws.url(urlSearch).setContentType("application/json").get().thenApply((WSResponse rs) -> {
//                    JSONObject bodySearch = new JSONObject(rs.getBody());
//                    int codeSearch = bodySearch.getJSONObject("status").getInt("code");
//                    if(codeSearch == 200){
//                        JSONArray jsonArrayData = bodySearch.getJSONObject("result").getJSONArray("data");
//                        String name = "";
//                        int final_price = 0;
//                        int product_id_update = -1;
//                        String cat_path = "";
//                        String image = "";
//                        String image_mob = "";
//                        String category_id = "";
//                        int shop_id = 0;
//                        String shop_name = "";
//                        if(jsonArrayData.length() > 0){
//                            int indexGetInfo = 0;
//                            if(jsonArrayData.length() > 21){
//                                indexGetInfo = 21;
//                            }
//                            JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
//                            category_id = item.getString("category_id");
//                            name = item.getString("name");
//                            product_id_update = item.getInt("id");
//                            final_price = item.getInt("final_price");
//                            cat_path = item.getString("cat_path");
//                            image = item.getString("img_url");
//                            image_mob = item.getString("img_url_mob");
////                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
//                            shop_name = item.getJSONObject("shop_info").getString("shop_name");
//                            try {
//                                Logger.info("99---------------" + product_id_update);
//                                dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 1, 0);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    return "";
//                });
//            }else {
//                JSONArray jsonArrayData = body.getJSONObject("result").getJSONArray("data");
//                String name = "";
//                int final_price = 0;
//                int product_id_update = -1;
//                String cat_path = "";
//                String image = "";
//                String image_mob = "";
//                String category_id = "";
//                int shop_id = 0;
//                String shop_name = "";
//                if(jsonArrayData.length() > 0){
//                    int indexGetInfo = 0;
//                    if(jsonArrayData.length() > 21){
//                        indexGetInfo = 21;
//                    }
//                    JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
//                    category_id = item.getString("category_id");
//                    name = item.getString("name");
//                    product_id_update = item.getInt("id");
//                    final_price = item.getInt("final_price");
//                    cat_path = item.getString("cat_path");
//                    image = item.getString("img_url");
//                    image_mob = item.getString("img_url_mob");
////                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
//                    shop_name = item.getJSONObject("shop_info").getString("shop_name");
//                    try {
//                        Logger.info("99---------------" + product_id_update);
//                        dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 0, 1);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return "";
//        });
//        return "";
//    }
//
//
//    public CompletionStage<Result> index() throws InterruptedException {
//        // Use a different task with explicit EC
//        return calculateResponse(count)
//                .thenApplyAsync(
//                        answer -> {
//                            count = answer;
//                            return ok("answer was " + answer);
//                        },
//                        httpExecutionContext.current());
//    }
//
//    public CompletionStage<Result> executionSendo(int product_id, String path) throws InterruptedException {
//        return getSenDoInfo2(dao, ws, product_id, path)
//                .thenApplyAsync(
//                        answer -> {
//                            count = answer;
//                            return ok("product_id: " + answer);
//                        },
//                        httpExecutionContext.current());
//    }
//
//    private static CompletionStage<Integer> calculateResponse(int count) throws InterruptedException {
//        Logger.info(String.valueOf(count));
////        double randomDouble = Math.random();
////        randomDouble = randomDouble * 999 + 1;
////        int randomInt = (int) randomDouble;
////        TimeUnit.MILLISECONDS.sleep(randomInt);
//        count = count + 1;
//        return CompletableFuture.completedFuture(count);
//    }
//
//
//    private static CompletionStage<Integer> getSenDoInfo2(ProductDao dao, WSClient ws, int product_id, String path) throws InterruptedException {
//        String url = "https://www.sendo.vn/m/wap_v2/full/san-pham/" + path;
//        CompletionStage<String> article = ws.url(url).setContentType("application/json").get().thenApply((WSResponse r) -> {
//            JSONObject body = new JSONObject(r.getBody());
//            int code = body.getJSONObject("status").getInt("code");
//            if(code == 404){
//                String urlSearch = "https://www.sendo.vn/m/wap_v2/search/product?p=1&platform=web&q=" + path + "&s=60&search_algo=algo6&sortType=rank";
//                CompletionStage<String> articleSearch = ws.url(urlSearch).setContentType("application/json").get().thenApply((WSResponse rs) -> {
//                    JSONObject bodySearch = new JSONObject(rs.getBody());
//                    int codeSearch = bodySearch.getJSONObject("status").getInt("code");
//                    if(codeSearch == 200){
//                        JSONArray jsonArrayData = bodySearch.getJSONObject("result").getJSONArray("data");
//                        String name = "";
//                        int final_price = 0;
//                        int product_id_update = -1;
//                        String cat_path = "";
//                        String image = "";
//                        String image_mob = "";
//                        String category_id = "";
//                        int shop_id = 0;
//                        String shop_name = "";
//                        if(jsonArrayData.length() > 0){
//                            int indexGetInfo = 0;
//                            if(jsonArrayData.length() > 21){
//                                indexGetInfo = 21;
//                            }
//                            JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
//                            category_id = item.getString("category_id");
//                            name = item.getString("name");
//                            product_id_update = item.getInt("id");
//                            final_price = item.getInt("final_price");
//                            cat_path = item.getString("cat_path");
//                            image = item.getString("img_url");
//                            image_mob = item.getString("img_url_mob");
////                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
//                            shop_name = item.getJSONObject("shop_info").getString("shop_name");
//                            try {
//                                Logger.info("99---------------" + product_id_update);
//                                dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 1, 0);
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    return "";
//                });
//            }else {
//                JSONArray jsonArrayData = body.getJSONObject("result").getJSONArray("data");
//                String name = "";
//                int final_price = 0;
//                int product_id_update = -1;
//                String cat_path = "";
//                String image = "";
//                String image_mob = "";
//                String category_id = "";
//                int shop_id = 0;
//                String shop_name = "";
//                if(jsonArrayData.length() > 0){
//                    int indexGetInfo = 0;
//                    if(jsonArrayData.length() > 21){
//                        indexGetInfo = 21;
//                    }
//                    JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
//                    category_id = item.getString("category_id");
//                    name = item.getString("name");
//                    product_id_update = item.getInt("id");
//                    final_price = item.getInt("final_price");
//                    cat_path = item.getString("cat_path");
//                    image = item.getString("image");
//                    image_mob = item.getJSONArray("media").getJSONObject(0).getString("image");
////                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
//                    shop_name = item.getJSONObject("shop_info").getString("shop_name");
//                    try {
//                        Logger.info("!!!!!!!!!!!!!!!!!!---------------" + product_id_update);
//                        dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 0, 1);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return "";
//        });
//        return CompletableFuture.completedFuture(product_id);
//    }
}
