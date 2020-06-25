package com.gmailat.pm.service;

import com.gmailat.pm.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {

    public List<Client> getAll() throws SQLException;

    public Client getById(int id) throws SQLException;

    public void save(Client client) throws SQLException;

    public void delete(int id) throws SQLException;

    public void update(Client client) throws SQLException;

}
