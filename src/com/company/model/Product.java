package com.company.model;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private long id;
    private String type;
    private String name;
    private int amount;
    private int price;

    public Product(){
    }

    public Product(String type,String name, int amount, int price) {
        this.id = System.currentTimeMillis() / 1000;
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Product(String str){
        String[] strArr = str.split(",");
        this.id = Long.parseLong(strArr[0]);
        this.type = strArr[1];
        this.name = strArr[2];
        this.amount = Integer.parseInt(strArr[3]);
        this.price = Integer.parseInt(strArr[4]);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "," + type + "," + name + "," + amount + "," + price;
    }

    @Override
    public int compareTo(Product o) {
        if(price - o.price == 0){
            if(amount - o.amount == 0){
                return name.compareTo(o.name);
            }else{
                return amount - o.amount;
            }
        }
        return price - o.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && amount == product.amount && price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, price);
    }
}
