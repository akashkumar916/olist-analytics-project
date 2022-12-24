package com.dbms.service.impl;

import com.dbms.common.*;
import com.dbms.controller.ProductPerformanceController;
import com.dbms.dto.AverageCartValueDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
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
public class AverageCartValueServiceImpl {

    private static final Logger logger = Logger.getLogger(AverageCartValueServiceImpl.class.getName());

    public List<AverageCartValueDTO> getAvgOrderValues(AverageCartValueMetricParameters averageCartValueMetricParameters) {
        List<AverageCartValueDTO> averageCartValues = new ArrayList<>();
        int numPlaceholders = averageCartValueMetricParameters.getStates().size();
        String placeHolder = CommonUtils.getParamPlaceholders(numPlaceholders);

        String weeklyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in ( " + placeHolder + ")), required_order as (select ORDER_ID, to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), required_order_items as (select ORDER_ID, (price + freight_value) as total_order_item_value, weekstart from required_order natural join ordered_items), required_final_orders as (select  CUSTOMER_ID, ORDER_ID, weekstart, zipcode, total_order_item_value from required_order_items natural join ordered_at natural join required_zips) select round(sum(total_order_item_value)/count(distinct(CUSTOMER_ID)), 3) as avg_customer_expenditure, to_char(cast (weekstart as date), 'DD-MON-YY') as weekstartdate from required_final_orders group by weekstart order by cast (weekstart as date)";
        String monthlyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (" + placeHolder + ")),required_order as (select ORDER_ID, to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MM-YY') as monthstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), required_order_items as (select ORDER_ID, (price + freight_value) as total_order_item_value, monthstart from required_order natural join ordered_items),required_final_orders as (select  CUSTOMER_ID, ORDER_ID, monthstart, zipcode, total_order_item_value from required_order_items natural join ordered_at natural join required_zips) select round(sum(total_order_item_value)/count(distinct(CUSTOMER_ID)), 3) as avg_customer_expenditure, to_char(cast (monthstart as date), 'DD-MON-YY') as monthstartdate from required_final_orders group by monthstart order by cast (monthstart as date)";
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement avgValueQuery
                    = connection.prepareStatement(averageCartValueMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyPerformanceQuery : monthlyPerformanceQuery);

            int idx = 1;
            while(idx <= numPlaceholders) {
                avgValueQuery.setString(idx, averageCartValueMetricParameters.getStates().get(idx - 1));
                idx++;
            }
            avgValueQuery.setString(idx, averageCartValueMetricParameters.getStartDate());
            avgValueQuery.setString(idx + 1, averageCartValueMetricParameters.getEndDate());

            resultSet = avgValueQuery.executeQuery();
            while(resultSet.next()) {
                double avgExp = resultSet.getDouble(1);
                String weekStart = resultSet.getString(2);
                averageCartValues.add(new AverageCartValueDTO(avgExp, weekStart));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageCartValues;
    }

}
