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
import com.dbms.common.NewCustomersMetricParameters;
import com.dbms.dto.NewCustomersMetricDTO;

@Service
public class NewCustomersServiceImpl {

	private static final Logger logger = Logger.getLogger(NewCustomersServiceImpl.class.getName());
	
	public List<NewCustomersMetricDTO> getNewCustomersMetric(NewCustomersMetricParameters newCustomersMetricParameters) {
		
		List<NewCustomersMetricDTO> newCustomersMetricDTOs = new ArrayList<>();

		String weeklyNewCustomers = "with required_customers as (select customer_id, cast(to_char(cast(purchase_date AS date) + 1 - to_char(cast(purchase_date AS date), 'd'), 'DD-MON-YY') as date) as weekstart from \"ORDER\" natural join ordered_at) select count(customer_id) as new_customers, weekstart from (select distinct customer_id, weekstart from required_customers rs where weekstart >= ? and weekstart < ? and customer_id not in (select distinct(customer_id) from required_customers where weekstart < rs.weekstart)) group by weekstart order by weekstart";
		
		String monthlyNewCustomers = "with required_customers as (select customer_id, cast(to_char(TRUNC(cast(purchase_date AS date), 'MM'), 'DD-MON-YY') as date) as monthstart from \"ORDER\" natural join ordered_at) select count(customer_id) as new_customers, monthstart from (select distinct customer_id, monthstart from required_customers rs where monthstart >= ? and monthstart < ? and customer_id not in (select distinct(customer_id) from required_customers where monthstart < rs.monthstart)) group by monthstart order by monthstart";

		Connection connection;
		ResultSet resultSet;

		try {
			connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

			PreparedStatement productQuery
					= connection.prepareStatement(newCustomersMetricParameters.getFrequency() == Frequency.WEEKLY ? weeklyNewCustomers : monthlyNewCustomers);

			productQuery.setString(1, newCustomersMetricParameters.getStartDate());
			productQuery.setString(2, newCustomersMetricParameters.getEndDate());

			resultSet = productQuery.executeQuery();
			while(resultSet.next()) {
				int newCustomers = resultSet.getInt(1);
				String freqStart = resultSet.getString(2);
				newCustomersMetricDTOs.add(new NewCustomersMetricDTO(newCustomers, freqStart));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newCustomersMetricDTOs;
	}	
}
