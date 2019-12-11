package thread;

import dao.ProductDao;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class GetInfoProductSendo extends Thread {
    private int product_id;
    private String path;
    private ProductDao dao;
    private WSClient ws;


    @Inject
    public GetInfoProductSendo(ProductDao productDao, WSClient ws){
        this.dao = productDao;
        this.ws = ws;
    }

    public void run() {
        String url = "https://www.sendo.vn/m/wap_v2/full/san-pham/" + path;
        CompletionStage<String> article = ws.url(url).setContentType("application/json").get().thenApply((WSResponse r) -> {
            JSONObject body = new JSONObject(r.getBody());
            int code = body.getJSONObject("status").getInt("code");
            if(code == 404){
                String urlSearch = "https://www.sendo.vn/m/wap_v2/search/product?p=1&platform=web&q=" + path + "&s=60&search_algo=algo6&sortType=rank";
                CompletionStage<String> articleSearch = ws.url(urlSearch).setContentType("application/json").get().thenApply((WSResponse rs) -> {
                    JSONObject bodySearch = new JSONObject(rs.getBody());
                    int codeSearch = bodySearch.getJSONObject("status").getInt("code");
                    if(codeSearch == 200){
                        JSONArray jsonArrayData = bodySearch.getJSONObject("result").getJSONArray("data");
                        String name = "";
                        int final_price = 0;
                        int product_id_update = -1;
                        String cat_path = "";
                        String image = "";
                        String image_mob = "";
                        String category_id = "";
                        int shop_id = 0;
                        String shop_name = "";
                        if(jsonArrayData.length() > 0){
                            int indexGetInfo = 0;
                            if(jsonArrayData.length() > 21){
                                indexGetInfo = 21;
                            }
                            JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
                            category_id = item.getString("category_id");
                            name = item.getString("name");
                            product_id_update = item.getInt("id");
                            final_price = item.getInt("final_price");
                            cat_path = item.getString("cat_path");
                            image = item.getString("img_url");
                            image_mob = item.getString("img_url_mob");
//                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
                            shop_name = item.getJSONObject("shop_info").getString("shop_name");
                            try {
                                Logger.info("99---------------" + product_id_update);
                                dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 1, 0);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return "";
                });
            }else {
                JSONArray jsonArrayData = body.getJSONObject("result").getJSONArray("data");
                String name = "";
                int final_price = 0;
                int product_id_update = -1;
                String cat_path = "";
                String image = "";
                String image_mob = "";
                String category_id = "";
                int shop_id = 0;
                String shop_name = "";
                if(jsonArrayData.length() > 0){
                    int indexGetInfo = 0;
                    if(jsonArrayData.length() > 21){
                        indexGetInfo = 21;
                    }
                    JSONObject item = jsonArrayData.getJSONObject(indexGetInfo);
                    category_id = item.getString("category_id");
                    name = item.getString("name");
                    product_id_update = item.getInt("id");
                    final_price = item.getInt("final_price");
                    cat_path = item.getString("cat_path");
                    image = item.getString("image");
                    image_mob = item.getJSONArray("media").getJSONObject(0).getString("image");
//                            shop_id = item.getJSONObject("shop_info").getInt("shop_id");
                    shop_name = item.getJSONObject("shop_info").getString("shop_name");
                    try {
                        Logger.info("!!!!!!!!!!!!!!!!!!---------------" + product_id_update);
                        dao.updateProduct(product_id, product_id_update, name, final_price, image, image_mob, cat_path, shop_id, shop_name, category_id, 0, 1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "";
        });

        this.interrupt();
    }

    public void setInfo(int product_id, String path) {
        this.product_id = product_id;
        this.path = path;

        this.start();
    }
}
