package com.company.services;

import com.company.model.Product;

import java.util.List;

public interface IProductServices {
    List<Product> getProducts();

    void add(Product newProduct);

    Product getById(long id);

    boolean existById(long id);

    void update(Product newProduct);

    void removeById(long id);
}
