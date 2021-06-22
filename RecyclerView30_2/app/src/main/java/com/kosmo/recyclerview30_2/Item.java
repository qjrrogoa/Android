package com.kosmo.recyclerview30_2;

public class Item {
    private String itemTitle;
    private int itemImageResId;

    public Item(String itemTitle, int itemImageResId) {
        this.itemTitle = itemTitle;
        this.itemImageResId = itemImageResId;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public int getItemImageResId() {
        return itemImageResId;
    }
}
