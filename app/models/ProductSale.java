package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductSale extends Model {
    @Id
    private int product_id;
    private String name;
    private String image;
    private String href;
    private int price;
    private int belong_cate_level1_id;
    private int belong_cate_level2_id;
    private int belong_cate_level3_id;
    private int enable;

    public ProductSale(int product_id, String name, String image, String href, int price, int belong_cate_level1_id, int belong_cate_level2_id, int belong_cate_level3_id, int enable) {
        this.product_id = product_id;
        this.name = name;
        this.image = image;
        this.href = href;
        this.price = price;
        this.belong_cate_level1_id = belong_cate_level1_id;
        this.belong_cate_level2_id = belong_cate_level2_id;
        this.belong_cate_level3_id = belong_cate_level3_id;
        this.enable = enable;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBelong_cate_level1_id() {
        return belong_cate_level1_id;
    }

    public void setBelong_cate_level1_id(int belong_cate_level1_id) {
        this.belong_cate_level1_id = belong_cate_level1_id;
    }

    public int getBelong_cate_level2_id() {
        return belong_cate_level2_id;
    }

    public void setBelong_cate_level2_id(int belong_cate_level2_id) {
        this.belong_cate_level2_id = belong_cate_level2_id;
    }

    public int getBelong_cate_level3_id() {
        return belong_cate_level3_id;
    }

    public void setBelong_cate_level3_id(int belong_cate_level3_id) {
        this.belong_cate_level3_id = belong_cate_level3_id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
