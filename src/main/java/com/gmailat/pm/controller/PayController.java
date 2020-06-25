package com.gmailat.pm.controller;

import com.gmailat.pm.Exception.NotEnoughMoneyExceptionx;
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

    @GetMapping("/pay/{whoId}/{productsIds}")
    public String payFor(@PathVariable("whoId") int whoId,
                         @PathVariable("productsIds") List<Integer> productsIds) throws SQLException {

        Client searchedClient = clientService.getById(whoId);
        List<Product> allProducts = productService.getAll();
        List<Product> filteredById = payService.getProductsByIds(allProducts, productsIds);

        if(payService.moreThenXdiscounts(3, filteredById)) {
            payService.sortByBestDiscounts(filteredById);
            ListIterator<Product> listIterator = filteredById.listIterator(filteredById.size());
            for(int i = 0; i < 3; i++) {
                Product productGreatDeal = listIterator.previous();
                productGreatDeal.setPrice(productGreatDeal.getPrice() - productGreatDeal.getDiscountBenefit());
            }
            while(listIterator.hasPrevious()) {
                Product productRegularPrice = listIterator.previous();
                productRegularPrice.setDiscount(0);
            }
        }

        float sumProducts = payService.sum(filteredById);
        if(searchedClient.getCash() < sumProducts) {
            throw new NotEnoughMoneyExceptionx();
            }else {
                searchedClient.setCash(searchedClient.getCash()-sumProducts);
                clientService.update(searchedClient);
            }

        return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(searchedClient);
    }

}
