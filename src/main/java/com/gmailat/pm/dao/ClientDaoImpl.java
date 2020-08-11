package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Client;
import com.gmailat.pm.entity.Client_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Client> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<Client> listClient = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return listClient;
    }


    @Override
    public Client getById(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Client searchedClient = session.get(Client.class, id);

        session.getTransaction().commit();
        session.close();

        return searchedClient;
    }


    @Override
    public void save(Client newClient) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(newClient);

        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Client> criteriaDelete = criteriaBuilder.createCriteriaDelete(Client.class);
        Root<Client> root = criteriaDelete.from(Client.class);
        ParameterExpression<Integer> idParameter = criteriaBuilder.parameter(Integer.class, "id");
        criteriaDelete.where(
                criteriaBuilder.equal(root.get(Client_.id), idParameter)
        );
        Query query = session.createQuery(criteriaDelete);
        query.setParameter(idParameter, id);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void update(Client client) throws SQLException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(client);

        session.getTransaction().commit();
        session.close();
    }

}
