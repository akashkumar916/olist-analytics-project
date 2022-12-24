package com.dbms.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dbms.common.NewCustomersMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.NewCustomersMetricDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.NewCustomersServiceImpl;

@RestController
@RequestMapping("/newCustomers")
@CrossOrigin(origins = "*")
public class NewCustomerMetricController {

	private static final Logger logger = Logger.getLogger(NewSellerMetricController.class.getName());

	@Autowired
	private NewCustomersServiceImpl newCustomersServiceImpl;
	
	@PostMapping
	public GenericDataResponse getNewCustomerMetric(@RequestBody NewCustomersMetricParameters newCustomersMetricParameters) throws Exception {
		logger.log(Level.SEVERE, "newCustomersMetricParameters = " + newCustomersMetricParameters);
		if(newCustomersMetricParameters == null || !newCustomersMetricParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", newCustomersMetricParameters);
			throw new Exception("Invalid params");
		}
		
		List<NewCustomersMetricDTO> newCustomersMetricDTOs = newCustomersServiceImpl.getNewCustomersMetric(newCustomersMetricParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, newCustomersMetricDTOs);
	}
	
}
