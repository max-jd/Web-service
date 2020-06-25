package com.gmailat.pm.service;

import com.gmailat.pm.dao.ClientDao;
import com.gmailat.pm.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public List<Client> getAll() throws SQLException {
        return clientDao.getAll();
    }

    @Override
    public Client getById(int id) throws SQLException {
        return clientDao.getById(id);
    }

    @Override
    public void save(Client client) throws SQLException {
        clientDao.save(client);
    }

    @Override
    public void delete(int id) throws SQLException {
        clientDao.delete(id);
    }

    @Override
    public void update(Client client) throws SQLException {
        clientDao.update(client);
    }

}
