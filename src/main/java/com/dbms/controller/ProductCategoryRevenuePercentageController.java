package com.dbms.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.common.ProductCategoryRevenuePercentageParameters;
import com.dbms.common.ProductPerformanceMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.ProductCategoryRevenuePercentageDTO;
import com.dbms.dto.ProductWeeklyPerformanceDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.ProductCategoryRevenuePercentageServiceImpl;
import com.dbms.service.impl.ProductPerformanceServiceImpl;

@RestController
@RequestMapping("/categoryRevenuePercentage")
@CrossOrigin(origins = "*")
public class ProductCategoryRevenuePercentageController {
	
	private static final Logger logger = Logger.getLogger(ProductCategoryRevenuePercentageController.class.getName());

	@Autowired
	private ProductCategoryRevenuePercentageServiceImpl productCategoryRevenuePercentageServiceImpl;
	
	@PostMapping
	public GenericDataResponse getProductCategoryRevenuePercentage(@RequestBody ProductCategoryRevenuePercentageParameters productCategoryRevenuePercentageParameters) throws Exception {
		logger.log(Level.SEVERE, "productCategoryRevenuePercentage = " + productCategoryRevenuePercentageParameters);
		if(productCategoryRevenuePercentageParameters == null || !productCategoryRevenuePercentageParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", productCategoryRevenuePercentageParameters);
			throw new Exception("Invalid params");
		}
		
		List<ProductCategoryRevenuePercentageDTO> productWeeklyPerformanceDTO = productCategoryRevenuePercentageServiceImpl.getProductCategoryRevenuePercentage(productCategoryRevenuePercentageParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, productWeeklyPerformanceDTO);
	}
	
}
