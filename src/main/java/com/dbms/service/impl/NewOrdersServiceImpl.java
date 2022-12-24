package com.dbms.service.impl;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */

import com.dbms.common.CommonConfig;
import com.dbms.common.Frequency;
import com.dbms.common.NewOrdersParameters;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.controller.ProductPerformanceController;
import com.dbms.dto.NewOrdersDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class NewOrdersServiceImpl {

    private static final Logger logger = Logger.getLogger(ProductPerformanceController.class.getName());

    public List<NewOrdersDTO> getNumNewOrders(NewOrdersParameters newOrdersParameters) {
        List<NewOrdersDTO> newOrdersDTOS = new ArrayList<>();
// Add states here, v. imp
        String weeklyPerformanceQuery = "With total_orders as (Select order_id, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?) select count(*) as new_orders, weekstart from total_orders group by weekstart order by weekstart";
        String monthlyPerformanceQuery = "With total_orders as (Select order_id, cast(to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MM-YY') as date) as monthstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?) select count(*) as new_orders, monthstart from total_orders group by monthstart order by monthstart";
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement productQuery
                    = connection.prepareStatement(newOrdersParameters.getFrequency() == Frequency.WEEKLY ? weeklyPerformanceQuery : monthlyPerformanceQuery);

            productQuery.setString(1, newOrdersParameters.getStartDate());
            productQuery.setString(2, newOrdersParameters.getEndDate());

            resultSet = productQuery.executeQuery();
            while(resultSet.next()) {
                int numOrders = resultSet.getInt(1);
                String weekStart = resultSet.getString(2);
                newOrdersDTOS.add(new NewOrdersDTO(numOrders, weekStart));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOrdersDTOS;
    }

}
