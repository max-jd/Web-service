package com.gmailat.pm.service;

import com.gmailat.pm.entity.Product;

import java.util.List;

public interface PayService {
    public List<Product> getProductsByIds(List<Product> allProducts, List<Integer> productsIds);

    public boolean moreThenXdiscounts(int amountDiscount, List<Product> productsList);

    public void sortByBestDiscounts(List<Product> productList);

    public float sum(List<Product> productList);
}
