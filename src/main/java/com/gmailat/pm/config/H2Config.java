package com.gmailat.pm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Deprecated
public class H2Config {
    private final String url = "jdbc:h2:mem:h2";
    static private Connection connection = null;

    public void setUp() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        connection = DriverManager.getConnection(url);
    }

    private void createTableClients() throws SQLException {
        final String sql =
                " CREATE TABLE Clients(\n" +
                        " id int  AUTO_INCREMENT,\n" +
                        " name varchar(25) NOT NULL,\n" +
                        " cash float DEFAULT 0,\n" +
                        " PRIMARY KEY(id)\n" +
                        " );";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void createTableProducts() throws SQLException {
        final String sql =
                "CREATE TABLE products(\n" +
                        " id int AUTO_INCREMENT,\n" +
                        " name varchar(255),\n" +
                        " category varchar(30),\n" +
                        " price float,\n" +
                        " fk_discount int,\n" +
                        " PRIMARY KEY(id),\n" +
                        " FOREIGN KEY(fk_discount) REFERENCES discounts(id)\n" +
                        " );";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void createTableDiscount() throws SQLException {
        final String sql =
                " CREATE TABLE discounts(\n" +
                        " id int AUTO_INCREMENT,\n" +
                        " percent  int,\n" +
                        " PRIMARY KEY(id)\n" +
                        " );\n" +
                        " ";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void addProducts() throws SQLException {
        String sql = "INSERT INTO products(name, category, price, fk_discount) \n" +
                " VALUES\n" +
                " ('Blue tie', 'tie', 150, 4),\n" +
                " ('Yellow tie', 'tie', 150, 1),\n" +
                " ('White tie', 'tie', 77, 1),\n" +
                " ('Black tie', 'tie', 12, 3),\n" +
                " ('Purple tie', 'tie', 222, 1),\n" +
                " ('White tie 2', 'tie', 333, 1),\n" +
                " ('Blue bow-tie', 'bow-tie', 1111, 1),\n" +
                " ('Yellow bow-tie', 'bow-tie', 233, 5),\n" +
                " ('White bow-tie', 'bow-tie', 111, 1),\n" +
                " ('Black bow-tie', 'bow-tie', 432, 2),\n" +
                " ('Purpule bow-tie', 'bow-tie', 221, 1),\n" +
                " ('White bow-tie 2', 'bow-tie', 222, 2),\n" +
                " ('Blue Suspender', 'Suspender', 123, 1),\n" +
                " ('Yellow Suspender', 'Suspender', 545, 1),\n" +
                " ('White Suspender', 'Suspender', 343, 3),\n" +
                " ('Black Suspender', 'Suspender', 4333, 2),\n" +
                " ('Purpule Suspender', 'Suspender', 2234, 3),\n" +
                " ('White Suspender 2', 'Suspender', 234, 5);";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void addDiscounts() throws SQLException {
        String sql = "INSERT INTO clients(name, cash)\n" +
                " VALUES\n" +
                " ('Sophia', 1500),\n" +
                " ('John', 2500),\n" +
                " ('William', 12200),\n" +
                " ('Emma', 2200),\n" +
                " ('Olivia', 100),\n" +
                " ('Jayden', 1500.50);";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void addClients() throws SQLException {
        String sql = " INSERT INTO discounts(percent)\n" +
                " VALUES\n" +
                " (5),\n" +
                " (15),\n" +
                " (25),\n" +
                " (35),\n" +
                " (50),\n" +
                " (75);";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    private void prepareDB() throws SQLException {
        setUp();

        createTableDiscount();
        createTableProducts();
        createTableClients();

        addDiscounts();
        addClients();
        addProducts();
    }


    public Connection getConnection() throws SQLException {
        if (connection == null) {
            prepareDB();
        }
        return connection;
    }

}
