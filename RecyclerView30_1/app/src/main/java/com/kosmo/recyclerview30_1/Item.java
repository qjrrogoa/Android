package com.kosmo.recyclerview30_1;

//하나의 아이템을 저장하는 자료구조
public class Item {
    private String itemName;
    private String itemDept;
    private String itemDate;
    private int itemImageResId;

    public Item(String itemName, String itemDept, String itemDate, int itemImageResId) {
        this.itemName = itemName;
        this.itemDept = itemDept;
        this.itemDate = itemDate;
        this.itemImageResId = itemImageResId;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemDept() {
        return itemDept;
    }
    public String getItemDate() {
        return itemDate;
    }
    public int getItemImageResId() {
        return itemImageResId;
    }
}
