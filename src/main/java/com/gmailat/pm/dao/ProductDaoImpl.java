package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Product;
import com.gmailat.pm.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    Connection connection;

    @Override
    public List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM products;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return new ProductMapper().mapToListProduct(resultSet);
    }

    @Override
    public Product getById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        return new ProductMapper().mapRow(resultSet, id);
    }

    @Override
    public void save(Product product) throws SQLException {
        String sql = " INSERT INTO products(name, category, price, fk_discount) VALUES(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCategory());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.setInt(4, product.getDiscount());
        preparedStatement.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name=?, category=?, price=?, fk_discount=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCategory());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.setInt(4, product.getDiscount());
        preparedStatement.setInt(5, product.getId());
        preparedStatement.execute();
    }

}
