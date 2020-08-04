package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Discount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.List;


@Repository
public class DiscountDaoImpl implements DiscountDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Discount> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = criteriaBuilder.createQuery(Discount.class);
        Root<Discount> root = criteriaQuery.from(Discount.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<Discount> listDiscount = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return listDiscount;
    }


    @Override
    public Discount getById(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Discount searchedDiscount = session.get(Discount.class, id);

        session.getTransaction().commit();
        session.close();

        return searchedDiscount;
    }


    @Override
    public void save(Discount discount) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(discount);

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Discount> criteriaDelete = criteriaBuilder.createCriteriaDelete(Discount.class);
        Root<Discount> root = criteriaDelete.from(Discount.class);
        ParameterExpression<Integer> idParameter = criteriaBuilder.parameter(Integer.class, "id");
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idParameter));

        Query query = session.createQuery(criteriaDelete);
        query.setParameter(idParameter, id);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void update(Discount discount) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(discount);

        session.getTransaction().commit();
        session.close();
    }

}
