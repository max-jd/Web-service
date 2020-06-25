package com.gmailat.pm.service;

import com.gmailat.pm.dao.ProductDao;
import com.gmailat.pm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> getAll() throws SQLException {
        return productDao.getAll();
    }

    @Override
    public Product getById(int id) throws SQLException {
        return productDao.getById(id);
    }

    @Override
    public void save(Product product) throws SQLException {
        productDao.save(product);
    }

    @Override
    public void delete(int id) throws SQLException {
        productDao.delete(id);
    }

    @Override
    public void update(Product product) throws SQLException {
        productDao.update(product);
    }

}
