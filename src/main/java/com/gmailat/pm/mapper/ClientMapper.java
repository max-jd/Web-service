package com.gmailat.pm.mapper;

import com.gmailat.pm.entity.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientMapper implements RowMapper<Client> {

    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Client client = new Client(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getFloat(3)
        );
        return client;
    }

    public List<Client> mapToListClient(ResultSet resultSet) throws SQLException {
        List<Client> listOfClients = new ArrayList<Client>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            float cash = resultSet.getFloat(3);

            Client client = new Client(id, name, cash);
            listOfClients.add(client);
        }
        return listOfClients;
    }

}

