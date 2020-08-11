package com.gmailat.pm.service;

import com.gmailat.pm.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService{


    public List<Product> getProductsByIds(List<Product> allProducts, List<Integer> productsIds) {
        //get all products and then every compare with ids of products
        return  allProducts.parallelStream().filter(
                (product) -> {
                    return productsIds.stream().anyMatch((intIdProduc) -> intIdProduc == product.getId());
                }
        ).collect(Collectors.toList());
    }

    public boolean moreThenXdiscounts(int amountDiscount, List<Product> productsList) {
        return productsList.parallelStream().filter(product -> product.getDiscount().getPercent() > 0).count() > amountDiscount;
    }

    public void sortByBestDiscounts(List<Product> productList) {
        Collections.sort(productList, Comparator.comparing(Product::getDiscountBenefit));
    }

    public float sum(List<Product> productList) {
        return productList.stream()
                .map((product -> product.getPrice()))
                .reduce(0F, (sum, nextPrice) -> sum + nextPrice);
    }
}
