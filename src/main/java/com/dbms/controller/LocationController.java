package com.dbms.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.common.ResponseConstants;
import com.dbms.dto.LocationDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.LocationServiceImpl;

@RestController
@RequestMapping("/getStates")
@CrossOrigin(origins = "*")
public class LocationController {

	private static final Logger logger = Logger.getLogger(LocationController.class.getName());

	@Autowired
	private LocationServiceImpl locationServiceImpl;
	
	@GetMapping
	public GenericDataResponse getStates() throws Exception {
		
		List<LocationDTO> locationDTOs = locationServiceImpl.getStates();
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, locationDTOs);
	}
}
