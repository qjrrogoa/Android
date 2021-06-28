package com.kosmo.jsonparse34_3;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "link",
        "image",
        "lprice",
        "hprice",
        "mallName",
        "productId",
        "productType",
        "brand",
        "maker",
        "category1",
        "category2",
        "category3",
        "category4"
})

public class NaverShoppingItem {

    public String lastBuildDate;
    public int total;
    public int start;
    public int display;
    public List<Item> items = null;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    static class Item {
        @JsonProperty("title")
        public String title;
        @JsonProperty("link")
        public String link;
        @JsonProperty("image")
        public String image;
        @JsonProperty("lprice")
        public String lprice;
        @JsonProperty("hprice")
        public String hprice;
        @JsonProperty("mallName")
        public String mallName;
        @JsonProperty("productId")
        public String productId;
        @JsonProperty("productType")
        public String productType;
        @JsonProperty("brand")
        public String brand;
        @JsonProperty("maker")
        public String maker;
        @JsonProperty("category1")
        public String category1;
        @JsonProperty("category2")
        public String category2;
        @JsonProperty("category3")
        public String category3;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLprice() {
            return lprice;
        }

        public void setLprice(String lprice) {
            this.lprice = lprice;
        }

        public String getHprice() {
            return hprice;
        }

        public void setHprice(String hprice) {
            this.hprice = hprice;
        }

        public String getMallName() {
            return mallName;
        }

        public void setMallName(String mallName) {
            this.mallName = mallName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getMaker() {
            return maker;
        }

        public void setMaker(String maker) {
            this.maker = maker;
        }

        public String getCategory1() {
            return category1;
        }

        public void setCategory1(String category1) {
            this.category1 = category1;
        }

        public String getCategory2() {
            return category2;
        }

        public void setCategory2(String category2) {
            this.category2 = category2;
        }

        public String getCategory3() {
            return category3;
        }

        public void setCategory3(String category3) {
            this.category3 = category3;
        }

        public String getCategory4() {
            return category4;
        }

        public void setCategory4(String category4) {
            this.category4 = category4;
        }

        @JsonProperty("category4")
        public String category4;
    }////Item


}////NaverShoppingItem