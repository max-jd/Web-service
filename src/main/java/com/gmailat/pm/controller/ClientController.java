package com.gmailat.pm.controller;

import com.gmailat.pm.Exception.NotFoundException;
import com.gmailat.pm.entity.Client;
import com.gmailat.pm.service.ClientService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/create_user")
    public String addUser(@RequestBody String strClient) throws IOException, SQLException {

        Client newClient = (Client) (new JSONDeserializer().deserialize(strClient, Client.class));
        clientService.save(newClient);

        return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(newClient);
    }

    @PostMapping(value = "/add_money/{id}/{name}/{addAmount}")
    public String addMoney(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("addAmount") float addAmount) throws SQLException {
        Client searchedClient = clientService.getById(id);
        if( !searchedClient.getName().equals(name) ) {
            throw new NotFoundException();
        }
        searchedClient.setCash(searchedClient.getCash() + addAmount);
        clientService.update(searchedClient);

        return new JSONSerializer().transform(
                new DateTransformer("MM/dd/yyyy HH:mm:ss"), java.util.Date.class)
                .exclude("*.class", "description")
                .serialize(searchedClient);
    }

}
