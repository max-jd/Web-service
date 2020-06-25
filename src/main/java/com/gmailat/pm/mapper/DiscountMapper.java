package com.gmailat.pm.mapper;

import com.gmailat.pm.entity.Discount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountMapper implements RowMapper<Discount> {

    public Discount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Discount discount = new Discount(
                resultSet.getInt(1),
                resultSet.getInt(2)
        );
        return discount;
    }

    public List<Discount> mapToListDiscount(ResultSet resultSet) throws SQLException {
        List<Discount> listOfDiscounts = new ArrayList<Discount>();

        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            int percent = resultSet.getInt(2);

            Discount discount = new Discount(id, percent);
            listOfDiscounts.add(discount);
        }
        return listOfDiscounts;
    }
}
