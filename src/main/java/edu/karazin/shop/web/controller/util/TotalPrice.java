package edu.karazin.shop.web.controller.util;

public class TotalPrice {

    private Double price;

    public TotalPrice(){}

    public TotalPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
