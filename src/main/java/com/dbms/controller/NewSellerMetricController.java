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

import com.dbms.common.NewSellersMetricParameters;
import com.dbms.common.ResponseConstants;
import com.dbms.dto.NewSellersMetricDTO;
import com.dbms.dto.response.GenericDataResponse;
import com.dbms.service.impl.NewSellersServiceImpl;

@RestController
@RequestMapping("/newSellers")
@CrossOrigin(origins = "*")
public class NewSellerMetricController {

	private static final Logger logger = Logger.getLogger(NewSellerMetricController.class.getName());

	@Autowired
	private NewSellersServiceImpl newSellersServiceImpl;
	
	@PostMapping
	public GenericDataResponse getNewSellersMetrics(@RequestBody NewSellersMetricParameters newSellersMetricParameters) throws Exception {
		logger.log(Level.SEVERE, "newSellerMetrics = " + newSellersMetricParameters);
		if(newSellersMetricParameters == null || !newSellersMetricParameters.isValid()) {
			logger.log(Level.SEVERE, "Invalid params = {}", newSellersMetricParameters);
			throw new Exception("Invalid params");
		}
		
		List<NewSellersMetricDTO> newSellersMetricDTOs = newSellersServiceImpl.getNewSellerMetrics(newSellersMetricParameters);
		return new GenericDataResponse<>(ResponseConstants.RESPONSE_OK, newSellersMetricDTOs);
	}
	
}
