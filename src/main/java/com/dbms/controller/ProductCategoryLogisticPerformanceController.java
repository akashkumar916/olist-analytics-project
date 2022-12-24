package com.dbms.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.common.ProductCategoryLogisticPerformanceParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.ProductCategoryLogisticPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.ProducrCategoryLogisticPerformanceServiceImpl;

@RestController
@RequestMapping("/productCategoryLogisticPerformance")
@CrossOrigin(origins = "*")
public class ProductCategoryLogisticPerformanceController {

	private static final Logger logger = Logger.getLogger(ProductCategoryLogisticPerformanceController.class.getName());

	@Autowired
	private ProducrCategoryLogisticPerformanceServiceImpl producrCategoryLogisticPerformanceServiceImpl;
	
	@PostMapping
	public GenericDataResponse getProductCategoryLogisticPerformance(@RequestBody ProductCategoryLogisticPerformanceParameters productCategoryLogisticPerformanceParameters) throws Exception {
		logger.log(Level.SEVERE, "productCategoryLogisticPerformanceParameters = " + productCategoryLogisticPerformanceParameters);
		if(productCategoryLogisticPerformanceParameters == null || !productCategoryLogisticPerformanceParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = " + productCategoryLogisticPerformanceParameters);
			return new GenericDataResponse<>(ResponseConstants.RESPONSE_FAIL, null);
		}
		
		List<ProductCategoryLogisticPerformanceDTO> productWeeklyPerformanceDTO = producrCategoryLogisticPerformanceServiceImpl.getProductCategoryLogisticPerformance(productCategoryLogisticPerformanceParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, productWeeklyPerformanceDTO);
	}
	
}
