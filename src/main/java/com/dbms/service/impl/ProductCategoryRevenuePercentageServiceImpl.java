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
import com.dbms.common.ProductCategoryRevenuePercentageParameters;
import com.dbms.dto.ProductCategoryRevenuePercentageDTO;

@Service
public class ProductCategoryRevenuePercentageServiceImpl {

	private static final Logger logger = Logger.getLogger(ProductCategoryRevenuePercentageServiceImpl.class.getName());
	
	public List<ProductCategoryRevenuePercentageDTO> getProductCategoryRevenuePercentage(ProductCategoryRevenuePercentageParameters productCategoryRevenuePercentageParameters) {
		
		List<ProductCategoryRevenuePercentageDTO> categoryRevenuePercentage = new ArrayList<>();

		String weeklyCategoryRevenuePercentage = "With required_orders as (select category, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart, price, freight_value from \"ORDER\" natural join ordered_items natural join product where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ? ), weekly_revenue as (select SUM(price + freight_value) as week_revenue, weekstart from required_orders group by weekstart) select category, round(SUM(price + freight_value)/(select week_revenue from weekly_revenue wre where wre.weekstart = ro.weekstart) * 100, 2) as percentage, weekstart from required_orders ro where category IN (?) group by weekstart, category order by weekstart";
		
		String monthlyCategoryRevenuePercentage = "With required_orders as (select category, cast(to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MON-YY') as date) as monthstart, price, freight_value from \"ORDER\" natural join ordered_items natural join product where cast(purchase_date as date) >= ? and cast(purchase_date as date) < ?), weekly_revenue as (select SUM(price + freight_value) as week_revenue, monthstart from required_orders group by monthstart) select category, round(SUM(price + freight_value)/(select week_revenue from weekly_revenue wre where wre.monthstart = ro.monthstart) * 100, 2) as percentage, monthstart from required_orders ro where category IN (?) group by monthstart, category order by monthstart";

		Connection connection;
		ResultSet resultSet;

		try {
			connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

			PreparedStatement productQuery
					= connection.prepareStatement(productCategoryRevenuePercentageParameters.getFrequency() == Frequency.WEEKLY ? weeklyCategoryRevenuePercentage : monthlyCategoryRevenuePercentage);

			productQuery.setString(1, productCategoryRevenuePercentageParameters.getStartDate());
			productQuery.setString(2, productCategoryRevenuePercentageParameters.getEndDate());
			productQuery.setString(3, productCategoryRevenuePercentageParameters.getProductCategory());

			resultSet = productQuery.executeQuery();
			while(resultSet.next()) {
				String category = resultSet.getString(1);
				double percentage = resultSet.getDouble(2);
				String weekStart = resultSet.getString(3);
				categoryRevenuePercentage.add(new ProductCategoryRevenuePercentageDTO(category, percentage, weekStart));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryRevenuePercentage;
	}
}
