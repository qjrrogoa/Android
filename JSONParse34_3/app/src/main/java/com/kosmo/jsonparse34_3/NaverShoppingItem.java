package com.kosmo.jsonparse34_3;

public class NaverShoppingItem {
    private String title;
    private String image;
    private String link;
    private String maker;
    private String brand;
    private String hprice;
    private String lprice;

    public NaverShoppingItem(String title, String image, String link, String maker, String brand, String hprice, String lprice) {
        this.title = title;
        this.image = image;
        this.link = link;
        this.maker = maker;
        this.brand = brand;
        this.hprice = hprice;
        this.lprice = lprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHprice() {
        return hprice;
    }

    public void setHprice(String hprice) {
        this.hprice = hprice;
    }

    public String getLprice() {
        return lprice;
    }

    public void setLprice(String lprice) {
        this.lprice = lprice;
    }
}
