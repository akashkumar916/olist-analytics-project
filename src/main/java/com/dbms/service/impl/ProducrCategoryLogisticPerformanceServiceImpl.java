package com.dbms.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.dbms.common.CommonConfig;
import com.dbms.common.Frequency;
import com.dbms.common.ProductCategoryLogisticPerformanceParameters;
import com.dbms.dto.ProductCategoryLogisticPerformanceDTO;

@Service
public class ProducrCategoryLogisticPerformanceServiceImpl {

private static final Logger logger = Logger.getLogger(ProducrCategoryLogisticPerformanceServiceImpl.class.getName());
	
	public List<ProductCategoryLogisticPerformanceDTO> getProductCategoryLogisticPerformance(ProductCategoryLogisticPerformanceParameters productCategoryLogisticPerformanceParameters) {
		
		List<ProductCategoryLogisticPerformanceDTO> productCategoryLogisticPerformanceDTOs = new ArrayList<>();

		String weeklyProductCategoryLogisticPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (?)), required_orders as (select order_id, purchase_date, carrier_delivery_date, actual_delivery_date, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ? and status = 'delivered'), required_product_category as (select category, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart, (EXTRACT(DAY FROM (actual_delivery_date - purchase_date)) * 24 + EXTRACT(HOUR FROM (actual_delivery_date - purchase_date)) + EXTRACT(MINUTE FROM (actual_delivery_date - purchase_date))/60) as difference from required_orders natural join ordered_items natural join product where category in (?)) select category, weekstart, round(AVG(DIFFERENCE), 2)  as average_time_taken from required_product_category group by category, weekstart order by weekstart";
		
		String monthlyProductCategoryLogisticPerformanceQuery = "With required_zips as (select zipcode from location where \"state\" in (?)), required_orders as (select order_id, purchase_date, carrier_delivery_date, actual_delivery_date, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart from \"ORDER\" where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ? and status = 'delivered'), required_product_category as (select category, cast(to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MON-YY') as date) as monthstart, (EXTRACT(DAY FROM (actual_delivery_date - purchase_date)) * 24 + EXTRACT(HOUR FROM (actual_delivery_date - purchase_date)) + EXTRACT(MINUTE FROM (actual_delivery_date - purchase_date))/60) as difference from required_orders natural join ordered_items natural join product where category in (?)) select category, monthstart, round(AVG(DIFFERENCE), 2)  as average_time_taken from required_product_category group by category, monthstart order by monthstart";

		Connection connection;
		ResultSet resultSet;

		try {
			connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

			PreparedStatement productQuery
					= connection.prepareStatement(productCategoryLogisticPerformanceParameters.getFrequency() == Frequency.WEEKLY ? weeklyProductCategoryLogisticPerformanceQuery : monthlyProductCategoryLogisticPerformanceQuery);

			productQuery.setString(1, productCategoryLogisticPerformanceParameters.getState());
			productQuery.setString(2, productCategoryLogisticPerformanceParameters.getStartDate());
			productQuery.setString(3, productCategoryLogisticPerformanceParameters.getEndDate());
			productQuery.setString(4, productCategoryLogisticPerformanceParameters.getProductCategory());

			resultSet = productQuery.executeQuery();
			while(resultSet.next()) {
				String productCategory = resultSet.getString(1);
				String weekStart = resultSet.getString(2);
				double averageTimeTaken = resultSet.getDouble(3);
				productCategoryLogisticPerformanceDTOs.add(new ProductCategoryLogisticPerformanceDTO(productCategory, weekStart, averageTimeTaken));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productCategoryLogisticPerformanceDTOs;
	}
	
}
