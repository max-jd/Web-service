package com.gmailat.pm.mapper;

import com.gmailat.pm.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getFloat(4),
                resultSet.getInt(5)
        );
        return product;
    }

    public List<Product> mapToListProduct(ResultSet resultSet) throws SQLException {
        List<Product> listOfProducts = new ArrayList<Product>();

        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);
            float price = resultSet.getFloat(4);
            int discount = resultSet.getInt(5);

            Product product = new Product(id, name, category, price, discount);
            listOfProducts.add(product);
        }
        return listOfProducts;
    }

}
