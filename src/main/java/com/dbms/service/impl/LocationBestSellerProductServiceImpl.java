package com.dbms.service.impl;

import com.dbms.common.*;
import com.dbms.controller.ProductPerformanceController;
import com.dbms.dto.LocationBestSellerDTO;
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
public class LocationBestSellerProductServiceImpl {

    private static final Logger logger = Logger.getLogger(LocationBestSellerProductServiceImpl.class.getName());

    public List<LocationBestSellerDTO> getProductPerformance(LocationBestSellingProductMetricParameters locationBestSellingProductMetricParameters) {
        List<LocationBestSellerDTO> locationBestSellerDTOS = new ArrayList<>();
        int numPlaceholders = locationBestSellingProductMetricParameters.getStates().size();
        String placeHolder = CommonUtils.getParamPlaceholders(numPlaceholders);

        String weeklyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (" + placeHolder + ")),required_order as (select ORDER_ID, to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), required_order_items as (select ORDER_ID, category, weekstart from required_order natural join ordered_items natural join product), required_final_orders as (select  ORDER_ID, category, weekstart, zipcode from required_order_items natural join ordered_at natural join required_zips), required_grouped_orders as (select count(*) as num_orders_for_category, to_char(cast (weekstart as date), 'DD-MON-YY') as weekstartdate, category from required_final_orders group by (category,weekstart)) select * from required_grouped_orders r1 where r1.num_orders_for_category >= all (select num_orders_for_category from required_grouped_orders r2 where r1.weekstartdate = r2.weekstartdate) order by cast(r1.weekstartdate as date)";
        String monthlyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (" + placeHolder + ")),required_order as (select ORDER_ID, to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MM-YY') as monthstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?),required_order_items as (select ORDER_ID, category, monthstart from required_order natural join ordered_items natural join product), required_final_orders as (select  ORDER_ID, category, monthstart, zipcode from required_order_items natural join ordered_at natural join required_zips), required_grouped_orders as (select count(*) as num_orders_for_category, to_char(cast (monthstart as date), 'DD-MON-YY') as monthstartdate, category from required_final_orders group by (category,monthstart)) select * from required_grouped_orders r1 where r1.num_orders_for_category >= all (select num_orders_for_category from required_grouped_orders r2 where r1.monthstartdate = r2.monthstartdate) order by cast(r1.monthstartdate as date)";
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement productQuery
                    = connection.prepareStatement(locationBestSellingProductMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyPerformanceQuery : monthlyPerformanceQuery);

            int idx = 1;
            while(idx <= numPlaceholders) {
                productQuery.setString(idx, locationBestSellingProductMetricParameters.getStates().get(idx - 1));
                idx++;
            }
            productQuery.setString(idx, locationBestSellingProductMetricParameters.getStartDate());
            productQuery.setString(idx + 1, locationBestSellingProductMetricParameters.getEndDate());

            resultSet = productQuery.executeQuery();
            while(resultSet.next()) {
                int numOrders = resultSet.getInt(1);
                String startDate = resultSet.getString(2);
                String category = resultSet.getString(3);
                locationBestSellerDTOS.add(new LocationBestSellerDTO(numOrders, category, startDate));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locationBestSellerDTOS;
    }
}
