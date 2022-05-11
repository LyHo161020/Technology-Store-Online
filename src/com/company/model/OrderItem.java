package com.company.model;

public class OrderItem {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private long orderId;
    private int productId;
    private String productName;
    private double total;

    private String date;
    private String status;

    public OrderItem(long id,String name, double price, int quantity, long orderId, int productId, String productName, double total,String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.total = total;
        this.date = date;
        this.status = "unpaid";
    }

    public OrderItem(){
    }
    public OrderItem(String record) {
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0]);
        name = fields[1];
        price = Double.parseDouble(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        orderId = Long.parseLong(fields[4]);
        productId = Integer.parseInt(fields[5]);
        productName = fields[6];
        total = Double.parseDouble(fields[7]);
        date = fields[8];
        status = fields[9];
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + orderId + ","
                + productId + "," + productName + "," + total + "," + date + "," + status;
    }
}
