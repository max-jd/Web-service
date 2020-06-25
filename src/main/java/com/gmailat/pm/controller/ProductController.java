package com.gmailat.pm.controller;

import com.gmailat.pm.entity.Product;
import com.gmailat.pm.service.ProductService;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public String getList() throws SQLException {
        List<Product> allProducts = productService.getAll();

       return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(allProducts);
    }

    @GetMapping("/list/{category}")
    public String getListCategory(@PathVariable String category) throws SQLException {
        List<Product> allProducts = productService.getAll();
        List<Product> categorizedProducts = allProducts.parallelStream()
                .filter((product) -> product.getCategory().equals(category)).collect(Collectors.toList());

        return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(categorizedProducts);
    }

}
