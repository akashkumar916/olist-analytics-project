package com.dbms.service.impl;

import com.dbms.common.CommonConfig;
import com.dbms.common.Frequency;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.controller.ProductPerformanceController;
import com.dbms.dto.AllCategoriesDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */

@Service
public class AllCategoriesServiceImpl {

    private static final Logger logger = Logger.getLogger(AllCategoriesServiceImpl.class.getName());

    public List<AllCategoriesDTO> getAllCategories() {
        List<AllCategoriesDTO> allCategories = new ArrayList<>();

        String query = "select distinct category from product";
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement productQuery
                    = connection.prepareStatement(query);

            resultSet = productQuery.executeQuery();
            while(resultSet.next()) {
                String category = resultSet.getString(1);
                allCategories.add(new AllCategoriesDTO(category));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

}
