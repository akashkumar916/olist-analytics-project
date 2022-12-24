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
import com.dbms.common.NewSellersMetricParameters;
import com.dbms.dto.NewSellersMetricDTO;

@Service
public class NewSellersServiceImpl {

	private static final Logger logger = Logger.getLogger(NewSellersServiceImpl.class.getName());
	
	public List<NewSellersMetricDTO> getNewSellerMetrics(NewSellersMetricParameters newSellersMetricParameters) {
		
		List<NewSellersMetricDTO> categoryRevenuePercentage = new ArrayList<>();

		String weeklyCategoryRevenuePercentage = "with required_sellers as (select seller_id, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart from \"ORDER\" natural join ordered_items) select count(seller_id) as new_sellers, weekstart from (select distinct seller_id, weekstart from required_sellers rs where weekstart >= ? and weekstart < ? and seller_id not in (select distinct(seller_id) from required_sellers where weekstart < rs.weekstart)) group by weekstart order by weekstart";
		
		String monthlyCategoryRevenuePercentage = "with required_sellers as (select seller_id, cast(to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MON-YY') as date) as monthstart from \"ORDER\" natural join ordered_items) select count(seller_id) as new_sellers, monthstart from (select distinct seller_id, monthstart from required_sellers rs where monthstart >= ? and monthstart < ? and seller_id not in (select distinct(seller_id) from required_sellers where monthstart < rs.monthstart)) group by monthstart order by monthstart";

		Connection connection;
		ResultSet resultSet;

		try {
			connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

			PreparedStatement productQuery
					= connection.prepareStatement(newSellersMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyCategoryRevenuePercentage : monthlyCategoryRevenuePercentage);

			productQuery.setString(1, newSellersMetricParameters.getStartDate());
			productQuery.setString(2, newSellersMetricParameters.getEndDate());

			resultSet = productQuery.executeQuery();
			while(resultSet.next()) {
				int newSellers = resultSet.getInt(1);
				String freqStart = resultSet.getString(2);
				categoryRevenuePercentage.add(new NewSellersMetricDTO(newSellers, freqStart));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryRevenuePercentage;
	}
}
