package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Product> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<Product> listProduct = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return listProduct;
    }


    @Override
    public Product getById(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product searchedProduct = session.get(Product.class, id);

        session.getTransaction().commit();
        session.close();

        return searchedProduct;
    }


    @Override
    public void save(Product product) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(product);

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);
        Root<Product> root = criteriaDelete.from(Product.class);
        ParameterExpression<Integer> idParameter = criteriaBuilder.parameter(Integer.class, "id");

        Query query = session.createQuery(criteriaDelete);
        query.setParameter(idParameter, id);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void update(Product product) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(product);

        session.getTransaction().commit();
        session.close();
    }

}
