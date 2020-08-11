package com.gmailat.pm.controller;

import com.gmailat.pm.exception.NotEnoughMoneyException;
import com.gmailat.pm.entity.Client;
import com.gmailat.pm.entity.Product;
import com.gmailat.pm.service.ClientService;
import com.gmailat.pm.service.PayService;
import com.gmailat.pm.service.ProductService;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.*;

@RestController
public class PayController {

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    PayService payService;

    private int amountProductsFroDiscount = 3;

    @GetMapping("/pay/{whoId}/{productsIds}")
    public String payFor(@PathVariable("whoId") int whoId,
                         @PathVariable("productsIds") List<Integer> productsIds) throws SQLException {

        Client searchedClient = clientService.getById(whoId);
        List<Product> allProducts = productService.getAll();
        List<Product> productsFilteredById = payService.getProductsByIds(allProducts, productsIds);

        if (payService.moreThenXdiscounts(amountProductsFroDiscount, productsFilteredById)) {
            payService.sortByBestDiscounts(productsFilteredById);
            //start from the end of list
            ListIterator<Product> listIterator = productsFilteredById.listIterator(productsFilteredById.size());
            for (int i = 0; i < amountProductsFroDiscount; i++) {
                Product productGreatDeal = listIterator.previous();
                //set a discounted price
                productGreatDeal.setPrice(productGreatDeal.getPrice() - productGreatDeal.getDiscountBenefit());
            }
            while (listIterator.hasPrevious()) {
                Product productRegularPrice = listIterator.previous();
                productRegularPrice.getDiscount().setPercent(0);
            }
        }

        float sumProducts = payService.sum(productsFilteredById);
        if (searchedClient.getCash() < sumProducts) {
            throw new NotEnoughMoneyException();
        } else {
            searchedClient.setCash(searchedClient.getCash() - sumProducts);
            clientService.update(searchedClient);
        }

        return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(searchedClient);
    }

}
