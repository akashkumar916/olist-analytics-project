package com.dbms.service.impl;

import com.dbms.common.*;
import com.dbms.dto.AverageCartValueDTO;
import com.dbms.dto.CustomerSatisfactionDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author ayushkumar
 * @created on 04/12/22
 */

@Service
public class CustomerSatisfactionServiceImpl {

    private static final Logger logger = Logger.getLogger(CustomerSatisfactionServiceImpl.class.getName());

    public List<CustomerSatisfactionDTO> getCustomerSatisfaction(CustomerSatisfactionMetricParameters customerSatisfactionMetricParameters) {
        List<CustomerSatisfactionDTO> customerSatisfactionDTOS = new ArrayList<>();
        int numPlaceholders = customerSatisfactionMetricParameters.getStates().size();
        String placeHolder = CommonUtils.getParamPlaceholders(numPlaceholders);

        String weeklyPerformanceQuery = "With required_order as (select ORDER_ID, to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?),required_order_in_location as (select ORDER_ID, weekstart, \"state\" from required_order natural join ordered_at natural join location where \"state\" in ( " + placeHolder + ")),required_order_location_review as (select \"state\", weekstart, Round(AVG(score),3) as average_customer_score from required_order_in_location natural join order_review group by (\"state\", weekstart) order by \"state\", cast (weekstart as date)) select * from required_order_location_review";
        String monthlyPerformanceQuery = "With required_order as (select ORDER_ID, to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MON-YY') as monthstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?),required_order_in_location as (select ORDER_ID, monthstart, \"state\" from required_order natural join ordered_at natural join location where \"state\" in (" + placeHolder + ")),required_order_location_review as (select \"state\", monthstart, Round(AVG(score),3) as average_customer_score from required_order_in_location natural join order_review group by (\"state\", monthstart) order by \"state\", cast (monthstart as date)) select * from required_order_location_review";
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement customerSatisfaction
                    = connection.prepareStatement(customerSatisfactionMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyPerformanceQuery : monthlyPerformanceQuery);

            int idx = 1;
            customerSatisfaction.setString(idx++, customerSatisfactionMetricParameters.getStartDate());
            customerSatisfaction.setString(idx++, customerSatisfactionMetricParameters.getEndDate());

            for(String state : customerSatisfactionMetricParameters.getStates()) {
                customerSatisfaction.setString(idx++, state);
            }

            resultSet = customerSatisfaction.executeQuery();
            while(resultSet.next()) {
                String stateName = resultSet.getString(1);
                String weekStart = resultSet.getString(2);
                double avgScore = resultSet.getDouble(3);
                customerSatisfactionDTOS.add(new CustomerSatisfactionDTO(stateName, avgScore, weekStart));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSatisfactionDTOS;
    }

}
