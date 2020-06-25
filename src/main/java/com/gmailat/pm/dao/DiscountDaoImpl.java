package com.gmailat.pm.dao;

import com.gmailat.pm.entity.Discount;
import com.gmailat.pm.mapper.DiscountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DiscountDaoImpl implements DiscountDao {

    @Autowired
    Connection connection;

    @Override
    public List<Discount> getAll() throws SQLException {
        String sql = "SELECT * FROM discounts;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return new DiscountMapper().mapToListDiscount(resultSet);
    }

    @Override
    public Discount getById(int id) throws SQLException {
        String sql = "SELECT * FROM discounts WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        return new DiscountMapper().mapRow(resultSet, id);
    }

    @Override
    public void save(Discount discount) throws SQLException {
        String sql = " INSERT INTO discounts(percent) VALUES(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, discount.getPercent());
        preparedStatement.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM discounts WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void update(Discount discount) throws SQLException {
        String sql = "UPDATE discounts SET percent=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, discount.getPercent());
        preparedStatement.setInt(2, discount.getId());
        preparedStatement.execute();
    }

}
