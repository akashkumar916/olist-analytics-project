package com.dbms.controller;

import com.dbms.common.ResponseConstants;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.service.impl.ProductPerformanceServiceImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/productPerformance")
@CrossOrigin(origins = "*")
public class ProductPerformanceController {

	private static final Logger logger = Logger.getLogger(ProductPerformanceController.class.getName());

	@Autowired
	private ProductPerformanceServiceImpl productPerformanceService;
	
	@PostMapping
	public GenericDataResponse getProductPerformance(@RequestBody ProductPerformanceMetricParameters productPerformanceMetric) throws Exception {
		if(productPerformanceMetric == null || !productPerformanceMetric.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", productPerformanceMetric);
			return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
		}
		List<ProductWeeklyPerformanceDTO> productWeeklyPerformanceDTO = productPerformanceService.getProductPerformance(productPerformanceMetric);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, productWeeklyPerformanceDTO);
	}
}