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
import com.dbms.dto.LocationDTO;

@Service
public class LocationServiceImpl {

	private static final Logger logger = Logger.getLogger(LocationServiceImpl.class.getName());

    public List<LocationDTO> getStates() {
        List<LocationDTO> locationDTOs = new ArrayList<>();

        String statesQuery = "select distinct(\"state\") from location";
        
        Connection connection;
        ResultSet resultSet;

        try {
            connection = DriverManager.getConnection(CommonConfig.DB_URL, CommonConfig.DB_USERNAME, CommonConfig.DB_PASSWORD);

            PreparedStatement statesQueryStatement
                    = connection.prepareStatement(statesQuery);

            resultSet = statesQueryStatement.executeQuery();
            while(resultSet.next()) {
                String state = resultSet.getString(1);
                locationDTOs.add(new LocationDTO(state));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locationDTOs;
    }
	
}
