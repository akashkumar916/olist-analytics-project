package com.dbms.service.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.dbms.common.CommonUtils;
import org.springframework.stereotype.Service;

import com.dbms.common.CommonConfig;
import com.dbms.common.Frequency;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.controller.ProductPerformanceController;
import com.dbms.dto.ProductWeeklyPerformanceDTO;

@Service
public class ProductPerformanceServiceImpl {

	private static final Logger logger = Logger.getLogger(ProductPerformanceController.class.getName());
	
	public List<ProductWeeklyPerformanceDTO> getProductPerformance(ProductPerformanceMetricParameters productPerformanceMetricParameters) {
		List<ProductWeeklyPerformanceDTO> productWeeklyPerformance = new ArrayList<>();
		int numPlaceholders = productPerformanceMetricParameters.getStates().size();
		String placeHolder = CommonUtils.getParamPlaceholders(numPlaceholders);

		String weeklyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (" + placeHolder + ")),required_order as (select ORDER_ID,to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), required_order_items as (select ORDER_ID, product_id, weekstart from required_order natural join ordered_items), required_final_orders as (select  ORDER_ID, product_id, weekstart, zipcode from required_order_items natural join ordered_at natural join required_zips),required_products as (select product_id from product where category = ?)select count(*) as num_orders, cast (weekstart as date) as weekstartdate from required_products natural join required_final_orders group by weekstart order by weekstartdate";
		String monthlyPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in ("+ placeHolder + ")),required_order as (select ORDER_ID,to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MM-YY') as monthstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), required_order_items as (select ORDER_ID, product_id, monthstart from required_order natural join ordered_items),required_final_orders as (select  ORDER_ID, product_id, monthstart, zipcode from required_order_items natural join ordered_at natural join required_zips),required_products as (select product_id from product where category = ?) select count(*) as num_orders, cast (monthstart as date) as monthstartdate from required_products natural join required_final_orders group by monthstart order by monthstartdate";
		Connection connection;
		ResultSet resultSet;

		try {
			connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

			PreparedStatement productQuery
					= connection.prepareStatement(productPerformanceMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyPerformanceQuery : monthlyPerformanceQuery);

			int idx = 1;
			while(idx <= numPlaceholders) {
				productQuery.setString(idx, productPerformanceMetricParameters.getStates().get(idx - 1));
				idx++;
			}
			productQuery.setString(idx, productPerformanceMetricParameters.getStartDate());
			productQuery.setString(idx + 1, productPerformanceMetricParameters.getEndDate());
			productQuery.setString(idx + 2, productPerformanceMetricParameters.getProductCategory());

			resultSet = productQuery.executeQuery();
			while(resultSet.next()) {
				int numOrders = resultSet.getInt(1);
				String weekStart = resultSet.getString(2);
				productWeeklyPerformance.add(new ProductWeeklyPerformanceDTO(numOrders, weekStart));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productWeeklyPerformance;
	}
}
