package com.dbms.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.CustomerSatisfactionDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.CustomerSatisfactionServiceImpl;
import com.dbms.service.impl.ProductPerformanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dbms.common.CustomerSatisfactionMetricParameters;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/customerSatisfaction")
@CrossOrigin(origins = "*")
public class CustomerSatisfactionController {

	private static final Logger logger = Logger.getLogger(ProductPerformanceController.class.getName());

	@Autowired
	private CustomerSatisfactionServiceImpl customerSatisfactionService;

	@PostMapping
	public GenericDataResponse getCustomerSatisfaction(@RequestBody CustomerSatisfactionMetricParameters customerSatisfactionMetricParameters) {
		if(customerSatisfactionMetricParameters == null || !customerSatisfactionMetricParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", customerSatisfactionMetricParameters);
			return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
		}
		List<CustomerSatisfactionDTO> customerSatisfaction = customerSatisfactionService.getCustomerSatisfaction(customerSatisfactionMetricParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, customerSatisfaction);
	}
}
