package com.example.mk0730.alkolmetre;

public class AlcoholFilter {
    String search;
    String category;
    Boolean is_continued;
    Boolean is_vqa;
    Boolean order_price;
    Boolean order_alcohol_content;
    Boolean order_price_per_liter;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIs_continued() {
        return is_continued;
    }

    public void setIs_continued(Boolean is_continued) {
        this.is_continued = is_continued;
    }

    public Boolean getIs_vqa() {
        return is_vqa;
    }

    public void setIs_vqa(Boolean is_vqa) {
        this.is_vqa = is_vqa;
    }

    public Boolean getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Boolean order_price) {
        this.order_price = order_price;
    }

    public Boolean getOrder_alcohol_content() {
        return order_alcohol_content;
    }

    public void setOrder_alcohol_content(Boolean order_alcohol_content) {
        this.order_alcohol_content = order_alcohol_content;
    }

    public Boolean getOrder_price_per_liter() {
        return order_price_per_liter;
    }

    public void setOrder_price_per_liter(Boolean order_price_per_liter) {
        this.order_price_per_liter = order_price_per_liter;
    }
}
