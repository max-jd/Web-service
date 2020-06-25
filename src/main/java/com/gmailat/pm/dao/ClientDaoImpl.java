package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Client;
import com.gmailat.pm.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    Connection connection;

    @Override
    public List<Client> getAll() throws SQLException {
        String sql = "SELECT * FROM clients;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return new ClientMapper().mapToListClient(resultSet);
    }

    @Override
    public Client getById(int id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();

        return new ClientMapper().mapRow(resultSet, id);
    }

    @Override
    public void save(Client client) throws SQLException {
        String sql = "INSERT INTO clients(id, name, cash) VALUES(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setFloat(3, client.getCash());
        preparedStatement.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void update(Client client) throws SQLException {
        String sql = "UPDATE clients SET name=?, cash=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setFloat(2, client.getCash());
        preparedStatement.setInt(3, client.getId());
        preparedStatement.execute();
    }

}
