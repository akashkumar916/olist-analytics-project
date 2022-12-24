package com.dbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories
public class OlistAnalyticsServerApplication {
	
	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		SpringApplication.run(OlistAnalyticsServerApplication.class, args);
	}

}
